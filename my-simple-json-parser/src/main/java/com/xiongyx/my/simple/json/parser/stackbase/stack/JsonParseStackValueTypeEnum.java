package com.xiongyx.my.simple.json.parser.stackbase.stack;

import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.parser.model.JsonArray;
import com.xiongyx.my.simple.json.parser.model.JsonObject;
import com.xiongyx.my.simple.json.parser.model.JsonPrimitiveStr;

public enum JsonParseStackValueTypeEnum {

    JSON_OBJECT(JsonObject.class),
    JSON_ARRAY(JsonArray.class),
    JSON_KEY(JsonToken.class),
    JSON_PRIMITIVE(JsonPrimitiveStr.class),
    ;

    JsonParseStackValueTypeEnum(Class type) {
        this.type = type;
    }

    private final Class type;

    public Class getType() {
        return type;
    }
}
