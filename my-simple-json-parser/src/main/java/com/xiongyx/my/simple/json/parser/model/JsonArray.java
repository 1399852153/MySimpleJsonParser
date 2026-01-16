package com.xiongyx.my.simple.json.parser.model;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonElement{

    private List<JsonElement> array = new ArrayList<>();

    public void addElement(JsonElement element) {
        array.add(element);
    }

    public List<JsonElement> getArray() {
        return array;
    }

    @Override
    public String toString() {
        return array.toString();
    }
}
