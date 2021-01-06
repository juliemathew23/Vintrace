package com.winery;

import com.winery.model.Details;
import com.winery.model.Model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Utililty {

    public static Model readFromJson(String fileName) throws IOException, ParseException {
        ClassLoader classLoader = Utililty.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName +".json").getFile());

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;

        Model model = new Model();
        model.setLotCode(String.valueOf(jsonObject.get("lotCode")));

        //if(searchFlag) {
            model.setDescription(String.valueOf(jsonObject.get("description")));
            model.setVolume(String.valueOf(jsonObject.get("volume")));
            model.setTankCode(String.valueOf(jsonObject.get("tankCode")));
            model.setProductState(String.valueOf(jsonObject.get("productState")));
            model.setOwnerName(String.valueOf(jsonObject.get("ownerName")));
        //}

        //else {
            List<Details> detailsList = new ArrayList<>();

            JSONArray components = (JSONArray) jsonObject.get("components");

            for (int i = 0; i < components.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) components.get(i);
                Details details = new Details();

                details.setPercentage(String.valueOf(jsonObject1.get("percentage")));
                details.setVariety(String.valueOf(jsonObject1.get("variety")));
                details.setYear(String.valueOf(jsonObject1.get("year")));
                details.setRegion(String.valueOf(jsonObject1.get("region")));

                detailsList.add(details);
            }

            model.setComponents(detailsList);
        //}

        return model;
    }

    public static List<Model> readFromAllJson() throws IOException, URISyntaxException, ParseException {
        List<Model> modelList = new ArrayList<>();
        List<String> lokNameList = getResourceFolderFiles();

        for(String lokName: lokNameList){
            modelList.add(readFromJson(lokName));
        }

        return modelList;
    }

        private static List<String> getResourceFolderFiles() throws URISyntaxException, IOException {
            List<String> lokNameList = new ArrayList<>();

            ClassLoader classLoader = Utililty.class.getClassLoader();
            URI uri = classLoader.getResource("").toURI();
            Path myPath;
            if (uri.getScheme().equals("json")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
                myPath = fileSystem.getPath("/resources");
            } else {
                myPath = Paths.get(uri);
            }
            Stream<Path> walk = Files.walk(myPath, 1);
            for (Iterator<Path> it = walk.iterator(); it.hasNext();){
                Path path = it.next();
                if(path.toString().endsWith("json"))
                    lokNameList.add(path.getFileName().toString().split(".json")[0]);
            }

            return lokNameList;
        }


}
