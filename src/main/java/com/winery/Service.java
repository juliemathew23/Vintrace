package com.winery;

import com.winery.dto.BreakdownDto;
import com.winery.dto.PercentageKeyDto;
import com.winery.dto.WineDto;
import com.winery.model.Details;
import com.winery.model.Model;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class Service {

    public static BreakdownDto getBreakDownOnProperties(String lotCode, String[] properties) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Model model = Utililty.readFromJson(lotCode, false);
        List<Method> methodList = new ArrayList<>();

        for(int i = 0; i < properties.length; i++) {
            methodList.add(getBreakdownMethod(properties[i]));
        }

        BreakdownDto dto = getBreakDownForProperties(model.getComponents(), methodList, properties);

        return dto;
    }


    private static Method getBreakdownMethod(String property) throws NoSuchMethodException {

        Method method;

        switch(property) {
            case "YEAR":
                method = Details.class.getMethod("getYear");
                break;

            case "VARIETY":
                method = Details.class.getMethod("getVariety");
                break;

            case "REGION":
                method = Details.class.getMethod("getRegion");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + property);
        }

        return method;
    }

    private static BreakdownDto getBreakDownForProperties(List<Details> components, List<Method> methodList, String[] properties) throws InvocationTargetException, IllegalAccessException {

        HashMap<String, Float> breakDownMap = new HashMap<>();

        if(methodList.size() > 1) {
            populateMap(components, properties, breakDownMap);
        }

        else {
                for (Details detail : components) {
                    if (breakDownMap.containsKey(methodList.get(0).invoke(detail))) {
                        float currentPercentage = breakDownMap.get(methodList.get(0).invoke(detail));
                        breakDownMap.put((String) methodList.get(0).invoke(detail), Float.parseFloat(detail.getPercentage()) + currentPercentage);
                    } else {
                        breakDownMap.put((String) methodList.get(0).invoke(detail), Float.parseFloat(detail.getPercentage()));
                    }
                }
            }

        BreakdownDto dto = populateDTO(breakDownMap, properties);

        return dto;
    }

    private static void populateMap(List<Details> components, String[] properties, HashMap<String, Float> breakDownMap) {

        Set<String> varieties = null;
        Set<String> years = null;
        Set<String> regions = null;


        for (int i = 0; i < properties.length; i++) {
            if (properties[i].equals(WineProperties.VARIETY.name()))
                varieties = new HashSet<>();
            if (properties[i].equals(WineProperties.YEAR.name()))
                years = new HashSet<>();
            if (properties[i].equals(WineProperties.REGION.name()))
                regions = new HashSet<>();
        }


        if (varieties != null && years != null) {
            for (Details detail : components) {
                varieties.add(detail.getVariety());
                years.add(detail.getYear());
            }

            Object[] varietyObj = varieties.toArray();
            Object[] yearObj = years.toArray();
            float percentage = 0f;

            for (int i = 0; i < varietyObj.length; i++) {
                for (int j = 0; j < yearObj.length; j++) {
                    for (Details detail : components) {
                        if(detail.getYear().equals(yearObj[j]) && detail.getVariety().equals(varietyObj[i])){
                            percentage = Float.parseFloat(detail.getPercentage());
                        }
                    }
                    if (breakDownMap.containsKey(varietyObj[i] + "-" + yearObj[j])) {
                        float currentPercentage = breakDownMap.get(varietyObj[i] + "-" + yearObj[j]);
                        breakDownMap.put((String) varietyObj[i] + "-" + yearObj[j], percentage + currentPercentage);
                    } else {
                        breakDownMap.put((String) varietyObj[i] + "-" + yearObj[j], percentage);
                    }
                }
            }
        }
    }

    private static BreakdownDto populateDTO(HashMap<String, Float> breakDownMap, String[] properties) {
        BreakdownDto dto = new BreakdownDto();
        if(properties.length > 1) {
            dto.setBreakDownType(String.join("-", properties));
        }
        else
            dto.setBreakDownType(properties[0]);


        List<PercentageKeyDto> percentageKeyDtos = new ArrayList<>();

        for(Map.Entry<String, Float> entry : breakDownMap.entrySet()){
            PercentageKeyDto percentageKeyDto = new PercentageKeyDto();

            percentageKeyDto.setKey(entry.getKey());
            percentageKeyDto.setPercentage(String.valueOf(entry.getValue()));

            percentageKeyDtos.add(percentageKeyDto);
        }

        Collections.sort(percentageKeyDtos,new Comparator<PercentageKeyDto>(){
            public int compare(PercentageKeyDto s1,PercentageKeyDto s2){
                return (int) (Float.parseFloat(s1.getPercentage()) - Float.parseFloat(s2.getPercentage()));
            }});

        dto.setBreakDown(percentageKeyDtos);

        return dto;
    }


    public List<WineDto> searchWine(String lot, String description) throws IOException, ParseException, URISyntaxException {
        List<Model> modelList = new ArrayList<>();

        if(lot != null){
            Model model = Utililty.readFromJson(lot, true);
            if(description != null && description.equals(model.getDescription())){

            }
                modelList.add(model);
        }
        else {
            List<Model> newModelList = Utililty.readFromAllJson();
            if(description != null){
                for(Model model: newModelList){
                    if(description.equals(model.getDescription()))
                        modelList.add(model);
                }
            }
                else
                    modelList = newModelList;
        }

        List<WineDto> dtoList = populateWineDto(modelList);

        return dtoList;
    }

    private List<WineDto> populateWineDto(List<Model> modelList) {
        List<WineDto> wineList = new ArrayList<>();

        for(Model model: modelList) {

            WineDto wineDto = new WineDto();
            wineDto.setDescription(model.getDescription());
            wineDto.setLotCode(model.getLotCode());
            wineDto.setVolume(model.getVolume());
            wineDto.setTank(model.getTankCode());
            wineDto.setProductState(model.getProductState());
            wineDto.setOwner(model.getOwnerName());

            wineList.add(wineDto);

        }


        return wineList;
    }
}
