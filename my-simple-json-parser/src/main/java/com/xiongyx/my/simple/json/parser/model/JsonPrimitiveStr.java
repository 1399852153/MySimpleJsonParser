package com.xiongyx.my.simple.json.parser.model;

public class JsonPrimitiveStr extends JsonElement{

    /**
     * 基础类型的字符串字面量
     * */
    private final String primitiveValueStr;

    public JsonPrimitiveStr(String primitiveValueStr) {
        this.primitiveValueStr = primitiveValueStr;
    }

    public String getPrimitiveValueStr() {
        return primitiveValueStr;
    }

    @Override
    public String toString() {
        return primitiveValueStr;
    }
}
