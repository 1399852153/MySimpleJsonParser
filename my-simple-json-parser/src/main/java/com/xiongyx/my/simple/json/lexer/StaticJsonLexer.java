package com.xiongyx.my.simple.json.lexer;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.util.CommonStringUtil;

import java.util.List;

public class StaticJsonLexer extends AbstractJsonLexer{

    public StaticJsonLexer(String jsonString) {
        super(jsonString);
    }

    /**
     * 一次完整的扫描，非流式的处理
     * */
    public List<JsonToken> doLex(){
        char[] chars = super.jsonStringArray;

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
                    doLexContext.tokenCollector.add(parseString(chars, doLexContext));
                    break;
                case 't':
                    // 尝试解析true关键字
                    doLexContext.tokenCollector.add(parseTrueKeyword(chars, doLexContext));
                    break;
                case 'f':
                    // 尝试解析false关键字
                    doLexContext.tokenCollector.add(parseFalseKeyword(chars, doLexContext));
                    break;
                case 'n':
                    // 尝试解析null关键字
                    doLexContext.tokenCollector.add(parseNullKeyword(chars, doLexContext));
                    break;
                default:
                    // 其它case
                    if(ch == '-' || CommonStringUtil.is0_9(ch)){
                        // number解析
                        JsonToken numberToken = parseNumber(chars, doLexContext);
                        doLexContext.tokenCollector.add(numberToken);
                        break;
                    }else if(CommonStringUtil.isWhitespace(ch)){
                        // whiteSpace 直接跳过
                        doLexContext.currentIndex++;
                        break;
                    }else{
                        throw new MuJsonParserException("unexpected character: " + ch + " at index " + doLexContext.currentIndex);
                    }
            }
        }

        // 最后加上EOF
        doLexContext.tokenCollector.add(new JsonToken(JsonTokenTypeEnum.EOF));
        return doLexContext.tokenCollector;
    }

    public static void main(String[] args) {
        String json = "{\"a\": true}";

        StaticJsonLexer staticJsonLexer = new StaticJsonLexer(json);
        List<JsonToken> jsonTokenList = staticJsonLexer.doLex();
        System.out.println(jsonTokenList);
    }
}
