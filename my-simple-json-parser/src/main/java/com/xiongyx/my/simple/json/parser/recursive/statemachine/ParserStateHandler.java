package com.xiongyx.my.simple.json.parser.recursive.statemachine;

import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.reader.JsonTokenReader;
import com.xiongyx.my.simple.json.parser.recursive.RecursiveDoParserContext;

public interface ParserStateHandler<T extends JsonElement> {

    int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<T> recursiveDoParserContext);
}
