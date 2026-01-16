package com.xiongyx.my.simple.json.lexer.model;

import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.util.MyJsonUnicodeUtils;

public class JsonToken {

    private final JsonTokenTypeEnum type;

    private final String content;

    public JsonToken(JsonTokenTypeEnum type) {
        this.type = type;
        this.content = type.getKey();
    }

    public JsonToken(JsonTokenTypeEnum type, String content) {
        this.type = type;

        if(type == JsonTokenTypeEnum.STRING) {
            // string类型，默认将unicode字符还原为普通字符
            this.content = MyJsonUnicodeUtils.unicodeToString(content);
        }else{
            this.content = content;
        }
    }

    public JsonTokenTypeEnum getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "JsonToken{" +
            "type=" + type +
            ", content='" + content + '\'' +
            '}';
    }
}
