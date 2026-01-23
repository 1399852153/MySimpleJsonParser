package com.xiongyx.my.simple.json.lexer.statemachine;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.model.DoLexContext;
import com.xiongyx.my.simple.json.util.CommonStringUtil;

import java.util.HashMap;
import java.util.Map;

public class NumberLexStatemachine extends LexStatementMachine{

    private static final Map<Integer,Boolean> staticFinalStateMap;
    private static final LexStateHandler[] lexStateHandlers;

    static{
        staticFinalStateMap = new HashMap<>();
        staticFinalStateMap.put(-1,true);
        staticFinalStateMap.put(1,true);
        staticFinalStateMap.put(2,false);
        staticFinalStateMap.put(3,true);
        staticFinalStateMap.put(4,true);
        staticFinalStateMap.put(5,false);
        staticFinalStateMap.put(6,true);
        staticFinalStateMap.put(7,false);
        staticFinalStateMap.put(8,false);
        staticFinalStateMap.put(9,true);

        lexStateHandlers = new LexStateHandler[]{
            new State0Handler(), new State1Handler(), new State2Handler(), new State3Handler(), new State4Handler(),
            new State5Handler(),new State6Handler(),new State7Handler(),new State8Handler(),new State9Handler()
        };
    }

    public NumberLexStatemachine() {
        this.stateHandlers = lexStateHandlers;
        this.isFinalStateMap = staticFinalStateMap;
    }

    private static abstract class NumberLexStateHandler implements LexStateHandler {

        @Override
        public int processInState(char[] chars, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            char currentChar = chars[doLexContext.currentIndex];

            // whitespace符号以及number后合法的终结符
            if(CommonStringUtil.isWhitespace(currentChar)
                || currentChar == ']' || currentChar == '}' || currentChar == ',' || currentChar == ':'){
                // 结束number的解析
                return -1;
            }

            return doProcessInState(currentChar,doLexContext, oneTokenAcceptResult);
        }

        abstract int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult);
    }

    private static class State0Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(currentChar == '0'){
                // accept
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                // 进入状态1
                return 1;
            }

            if(currentChar == '-'){
                // accept
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                // 进入状态2
                return 2;
            }

            if(CommonStringUtil.is1_9(currentChar)){
                // accept
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                // 进入状态3
                return 3;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State1Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(currentChar == '.'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 5;
            }

            if(currentChar == 'e' || currentChar == 'E'){
                accept(currentChar,doLexContext, oneTokenAcceptResult);
                return 7;
            }

            throw new MuJsonParserException("unexpected char '" + currentChar + "', index=" + doLexContext.currentIndex);
        }
    }

    private static class State2Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(currentChar == '0'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 1;
            }

            if(CommonStringUtil.is1_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 3;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State3Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(currentChar == '.'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 5;
            }

            if(currentChar == 'e' || currentChar == 'E'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 7;
            }

            if(CommonStringUtil.is0_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 4;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State4Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext,StringBuilder oneTokenAcceptResult) {
            if(currentChar == '.'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 5;
            }

            if(currentChar == 'e' || currentChar == 'E'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 7;
            }

            if(CommonStringUtil.is0_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 4;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State5Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.is0_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 6;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State6Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext,StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.is0_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 6;
            }

            if(currentChar == 'e' || currentChar == 'E'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 7;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State7Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext,StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.is0_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 9;
            }

            if(currentChar == '-' || currentChar == '+'){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 8;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State8Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.is0_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 9;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }

    private static class State9Handler extends NumberLexStateHandler {
        @Override
        int doProcessInState(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult) {
            if(CommonStringUtil.is0_9(currentChar)){
                accept(currentChar,doLexContext,oneTokenAcceptResult);
                return 9;
            }

            throw new MuJsonParserException("unexpected char " + currentChar + " " + doLexContext.currentIndex);
        }
    }
}
