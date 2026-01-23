package com.xiongyx.my.simple.json.lexer;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.util.CommonStringUtil;

public class StreamJsonLexer extends AbstractJsonLexer{

    public StreamJsonLexer(String jsonString) {
        super(jsonString);
    }

    public JsonToken doLex(){
        if(doLexContext.currentIndex >= jsonStringArray.length){
            return new JsonToken(JsonTokenTypeEnum.EOF);
        }

        while(true) {
            char ch = jsonStringArray[doLexContext.currentIndex];

            // 每一次尝试解析一个完整的token前，都是状态0
            switch (ch) {
                case '{':
                    doLexContext.currentIndex++;
                    return new JsonToken(JsonTokenTypeEnum.LEFT_BRACE);
                case '}':
                    doLexContext.currentIndex++;
                    return new JsonToken(JsonTokenTypeEnum.RIGHT_BRACE);
                case '[':
                    doLexContext.currentIndex++;
                    return new JsonToken(JsonTokenTypeEnum.LEFT_BRACKET);
                case ']':
                    doLexContext.currentIndex++;
                    return new JsonToken(JsonTokenTypeEnum.RIGHT_BRACKET);
                case ',':
                    doLexContext.currentIndex++;
                    return new JsonToken(JsonTokenTypeEnum.COMMA);
                case ':':
                    doLexContext.currentIndex++;
                    return new JsonToken(JsonTokenTypeEnum.COLON);
                case '"':
                    return parseString(jsonStringArray, doLexContext);
                case 't':
                    // 尝试解析true关键字
                    return parseTrueKeyword(jsonStringArray, doLexContext);
                case 'f':
                    // 尝试解析false关键字
                    return parseFalseKeyword(jsonStringArray, doLexContext);
                case 'n':
                    // 尝试解析null关键字
                    return parseNullKeyword(jsonStringArray, doLexContext);
                default:
                    // 走其它case
                    break;
            }

            // 其它case
            if (CommonStringUtil.is0_9(ch) || ch == '-') {
                // number解析
                return parseNumber(jsonStringArray, doLexContext);
            } else if (CommonStringUtil.isWhitespace(ch)) {
                // whiteSpace 直接跳过
                doLexContext.currentIndex++;
            } else{
                throw new MuJsonParserException("unexpected character: " + ch + ",charIndex=" + doLexContext.currentIndex);
            }
        }
    }
}
