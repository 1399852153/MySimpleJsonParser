package com.xiongyx.my.simple.json.lexer.statemachine;

import com.xiongyx.my.simple.json.lexer.model.DoLexContext;

public interface LexStateHandler {

    int processInState(char[] chars, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult);
}
