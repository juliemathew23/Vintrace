package com.winery.dto;

public class DetailsDto {

    private String percentage;

    private String year;

    private String variety;

    private String region;

    public DetailsDto percentage(String percentage) {
        this.setPercentage(percentage);
        return this;
    }

    public DetailsDto year(String year) {
        this.setYear(year);
        return this;
    }

    public DetailsDto variety(String variety) {
        this.setVariety(variety);
        return this;
    }

    public DetailsDto region(String region) {
        this.setRegion(region);
        return this;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Details{" +
                "percentage='" + percentage + '\'' +
                ", year='" + year + '\'' +
                ", variety='" + variety + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
