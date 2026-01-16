package com.xiongyx.my.simple.json.model;

public class JsonParseResult {

    private String compactJsonString;
    private String beautyJsonString;

    public JsonParseResult(String compactJsonString, String beautyJsonString) {
        this.compactJsonString = compactJsonString;
        this.beautyJsonString = beautyJsonString;
    }

    public String getCompactJsonString() {
        return compactJsonString;
    }

    public void setCompactJsonString(String compactJsonString) {
        this.compactJsonString = compactJsonString;
    }

    public String getBeautyJsonString() {
        return beautyJsonString;
    }

    public void setBeautyJsonString(String beautyJsonString) {
        this.beautyJsonString = beautyJsonString;
    }

    @Override
    public String toString() {
        return "JsonParseResult{" +
            "compactJsonString='" + compactJsonString + '\n' +
            ", beautyJsonString='" + beautyJsonString + '\n' +
            '}';
    }
}
