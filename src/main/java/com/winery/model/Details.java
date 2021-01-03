package com.winery.model;

public class Details {

    private String percentage;

    private String year;

    private String variety;

    private String region;

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
