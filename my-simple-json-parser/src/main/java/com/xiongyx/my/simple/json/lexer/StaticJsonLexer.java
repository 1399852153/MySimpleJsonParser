package com.xiongyx.my.simple.json.lexer;

import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.DoLexContext;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.lexer.statemachine.*;
import com.xiongyx.my.simple.json.util.CommonStringUtil;

import java.util.List;

public class StaticJsonLexer {

    /**
     * 一次完整的扫描，非流式的处理
     * */
    public List<JsonToken> doLex(String jsonStr){
        DoLexContext doLexContext = new DoLexContext();
        char[] chars = jsonStr.toCharArray();

        // 相当于是状态0
        while(doLexContext.currentIndex < chars.length){
            char ch = chars[doLexContext.currentIndex];

            switch(ch){
                case '{':
                    doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.LEFT_BRACE));
                    doLexContext.currentIndex++;
                    break;
                case '}':
                    doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.RIGHT_BRACE));
                    doLexContext.currentIndex++;
                    break;
                case '[':
                    doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.LEFT_BRACKET));
                    doLexContext.currentIndex++;
                    break;
                case ']':
                    doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.RIGHT_BRACKET));
                    doLexContext.currentIndex++;
                    break;
                case ',':
                    doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.COMMA));
                    doLexContext.currentIndex++;
                    break;
                case ':':
                    doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.COLON));
                    doLexContext.currentIndex++;
                    break;
                case '"':
                    parseString(chars, doLexContext);
                    break;
                case 't':
                    // 尝试解析true关键字
                    parseTrueKeyword(chars, doLexContext);
                    break;
                case 'f':
                    // 尝试解析false关键字
                    parseFalseKeyword(chars, doLexContext);
                    break;
                case 'n':
                    // 尝试解析null关键字
                    parseNullKeyword(chars, doLexContext);
                    break;
                default:
                    // 其它case
                    if(ch == '-' || CommonStringUtil.is0_9(ch)){
                        // number解析
                        parseNumber(chars, doLexContext);
                        break;
                    }else if(CommonStringUtil.isWhitespace(ch)){
                        // whiteSpace 直接跳过
                        doLexContext.currentIndex++;
                        break;
                    }else{
                        throw new RuntimeException("unexpected character: " + ch + " at index " + doLexContext.currentIndex);
                    }
            }
        }

        // 最后加上EOF
        doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.EOF));
        return doLexContext.tokenCollector;
    }

    private void parseNumber(char[] chars, DoLexContext doLexContext){
        // number类型的内容
        String numberStr = new NumberLexStatemachine().tryParse(chars,doLexContext);

        doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.NUMBER, numberStr));
    }

    private void parseString(char[] chars, DoLexContext doLexContext){
        // string类型的内容
        String stringStr = new StringLexStatemachine().tryParse(chars,doLexContext);

        doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.STRING, stringStr));
    }

    private void parseTrueKeyword(char[] chars, DoLexContext doLexContext){
        // true关键字
        String stringStr = new KeywordTrueLexStatementMachine().tryParse(chars,doLexContext);

        doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.TRUE, stringStr));
    }

    private void parseFalseKeyword(char[] chars, DoLexContext doLexContext){
        // false关键字
        String stringStr = new KeywordFalseLexStatementMachine().tryParse(chars,doLexContext);

        doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.FALSE, stringStr));
    }

    private void parseNullKeyword(char[] chars, DoLexContext doLexContext){
        // null关键字
        String stringStr = new KeywordNullLexStatementMachine().tryParse(chars,doLexContext);

        doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.NULL, stringStr));
    }

    public static void main(String[] args) {
        String json = "{\"a\": true}";

        StaticJsonLexer staticJsonLexer = new StaticJsonLexer();
        List<JsonToken> jsonTokenList = staticJsonLexer.doLex(json);
        System.out.println(jsonTokenList);
    }
}
