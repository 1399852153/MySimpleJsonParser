package com.xiongyx.my.simple.json.lexer.statemachine;

import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;

import java.util.Map;

public class KeywordTrueLexStatementMachine extends KeywordLexStatementMachine{

    private static final String KEYWORD = JsonTokenTypeEnum.TRUE.getKey();
    private static final Map<Integer,Boolean> staticIsFinalStateMap;
    private static final LexStateHandler[] lexStateHandlers;

    static {
        staticIsFinalStateMap = buildIsFinalStateMap(KEYWORD);
        lexStateHandlers = buildLexStateHandlers(KEYWORD);
    }

    public KeywordTrueLexStatementMachine() {
        super(KEYWORD);

        super.isFinalStateMap = staticIsFinalStateMap;
        super.stateHandlers = lexStateHandlers;
    }
}
