package com.xiongyx.my.simple.json.statemachine;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.model.DoLexContext;
import com.xiongyx.my.simple.json.lexer.statemachine.StringLexStatemachine;
import com.xiongyx.my.simple.json.util.JackSonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestStringLexStatemachine {

    private static List<String> randomStrChars = new ArrayList<>();

    static {
        for(int i='a';i<'z'; i++){
            randomStrChars.add(String.valueOf(i));
        }

        for(int i='A';i<'Z'; i++){
            randomStrChars.add(String.valueOf(i));
        }

        for(int i='0';i<'9'; i++){
            randomStrChars.add(String.valueOf(i));
        }

        randomStrChars.add("\\u");
        randomStrChars.add("\\");
    }

    @Test
    public void testStringLexStatemachine(){
        for(int i=0; i<200000; i++) {
            String randomNumberStr = generateRandomString((int)(Math.random()*50));
            String jsonTemplate = "{\"string\":%s}";
            String finallyJson = String.format(jsonTemplate, randomNumberStr);

            boolean jacksonParseResult = jacksonParse(finallyJson);
            boolean stringLexParseResult = stringLexParse(randomNumberStr);
            if(jacksonParseResult != stringLexParseResult){
                throw new MuJsonParserException(
                    String.format("parse result not match! randomNumberStr=%s,finallyJson=%s,jacksonParseResult=%s,stringLexParseResult=%s",
                        randomNumberStr,finallyJson,jacksonParseResult,stringLexParseResult));
            }else{
//                System.out.printf("randomNumberStr=%s, jacksonParseResult=%s, stringLexParseResult=%s%n", randomNumberStr, jacksonParseResult, stringLexParseResult);
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

    private static boolean stringLexParse(String numberStr){
        try {
            new StringLexStatemachine().tryParse(numberStr.toCharArray(),new DoLexContext());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static String generateRandomString(int length){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            int targetIndex = (int) (Math.random()*randomStrChars.size());
            sb.append(randomStrChars.get(targetIndex));
        }

        return "\""+ sb + "\"";
    }
}
