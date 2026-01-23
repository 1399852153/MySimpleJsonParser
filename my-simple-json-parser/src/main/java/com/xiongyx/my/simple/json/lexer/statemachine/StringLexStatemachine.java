package com.xiongyx.my.simple.json.lexer.statemachine;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.model.DoLexContext;
import com.xiongyx.my.simple.json.util.CommonStringUtil;

import java.util.HashMap;
import java.util.Map;

public class StringLexStatemachine extends LexStatementMachine{

    private static final Map<Integer,Boolean> staticFinalStateMap;
    private static final LexStateHandler[] lexStateHandlers;

    static{
        staticFinalStateMap = new HashMap<>();
        staticFinalStateMap.put(-1,true);
        staticFinalStateMap.put(1,false);
        staticFinalStateMap.put(2,true);
        staticFinalStateMap.put(3,false);
        staticFinalStateMap.put(4,false);
        staticFinalStateMap.put(5,false);
        staticFinalStateMap.put(6,false);
        staticFinalStateMap.put(7,false);

        lexStateHandlers = new LexStateHandler[]{
            new State0Handler(),new State1Handler(),new State2Handler(),new State3Handler(),new State4Handler(),
            new State5Handler(),new State6Handler(),new State7Handler()};
    }

    public StringLexStatemachine() {
        this.stateHandlers = lexStateHandlers;
        this.isFinalStateMap = staticFinalStateMap;
    }

    private static abstract class StringLexStateHandler implements LexStateHandler {

        @Override
        public int processInState(char[] chars, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            char currentChar = chars[doLexContext.currentIndex];

            return doProcessInState(currentChar,doLexContext,oneTokenAcceptResult);
        }

        abstract int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult);
    }

    private static class State0Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(currentChar == '"'){
                // accept
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                // 进入状态1
                return 1;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State1Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(currentChar == '"'){
                // accept
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                // 进入状态2
                return 2;
            }

            if(currentChar == '\\'){
                // accept
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                // 进入状态3
                return 3;
            }

            // 除了["]和[\]两个字符，别的都当做字符串的一部分接收
            // accept
            accept(currentChar,doLexContext,oneTokenAcceptResult);
            return 1;
        }
    }

    private static class State2Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.isWhitespace(currentChar)
                || currentChar == ']' || currentChar == '}' || currentChar == ',' || currentChar == ':'){
                // 合法的，但是不accept，直接返回
                return -1;
            }

            throw new MuJsonParserException("after a string parse，unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State3Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            // 合法的转义字符
            if(currentChar == '"' || currentChar == '\\' || currentChar == '/' ||
                currentChar == 'b' || currentChar == 'f' || currentChar == 'n' ||
                currentChar == 'r' || currentChar == 't'){
                // 接收，回到状态1
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 1;
            }

            if(currentChar == 'u'){
                // 特殊case 要求后面连续4个hex字符 '\\u hex hex hex hex'
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 4;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State4Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.isHex(currentChar)){
                // 接收，进入状态5
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 5;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State5Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.isHex(currentChar)){
                // 接收，进入状态6
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 6;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State6Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.isHex(currentChar)){
                // 接收，进入状态7
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 7;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State7Handler extends StringLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.isHex(currentChar)){
                // 连续接收了4个hex字符，回到状态1
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 1;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    public static void main(String[] args) {
        StringLexStatemachine statemachine = new StringLexStatemachine();

        String s = "\"\\'单引号\\' \\/斜杠\"";
        System.out.println(s);
        String result = statemachine.tryParse(s.toCharArray(),new DoLexContext());
        System.out.println(result);
    }
}
