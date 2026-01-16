package com.xiongyx.my.simple.json.parser.stackbase.stack;

import com.xiongyx.my.simple.json.util.Assert;

public class JsonParseStackValue {

    private final JsonParseStackValueTypeEnum type;
    private final Object value;

    public JsonParseStackValue(JsonParseStackValueTypeEnum type, Object value) {
        Assert.assertTrue(value.getClass() == type.getType(),"type not match!");

        this.type = type;
        this.value = value;
    }

    public JsonParseStackValueTypeEnum getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "JsonParseStackValue{" +
            "type=" + type +
            ", value=" + value +
            '}';
    }
}
