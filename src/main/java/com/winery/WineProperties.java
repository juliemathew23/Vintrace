package com.winery;

public enum WineProperties {
    YEAR("year"),
    VARIETY("variety"),
    REGION("region"),
    ;

    public String label;

    private WineProperties(String label) {
        this.label = label;
    }
}
