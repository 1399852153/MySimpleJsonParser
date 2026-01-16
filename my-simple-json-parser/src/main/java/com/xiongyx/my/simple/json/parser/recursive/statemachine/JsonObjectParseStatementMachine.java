package com.xiongyx.my.simple.json.parser.recursive.statemachine;

import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.parser.model.JsonArray;
import com.xiongyx.my.simple.json.parser.model.JsonObject;
import com.xiongyx.my.simple.json.parser.model.JsonPrimitiveStr;
import com.xiongyx.my.simple.json.parser.reader.JsonTokenReader;
import com.xiongyx.my.simple.json.parser.recursive.RecursiveDoParserContext;
import com.xiongyx.my.simple.json.util.Assert;

public class JsonObjectParseStatementMachine extends AbstractJsonParseStatementMachine<JsonObject>{

    public JsonObjectParseStatementMachine(JsonTokenReader jsonTokenReader) {
        this.jsonTokenReader = jsonTokenReader;
        this.targetJsonElement = new JsonObject();
        this.recursiveDoParserContext = new RecursiveDoParserContext<>(this.targetJsonElement);
        stateHandlers = new ParserStateHandler[]{
            new ParserState0Handler(),new ParserState1Handler(),new ParserState2Handler(),new ParserState3Handler(),
            new ParserState4Handler(),new ParserState5Handler(),new ParserState6Handler()
        };
    }

    private static class ParserState0Handler implements ParserStateHandler<JsonObject>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonObject> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() != JsonTokenTypeEnum.LEFT_BRACE){
                throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
            }

            accept(jsonTokenReader);
            return 1;
        }
    }

    private static class ParserState1Handler implements ParserStateHandler<JsonObject>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonObject> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() == JsonTokenTypeEnum.RIGHT_BRACE){
                accept(jsonTokenReader);
                return 2;
            }

            if(token.getType() == JsonTokenTypeEnum.STRING){
                // 把key先压入栈中，然后等构造kv对时弹出
                recursiveDoParserContext.getTokenStack().push(token);
                accept(jsonTokenReader);
                return 3;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private static class ParserState2Handler implements ParserStateHandler<JsonObject>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonObject> recursiveDoParserContext) {
            // 终态，直接返回
            return -1;
        }
    }

    private static class ParserState3Handler implements ParserStateHandler<JsonObject>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonObject> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() == JsonTokenTypeEnum.COLON){
                accept(jsonTokenReader);
                return 4;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private static class ParserState4Handler implements ParserStateHandler<JsonObject>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonObject> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            JsonToken keyToken = recursiveDoParserContext.getTokenStack().pop();
            Assert.assertTrue(keyToken != null && keyToken.getType() == JsonTokenTypeEnum.STRING,"parse object keyToken not match!");

            // 嵌套的jsonObject结构
            if(token.getType() == JsonTokenTypeEnum.LEFT_BRACE){
                JsonObjectParseStatementMachine jsonObjectParseStatementMachine = new JsonObjectParseStatementMachine(jsonTokenReader);

                JsonObject subJsonObject = jsonObjectParseStatementMachine.parseJsonElement();

                // 构造好了一个kv对（key : obj）
                recursiveDoParserContext.getTargetJsonElement().putKey(keyToken.getContent(), subJsonObject);

                return 5;
            }

            // 嵌套的jsonArray结构
            if(token.getType() == JsonTokenTypeEnum.LEFT_BRACKET){
                // jsonArray状态机
                JsonArrayParseStatementMachine jsonArrayParseStatementMachine = new JsonArrayParseStatementMachine(jsonTokenReader);

                JsonArray jsonArray = jsonArrayParseStatementMachine.parseJsonElement();
                // 构造好了一个kv对 (key ：array)
                recursiveDoParserContext.getTargetJsonElement().putKey(keyToken.getContent(), jsonArray);

                return 5;
            }

            // 基础类型的value
            if(token.getType().isPrimitiveValue()){
                accept(jsonTokenReader);
                recursiveDoParserContext.getTargetJsonElement().putKey(keyToken.getContent(), new JsonPrimitiveStr(token.getContent()));

                return 5;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private static class ParserState5Handler implements ParserStateHandler<JsonObject>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonObject> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() == JsonTokenTypeEnum.RIGHT_BRACE){
                accept(jsonTokenReader);
                return 2;
            }

            if(token.getType() == JsonTokenTypeEnum.COMMA){
                accept(jsonTokenReader);
                return 6;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private static class ParserState6Handler implements ParserStateHandler<JsonObject>{

        @Override
        public int processInState(JsonTokenReader jsonTokenReader, RecursiveDoParserContext<JsonObject> recursiveDoParserContext) {
            JsonToken token = jsonTokenReader.peek();

            if(token.getType() == JsonTokenTypeEnum.STRING){
                // 把key先压入栈中，然后等构造kv对时弹出
                recursiveDoParserContext.getTokenStack().push(token);
                accept(jsonTokenReader);
                return 3;
            }

            throw new RuntimeException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }
}
