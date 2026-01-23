package com.xiongyx.my.simple.json.parser.stackbase;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import com.xiongyx.my.simple.json.parser.JsonParser;
import com.xiongyx.my.simple.json.parser.model.JsonArray;
import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.model.JsonObject;
import com.xiongyx.my.simple.json.parser.model.JsonPrimitiveStr;
import com.xiongyx.my.simple.json.parser.reader.JsonTokenReader;
import com.xiongyx.my.simple.json.parser.stackbase.enums.StackBaseJsonParserStatusEnum;
import com.xiongyx.my.simple.json.parser.stackbase.stack.JsonParseStack;
import com.xiongyx.my.simple.json.parser.stackbase.stack.JsonParseStackValue;
import com.xiongyx.my.simple.json.parser.stackbase.stack.JsonParseStackValueTypeEnum;
import com.xiongyx.my.simple.json.util.Assert;

/**
 * 基于栈的，非递归的json解析器
 * */
public class StackBaseJsonParser extends JsonParser {

    private final JsonParseStack parseStack = new JsonParseStack();

    private StackBaseJsonParserStatusEnum currentStatus;

    public StackBaseJsonParser(JsonTokenReader tokenReader) {
        super(tokenReader);

        this.currentStatus = StackBaseJsonParserStatusEnum.START_PARSE;
    }

    private void accept(){
        jsonTokenReader.nextToken();
    }

    @Override
    public JsonElement doParse() {
        while(jsonTokenReader.hasNextToken()){
            JsonToken token = jsonTokenReader.peek();

            if(currentStatus == StackBaseJsonParserStatusEnum.END_PARSE){
                break;
            }

            switch (currentStatus){
                case START_PARSE:
                    processInStartParse(token);
                    break;
                case PARSE_OBJECT_0:
                    processInParseObject0(token);
                    break;
                case PARSE_OBJECT_1:
                    processInParseObject1(token);
                    break;
                case PARSE_OBJECT_2:
                    processInParseObject2(token);
                    break;
                case PARSE_OBJECT_3:
                    processInParseObject3(token);
                    break;
                case PARSE_OBJECT_4:
                    processInParseObject4(token);
                    break;
                case PARSE_OBJECT_5:
                    processInParseObject5(token);
                    break;
                case PARSE_OBJECT_6:
                    processInParseObject6(token);
                    break;
                case PARSE_ARR_0:
                    processInParseArr0(token);
                    break;
                case PARSE_ARR_1:
                    processInParseArr1(token);
                    break;
                case PARSE_ARR_2:
                    processInParseArr2(token);
                    break;
                case PARSE_ARR_3:
                    processInParseArr3(token);
                    break;
                default:
                    throw new MuJsonParserException("Unexpected currentStatus: " + currentStatus);
            }
        }

        // 如果json字符串是合法的，那么最后栈顶必然是有且唯一的一个JsonElement类型的对象
        if(this.parseStack.size() != 1){
            throw new MuJsonParserException("after parse，stack element size > 1! stack=" + this.parseStack);
        }

        JsonParseStackValue object = this.parseStack.pop();
        return (JsonElement) object.getValue();
    }

