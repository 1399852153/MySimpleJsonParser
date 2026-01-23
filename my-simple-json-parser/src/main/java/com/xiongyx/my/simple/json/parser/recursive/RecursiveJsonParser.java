package com.xiongyx.my.simple.json.parser.recursive;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.parser.JsonParser;
import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.model.JsonPrimitiveStr;
import com.xiongyx.my.simple.json.parser.reader.JsonTokenReader;
import com.xiongyx.my.simple.json.parser.recursive.statemachine.JsonArrayParseStatementMachine;
import com.xiongyx.my.simple.json.parser.recursive.statemachine.JsonObjectParseStatementMachine;

/**
 * 基于递归的json解析器
 * */
public class RecursiveJsonParser extends JsonParser {

    public RecursiveJsonParser(JsonTokenReader tokenReader) {
        super(tokenReader);
    }

    @Override
    public JsonElement doParse() {
        JsonToken token = jsonTokenReader.peek();

        if (token.getType() == JsonTokenTypeEnum.LEFT_BRACE) {
            JsonObjectParseStatementMachine jsonObjectParseStatementMachine = new JsonObjectParseStatementMachine(jsonTokenReader);

            return jsonObjectParseStatementMachine.parseJsonElement();
        }

        if (token.getType() == JsonTokenTypeEnum.LEFT_BRACKET) {
            JsonArrayParseStatementMachine jsonArrayParseStatementMachine = new JsonArrayParseStatementMachine(jsonTokenReader);

            return jsonArrayParseStatementMachine.parseJsonElement();
        }

        // 基础类型的value
        if (token.getType().isPrimitiveValue()) {
            return new JsonPrimitiveStr(token.getContent());
        }

        // 第一个token，不属于json规则的f(1)集合
        throw new MuJsonParserException("unexpected start json token! token=" + jsonTokenReader.currentIndex());
    }
}
