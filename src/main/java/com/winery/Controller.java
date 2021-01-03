package com.winery;

import com.winery.dto.BreakdownDto;
import com.winery.dto.WineDto;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("breakdown/year/{lotCode}")
    public BreakdownDto getBreakDownOnYear(@PathVariable("lotCode") String lotCode) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
      return service.getBreakDownOnProperties(lotCode, new String[] { WineProperties.YEAR.name() });
    }

    @GetMapping("breakdown/variety/{lotCode}")
    public BreakdownDto getBreakDownOnVariety(@PathVariable("lotCode") String lotCode) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return service.getBreakDownOnProperties(lotCode, new String[] { WineProperties.VARIETY.name() });
    }

    @GetMapping("breakdown/region/{lotCode}")
    public BreakdownDto getBreakDownOnRegion(@PathVariable("lotCode") String lotCode) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return service.getBreakDownOnProperties(lotCode, new String[] { WineProperties.REGION.name() });
    }

    @GetMapping("breakdown/year-variety/{lotCode}")
    public BreakdownDto getBreakDownOnYearVariety(@PathVariable("lotCode") String lotCode) throws IOException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return service.getBreakDownOnProperties(lotCode, new String[] { WineProperties.YEAR.name(), WineProperties.VARIETY.name() });
    }

    @GetMapping("search")
    public List<WineDto> searchWine(@RequestParam(required = false, name = "lot") String lot, @RequestParam(required = false, name = "description") String description) throws ParseException, IOException, URISyntaxException {
        return service.searchWine(lot, description);
    }
}
