package com.xiongyx.my.simple.json.parser;

import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.reader.JsonTokenReader;

public abstract class JsonParser {

    protected final JsonTokenReader jsonTokenReader;

    public JsonParser(JsonTokenReader jsonTokenReader) {
        this.jsonTokenReader = jsonTokenReader;
    }

    public abstract JsonElement doParse();
}
