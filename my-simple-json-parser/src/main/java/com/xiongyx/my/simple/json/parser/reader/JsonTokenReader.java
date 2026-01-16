package com.xiongyx.my.simple.json.parser.reader;

import com.xiongyx.my.simple.json.lexer.model.JsonToken;

public interface JsonTokenReader {

    boolean hasNextToken();

    JsonToken nextToken();

    JsonToken peek();

    int currentIndex();
}
