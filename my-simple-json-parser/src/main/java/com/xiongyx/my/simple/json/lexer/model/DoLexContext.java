package com.xiongyx.my.simple.json.lexer.model;

import java.util.ArrayList;
import java.util.List;

public class DoLexContext {

    public int currentIndex = 0;

    public List<JsonToken> tokenCollector = new ArrayList<>();
}
