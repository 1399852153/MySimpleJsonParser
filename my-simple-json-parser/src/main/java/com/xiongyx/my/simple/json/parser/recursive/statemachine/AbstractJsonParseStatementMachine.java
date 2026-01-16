package com.xiongyx.my.simple.json.parser.recursive.statemachine;

import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.reader.JsonTokenReader;
import com.xiongyx.my.simple.json.parser.recursive.RecursiveDoParserContext;

public class AbstractJsonParseStatementMachine<T extends JsonElement> {

    protected JsonTokenReader jsonTokenReader;

    protected RecursiveDoParserContext<T> recursiveDoParserContext;

    protected int currentState = 0;

    protected T targetJsonElement;

    protected ParserStateHandler[] stateHandlers;

    public T parseJsonElement(){
        while(jsonTokenReader.hasNextToken()){
            if(currentState == -1){
                // 遇到了合法的分隔符号，退出token解析
                return targetJsonElement;
            }

            ParserStateHandler targetStateHandler = stateHandlers[currentState];
            if(currentState >= stateHandlers.length){
                // 有bug
                throw new RuntimeException(String.format("unknown state! currentState=%s",currentState));
            }

            currentState = targetStateHandler.processInState(jsonTokenReader, recursiveDoParserContext);
        }

        return targetJsonElement;
    }

    protected static void accept(JsonTokenReader jsonTokenReader){
        jsonTokenReader.nextToken();
    }

}
