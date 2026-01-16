package com.xiongyx.my.simple.json.parser.recursive;

import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.parser.model.JsonElement;

import java.util.Stack;

public class RecursiveDoParserContext<T extends JsonElement>  {

    private Stack<JsonToken> tokenStack = new Stack<>();

    private T targetJsonElement;

    public RecursiveDoParserContext(T targetJsonElement) {
        this.targetJsonElement = targetJsonElement;
    }

    public Stack<JsonToken> getTokenStack() {
        return tokenStack;
    }

    public T getTargetJsonElement() {
        return targetJsonElement;
    }
}
