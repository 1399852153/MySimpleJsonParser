package com.xiongyx.my.simple.json.parser.recursive.statemachine;

import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.parser.model.JsonArray;
import com.xiongyx.my.simple.json.parser.model.JsonObject;
import com.xiongyx.my.simple.json.parser.model.JsonPrimitiveStr;
import com.xiongyx.my.simple.json.parser.reader.JsonTokenReader;
import com.xiongyx.my.simple.json.parser.recursive.RecursiveDoParserContext;

public class JsonArrayParseStatementMachine extends AbstractJsonParseStatementMachine<JsonArray> {

    public JsonArrayParseStatementMachine(JsonTokenReader jsonTokenReader) {
        this.jsonTokenReader = jsonTokenReader;
        this.targetJsonElement = new JsonArray();
        this.recursiveDoParserContext = new RecursiveDoParserContext<>(this.targetJsonElement);
        stateHandlers = new ParserStateHandler[]{
            new ParserState0Handler(),new ParserState1Handler(),new ParserState2Handler(),
            new ParserState3Handler(), new ParserState4Handler()
        };
    }

    private static class ParserState0Handler implements ParserStateHandler<JsonArray>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonArray> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() != JsonTokenTypeEnum.LEFT_BRACKET){
                throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
            }

            accept(jsonTokenReader);
            return 1;
        }
    }

    private static class ParserState1Handler implements ParserStateHandler<JsonArray>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonArray> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() == JsonTokenTypeEnum.RIGHT_BRACKET){
                accept(jsonTokenReader);
                return 2;
            }

            // 嵌套的jsonObject结构
            if(token.getType() == JsonTokenTypeEnum.LEFT_BRACE){
                JsonObjectParseStatementMachine jsonObjectParseStatementMachine = new JsonObjectParseStatementMachine(jsonTokenReader);

                JsonObject subJsonObject = jsonObjectParseStatementMachine.parseJsonElement();

                // add一个obj
                recursiveDoParserContext.getTargetJsonElement().addElement(subJsonObject);

                return 3;
            }

            // 嵌套的jsonArray结构
            if(token.getType() == JsonTokenTypeEnum.LEFT_BRACKET){
                // jsonArray状态机
                JsonArrayParseStatementMachine jsonArrayParseStatementMachine = new JsonArrayParseStatementMachine(jsonTokenReader);

                JsonArray jsonArray = jsonArrayParseStatementMachine.parseJsonElement();

                // add一个array
                recursiveDoParserContext.getTargetJsonElement().addElement(jsonArray);

                return 3;
            }

            // 基础类型的value
            if(token.getType().isPrimitiveValue()){
                accept(jsonTokenReader);
                recursiveDoParserContext.getTargetJsonElement().addElement(new JsonPrimitiveStr(token.getContent()));

                return 3;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private static class ParserState2Handler implements ParserStateHandler<JsonArray>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonArray> recursiveDoParserContext) {
            // 终态，直接返回
            return -1;
        }
    }

    private static class ParserState3Handler implements ParserStateHandler<JsonArray>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonArray> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() == JsonTokenTypeEnum.RIGHT_BRACKET){
                accept(jsonTokenReader);
                return 2;
            }

            if(token.getType() == JsonTokenTypeEnum.COMMA){
                accept(jsonTokenReader);
                return 4;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private static class ParserState4Handler implements ParserStateHandler<JsonArray>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonArray> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            // 嵌套的jsonObject结构
            if(token.getType() == JsonTokenTypeEnum.LEFT_BRACE){
                JsonObjectParseStatementMachine jsonObjectParseStatementMachine = new JsonObjectParseStatementMachine(jsonTokenReader);

                JsonObject subJsonObject = jsonObjectParseStatementMachine.parseJsonElement();

                // add一个obj
                recursiveDoParserContext.getTargetJsonElement().addElement(subJsonObject);

                return 3;
            }

            // 嵌套的jsonArray结构
            if(token.getType() == JsonTokenTypeEnum.LEFT_BRACKET){
                // jsonArray状态机
                JsonArrayParseStatementMachine jsonArrayParseStatementMachine = new JsonArrayParseStatementMachine(jsonTokenReader);

                JsonArray jsonArray = jsonArrayParseStatementMachine.parseJsonElement();

                // add一个array
                recursiveDoParserContext.getTargetJsonElement().addElement(jsonArray);

                return 3;
            }

            // 基础类型的value
            if(token.getType().isPrimitiveValue()){
                accept(jsonTokenReader);
                recursiveDoParserContext.getTargetJsonElement().addElement(new JsonPrimitiveStr(token.getContent()));

                return 3;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }
}