    private void processInStartParse(JsonToken token){
        if (token.getType() == JsonTokenTypeEnum.LEFT_BRACE) {
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_0;
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_OBJECT,new JsonObject()));
            return;
        }

        if (token.getType() == JsonTokenTypeEnum.LEFT_BRACKET) {
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_0;
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_ARRAY,new JsonArray()));
            return;
        }

        if (token.getType().isPrimitiveValue()) {
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_0;
            accept();
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_PRIMITIVE,new JsonPrimitiveStr(token.getContent())));
            return;
        }

        // 第一个token，不属于json规则的f(1)集合
        throw new MuJsonParserException("unexpected start json token! token=" + jsonTokenReader.currentIndex());
    }

    private void processInParseObject0(JsonToken token){
        if(token.getType() != JsonTokenTypeEnum.LEFT_BRACE){
            throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }

        accept();

        this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_1;
    }

    private void processInParseObject1(JsonToken token){
        if(token.getType() == JsonTokenTypeEnum.RIGHT_BRACE){
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_2;
            return;
        }

        if(token.getType() == JsonTokenTypeEnum.STRING){
            // 把key先压入栈中，然后等构造kv对时弹出
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_KEY,token));
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_3;
            return;
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }

    private void processInParseObject2(JsonToken token){
        // 遇到'}'才会进来
        if(token.getType() != JsonTokenTypeEnum.RIGHT_BRACE){
            throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }else{
            accept();
        }

        // 当前栈顶必定是JsonObject，先将其弹出，然后看栈顶的元素类型判断
        JsonParseStackValue currentJsonObjectStackValue = this.parseStack.popAndCheck(JsonParseStackValueTypeEnum.JSON_OBJECT);
        if(this.parseStack.isEmpty()){
            // 说明是root的JsonObject解析完了，再推回去直接返回
            this.parseStack.push(currentJsonObjectStackValue);
            this.currentStatus = StackBaseJsonParserStatusEnum.END_PARSE;
            return;
        }

        JsonObject currentJsonObject = (JsonObject) currentJsonObjectStackValue.getValue();

        JsonParseStackValueTypeEnum topObjType = this.parseStack.peekTopType();

        if(topObjType == JsonParseStackValueTypeEnum.JSON_KEY){
            // 如果是json_key，说明是当前jsonObject是父object的一个k/v项中的value。
            JsonParseStackValue keyStackValue = this.parseStack.popAndCheck(JsonParseStackValueTypeEnum.JSON_KEY);
            JsonToken keyJsonToken = (JsonToken) keyStackValue.getValue();
            JsonParseStackValue parentObject = this.parseStack.peekAndCheck(JsonParseStackValueTypeEnum.JSON_OBJECT);

            // 将当前k/v项附加在父object上
            ((JsonObject)parentObject.getValue()).putKey(keyJsonToken.getContent(), currentJsonObject);

            // 基于下一个token判断状态跳转
            JsonToken nextJsonToken = this.jsonTokenReader.peek();
            if(nextJsonToken.getType() == JsonTokenTypeEnum.COMMA){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_5;
                return;
            }else if (nextJsonToken.getType() == JsonTokenTypeEnum.RIGHT_BRACE){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_2;
                return;
            }else{
                throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex()+1);
            }

        }else if(topObjType == JsonParseStackValueTypeEnum.JSON_ARRAY){
            // 说明当前jsonObject是jsonArray的一个元素

            JsonParseStackValue parentArr = this.parseStack.peekAndCheck(JsonParseStackValueTypeEnum.JSON_ARRAY);
            ((JsonArray)parentArr.getValue()).addElement(currentJsonObject);

            // 基于下一个token判断状态跳转
            JsonToken nextJsonToken = this.jsonTokenReader.peek();
            if(nextJsonToken.getType() == JsonTokenTypeEnum.COMMA){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_3;
                return;
            }else if (nextJsonToken.getType() == JsonTokenTypeEnum.RIGHT_BRACKET){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_2;
                return;
            }else{
                throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex()+1);
            }
        }else{
            // 别的情况都说明有问题，不是合法的json
            throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private void processInParseObject3(JsonToken token){
        if(token.getType() == JsonTokenTypeEnum.COLON){
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_4;
            return;
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }

    private void processInParseObject4(JsonToken token){
        // 嵌套的jsonObject结构
        if(token.getType() == JsonTokenTypeEnum.LEFT_BRACE){
            // 发现'{'，栈上推进一个JsonObject
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_OBJECT, new JsonObject()));
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_1;
            return;
        }

        // 嵌套的jsonArray结构
        if(token.getType() == JsonTokenTypeEnum.LEFT_BRACKET){
            // 发现'['，栈上推进一个JsonArr
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_ARRAY, new JsonArray()));
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_1;
            return;
        }

        // 基础类型的value
        if(token.getType().isPrimitiveValue()){
            JsonParseStackValue jsonKeyToken = this.parseStack.popAndCheck(JsonParseStackValueTypeEnum.JSON_KEY);

            JsonToken keyToken  = (JsonToken) jsonKeyToken.getValue();
            Assert.assertTrue(keyToken.getType() == JsonTokenTypeEnum.STRING,"parse object keyToken not match!");

            // 获取栈顶的jsonObject对象，设置k/v
            JsonParseStackValue topJsonObject = this.parseStack.peekAndCheck(JsonParseStackValueTypeEnum.JSON_OBJECT);

            ((JsonObject) topJsonObject.getValue()).putKey(keyToken.getContent(), new JsonPrimitiveStr(token.getContent()));

            accept();

            // 基于下一个token判断状态跳转
            JsonToken nextJsonToken = this.jsonTokenReader.peek();
            if(nextJsonToken.getType() == JsonTokenTypeEnum.COMMA){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_5;
                return;
            }else if (nextJsonToken.getType() == JsonTokenTypeEnum.RIGHT_BRACE){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_2;
                return;
            }else{
                throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex()+1);
            }
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }

    private void processInParseObject5(JsonToken token){
        if(token.getType() == JsonTokenTypeEnum.COMMA){
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_6;
            return;
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }

    private void processInParseObject6(JsonToken token){
        if(token.getType() == JsonTokenTypeEnum.STRING){
            // 把key先压入栈中，然后等构造kv对时弹出
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_KEY,token));
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_3;
            return;
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }

    private void processInParseArr0(JsonToken token){
        if(token.getType() == JsonTokenTypeEnum.LEFT_BRACKET){
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_1;
            return;
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }

    private void processInParseArr1(JsonToken token){
        if(token.getType() == JsonTokenTypeEnum.RIGHT_BRACKET){
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_2;
            return;
        }

        // 嵌套的jsonObject结构
        if(token.getType() == JsonTokenTypeEnum.LEFT_BRACE){
            // 发现'{'，栈上推进一个JsonObject
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_OBJECT, new JsonObject()));
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_1;
            return;
        }

        // 嵌套的jsonArray结构
        if(token.getType() == JsonTokenTypeEnum.LEFT_BRACKET){
            // 发现'['，栈上推进一个JsonArr
            this.parseStack.push(new JsonParseStackValue(JsonParseStackValueTypeEnum.JSON_ARRAY, new JsonArray()));
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_1;
            return;
        }

        // 基础类型的value
        if(token.getType().isPrimitiveValue()){
            // 获取栈顶的jsonArr对象，添加一个元素
            JsonParseStackValue topJsonArr = this.parseStack.peekAndCheck(JsonParseStackValueTypeEnum.JSON_ARRAY);

            ((JsonArray) topJsonArr.getValue()).addElement(new JsonPrimitiveStr(token.getContent()));

            accept();

            // 基于下一个token判断状态跳转
            JsonToken nextJsonToken = this.jsonTokenReader.peek();
            if(nextJsonToken.getType() == JsonTokenTypeEnum.COMMA){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_3;
                return;
            }else if (nextJsonToken.getType() == JsonTokenTypeEnum.RIGHT_BRACKET){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_2;
                return;
            }else{
                throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex()+1);
            }
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }

    private void processInParseArr2(JsonToken token){
        // 遇到']'才会进来
        if(token.getType() != JsonTokenTypeEnum.RIGHT_BRACKET){
            throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }else{
            accept();
        }

        // 当前栈顶必定是JsonArray，先将其弹出，然后看栈顶的元素类型判断
        JsonParseStackValue currentJsonObjectStackValue = this.parseStack.popAndCheck(JsonParseStackValueTypeEnum.JSON_ARRAY);
        if(this.parseStack.isEmpty()){
            // 说明是root的JsonArr解析完了，再推回去直接返回
            this.parseStack.push(currentJsonObjectStackValue);
            this.currentStatus = StackBaseJsonParserStatusEnum.END_PARSE;
            return;
        }

        JsonArray jsonArray = (JsonArray) currentJsonObjectStackValue.getValue();

        JsonParseStackValueTypeEnum topObjType = this.parseStack.peekTopType();

        if(topObjType == JsonParseStackValueTypeEnum.JSON_KEY){
            // 如果是json_key，说明是当前jsonArray是父object的一个k/v项中的value。
            JsonParseStackValue keyStackValue = this.parseStack.popAndCheck(JsonParseStackValueTypeEnum.JSON_KEY);
            JsonToken keyJsonToken = (JsonToken) keyStackValue.getValue();

            JsonParseStackValue parentObject = this.parseStack.peekAndCheck(JsonParseStackValueTypeEnum.JSON_OBJECT);

            // 将当前k/v项附加在父object上
            ((JsonObject)parentObject.getValue()).putKey(keyJsonToken.getContent(), jsonArray);

            // 基于下一个token判断状态跳转
            JsonToken nextJsonToken = this.jsonTokenReader.peek();
            if(nextJsonToken.getType() == JsonTokenTypeEnum.COMMA){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_5;
                return;
            }else if (nextJsonToken.getType() == JsonTokenTypeEnum.RIGHT_BRACE){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_OBJECT_2;
                return;
            }else{
                throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex()+1);
            }

        }else if(topObjType == JsonParseStackValueTypeEnum.JSON_ARRAY){
            // 说明当前jsonObject是jsonArray的一个元素

            JsonParseStackValue parentArr = this.parseStack.peekAndCheck(JsonParseStackValueTypeEnum.JSON_ARRAY);
            ((JsonArray)parentArr.getValue()).addElement(jsonArray);

            // 基于下一个token判断状态跳转
            JsonToken nextJsonToken = this.jsonTokenReader.peek();
            if(nextJsonToken.getType() == JsonTokenTypeEnum.COMMA){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_3;
                return;
            }else if (nextJsonToken.getType() == JsonTokenTypeEnum.RIGHT_BRACKET){
                this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_2;
                return;
            }else{
                throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex()+1);
            }
        }else{
            // 别的情况都说明有问题，不是合法的json
            throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
        }
    }

    private void processInParseArr3(JsonToken token){
        if(token.getType() == JsonTokenTypeEnum.COMMA){
            accept();
            this.currentStatus = StackBaseJsonParserStatusEnum.PARSE_ARR_1;
            return;
        }

        throw new MuJsonParserException("unexpected token! index=" + jsonTokenReader.currentIndex());
    }
}
