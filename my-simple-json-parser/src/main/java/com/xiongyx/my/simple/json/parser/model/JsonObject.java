package com.xiongyx.my.simple.json.parser.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * json AST的object类型节点
 * */
public class JsonObject extends JsonElement{

    private final Map<String,JsonElement> objMap = new LinkedHashMap<>();

    public void putKey(String key, JsonElement value) {
        objMap.put(key, value);
    }

    public Map<String, JsonElement> getObjMap() {
        return objMap;
    }

    @Override
    public String toString() {
        return objMap.toString();
    }
}
