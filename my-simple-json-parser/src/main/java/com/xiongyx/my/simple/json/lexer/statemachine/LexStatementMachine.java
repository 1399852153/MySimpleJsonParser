package com.xiongyx.my.simple.json.lexer.statemachine;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.model.DoLexContext;

import java.util.Map;

public abstract class LexStatementMachine {

    protected int currentState = 0;
    protected StringBuilder oneTokenAcceptResult = new StringBuilder();

    protected LexStateHandler[] stateHandlers;
    protected Map<Integer,Boolean> isFinalStateMap;

    public String tryParse(char[] chars, DoLexContext doLexContext){
        doParse(chars,doLexContext);

        boolean isFinalState = isFinalStateMap.get(currentState);
        if(isFinalState){
            return oneTokenAcceptResult.toString();
        }else{
            throw new MuJsonParserException(String.format("currentState is not finalState! acceptResult=%s, acceptResult=%s",currentState, oneTokenAcceptResult));
        }
    }

    protected static void accept(char currentChar, DoLexContext doLexContext, StringBuilder oneTokenAcceptResult){
        oneTokenAcceptResult.append(currentChar);
        doLexContext.currentIndex++;
    }

    private void doParse(char[] chars, DoLexContext doLexContext){
        // 一进来是状态0
        while(doLexContext.currentIndex < chars.length){
            if(currentState == -1){
                // 遇到了合法的分隔符号，退出token解析
                return;
            }

            LexStateHandler targetStateHandler = stateHandlers[currentState];
            if(currentState >= stateHandlers.length){
                // 有bug
                throw new MuJsonParserException(String.format("unknown state! currentState=%s",currentState));
            }

            currentState = targetStateHandler.processInState(chars,doLexContext,oneTokenAcceptResult);
        }
    }

    public static void main(String[] args) {
        String s = "-12";
        String result = new NumberLexStatemachine().tryParse(s.toCharArray(),new DoLexContext());
        System.out.println(result);
    }
}
