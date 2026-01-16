package com.xiongyx.my.simple.json.parser.reader;

import com.xiongyx.my.simple.json.lexer.StaticJsonLexer;
import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;

import java.util.List;

public class StaticJsonTokenReader implements JsonTokenReader {

    private int currentIndex;

    private final List<JsonToken> tokens;

    public StaticJsonTokenReader(String jsonString) {
        this.currentIndex = 0;

        StaticJsonLexer staticJsonLexer = new StaticJsonLexer();
        this.tokens  = staticJsonLexer.doLex(jsonString);
    }

    @Override
    public boolean hasNextToken() {
        return tokens.get(currentIndex).getType() != JsonTokenTypeEnum.EOF;
    }

    @Override
    public JsonToken nextToken() {
        JsonToken jsonToken = tokens.get(currentIndex);
        currentIndex++;
        return jsonToken;
    }

    @Override
    public JsonToken peek() {
        return tokens.get(currentIndex);
    }

    @Override
    public int currentIndex() {
        return this.currentIndex;
    }


}
