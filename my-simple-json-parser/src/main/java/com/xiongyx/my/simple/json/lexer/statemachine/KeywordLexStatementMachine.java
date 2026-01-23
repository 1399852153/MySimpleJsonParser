package com.xiongyx.my.simple.json.lexer.statemachine;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.model.DoLexContext;

import java.util.HashMap;
import java.util.Map;

public abstract class KeywordLexStatementMachine extends LexStatementMachine{

    protected final String keyword;

    public KeywordLexStatementMachine(String keyword) {
        this.keyword = keyword;
    }

    protected static Map<Integer,Boolean> buildIsFinalStateMap(String keyword){
        Map<Integer,Boolean> isFinalStateMap = new HashMap<>(keyword.length() + 1);
        isFinalStateMap.put(-1,true);

        for(int i=0; i<keyword.length(); i++) {
            isFinalStateMap.put(i,false);
        }

        // 最后一个字符就是合理的终态
        isFinalStateMap.put(keyword.length(),true);

        return isFinalStateMap;
    }

    protected static LexStateHandler[] buildLexStateHandlers(String keyword){
        LexStateHandler[] lexStateHandlers = new LexStateHandler[keyword.length() + 1];

        for(int i=0; i<keyword.length(); i++) {
            char c = keyword.charAt(i);

            lexStateHandlers[i] = new KeywordLexStateHandler(c,i+1);
        }

        // 最后一个状态，直接返回
        lexStateHandlers[keyword.length()] = new KeywordLexStateHandler(' ',-1);

        return lexStateHandlers;
    }

    private static class KeywordLexStateHandler implements LexStateHandler {

        private final char targetCh;
        private final int nextState;

        public KeywordLexStateHandler(char targetCh, int nextState) {
            this.targetCh = targetCh;
            this.nextState = nextState;
        }

        @Override
        public int processInState(char[] chars, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            char currentChar = chars[doLexContext.currentIndex];

            return doProcessInState(currentChar,doLexContext,oneTokenAcceptResult);
        }

        private int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult){
            if(nextState == -1){
                // -1是特殊的直接返回
                return nextState;
            }

            if(currentChar == targetCh) {
                // 接收，进入下一个状态
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return nextState;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }

        @Override
        public String toString() {
            return "KeywordLexMachineStateHandler{" +
                "targetCh=" + targetCh +
                ", nextState=" + nextState +
                '}';
        }
    }

    @Override
    public String toString() {
        return "KeywordLexStatementMachine{" +
            "keyword='" + keyword + '\'' +
            "} " + super.toString();
    }

    public static void main(String[] args) {
        String result = new KeywordNullLexStatementMachine().tryParse("nulaa".toCharArray(),new DoLexContext());
        System.out.println(result);
    }
}
