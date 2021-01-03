package com.winery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BreakdownDto {

    @JsonProperty("breakDownType")
    private String breakDownType;

    @JsonProperty("breakDown")
    private List<PercentageKeyDto> breakDown;

    public String getBreakDownType() {
        return breakDownType;
    }

    public void setBreakDownType(String breakDownType) {
        this.breakDownType = breakDownType;
    }

    public List<PercentageKeyDto> getBreakDown() {
        return breakDown;
    }

    public void setBreakDown(List<PercentageKeyDto> breakDown) {
        this.breakDown = breakDown;
    }
}
