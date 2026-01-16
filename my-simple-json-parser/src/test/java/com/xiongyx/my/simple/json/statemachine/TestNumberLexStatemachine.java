package com.xiongyx.my.simple.json.statemachine;

import com.xiongyx.my.simple.json.lexer.model.DoLexContext;
import com.xiongyx.my.simple.json.lexer.statemachine.NumberLexStatemachine;
import com.xiongyx.my.simple.json.util.JackSonUtil;
import org.junit.Test;

public class TestNumberLexStatemachine {

    private static final char[] randomStrChars = {'0','1','2','3','4','5','6','7','8','9',
                                     '0','1','2','3','4','5','6','7','8','9',
                                     '0','1','2','3','4','5','6','7','8','9',
                                     'e','E', '-','+','.','a','b'};

    @Test
    public void testNumberLexStatemachine(){
        for(int i=0; i<200000; i++) {
            String randomNumberStr = generateRandomString((int)(Math.random()*10));
            String jsonTemplate = "{\"number\":%s}";
            String finallyJson = String.format(jsonTemplate, randomNumberStr);

            boolean jacksonParseResult = jacksonParse(finallyJson);
            boolean numberLexParse = numberLexParse(randomNumberStr);
            if(jacksonParseResult != numberLexParse){
                throw new RuntimeException(
                    String.format("parse result not match! randomNumberStr=%s,finallyJson=%s,jacksonParseResult=%s,numberLexParse=%s",
                        randomNumberStr,finallyJson,jacksonParseResult,numberLexParse));
            }
        }
    }

    private static boolean jacksonParse(String finallyJson){
        try {
            JackSonUtil.string2Obj(finallyJson,Object.class);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static boolean numberLexParse(String numberStr){
        try {
            new NumberLexStatemachine().tryParse(numberStr.toCharArray(),new DoLexContext());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static String generateRandomString(int length){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            sb.append(randomStrChars[(int)(Math.random()*randomStrChars.length)]);
        }

        return sb.toString();
    }
}
