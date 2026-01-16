package com.xiongyx.my.simple.json.lexer.statemachine;

import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;

import java.util.Map;

public class KeywordFalseLexStatementMachine extends KeywordLexStatementMachine{

    private static final String KEYWORD = JsonTokenTypeEnum.FALSE.getKey();
    private static final Map<Integer,Boolean> staticIsFinalStateMap;
    private static final LexStateHandler[] lexStateHandlers;

    static {
        staticIsFinalStateMap = buildIsFinalStateMap(KEYWORD);
        lexStateHandlers = buildLexStateHandlers(KEYWORD);
    }

    public KeywordFalseLexStatementMachine() {
        super(KEYWORD);

        super.isFinalStateMap = staticIsFinalStateMap;
        super.stateHandlers = lexStateHandlers;
    }
}
