package com.winery.dto;

import org.springframework.stereotype.Component;

@Component
public class PercentageKeyDto {

    private String percentage;

    private String key;

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
