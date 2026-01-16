package com.xiongyx.my.simple.json.parser.reader;

import com.xiongyx.my.simple.json.lexer.StreamJsonLexer;
import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;

public class StreamJsonTokenReader implements JsonTokenReader {

    private final int currentIndex;
    private final StreamJsonLexer streamJsonLexer;

    private JsonToken peekToken;
    private boolean hasNextToken;

    public StreamJsonTokenReader(String jsonString) {
        this.currentIndex = 0;
        this.hasNextToken = true;
        this.streamJsonLexer = new StreamJsonLexer(jsonString);
    }

    @Override
    public boolean hasNextToken() {
        return hasNextToken;
    }

    @Override
    public JsonToken nextToken() {
        JsonToken nextToken = getNextToken();
        if(nextToken.getType() == JsonTokenTypeEnum.EOF){
            hasNextToken = false;
        }

        return nextToken;
    }

    private JsonToken getNextToken() {
        if(peekToken != null){
            JsonToken nextToken = peekToken;
            this.peekToken = null;
            return nextToken;
        }

        return streamJsonLexer.doLex();
    }

    @Override
    public JsonToken peek() {
        if(peekToken == null) {
            peekToken = streamJsonLexer.doLex();
        }

        return peekToken;
    }

    @Override
    public int currentIndex() {
        return currentIndex;
    }
}
