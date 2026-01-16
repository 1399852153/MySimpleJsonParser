package com.xiongyx.my.simple.json.parser.stackbase.stack;

import com.xiongyx.my.simple.json.util.Assert;

import java.util.Stack;

public class JsonParseStack{

    private Stack<JsonParseStackValue> stack;

    public JsonParseStack() {
        this.stack = new Stack<>();
    }

    public void push(JsonParseStackValue jsonParseStackValue){
        this.stack.push(jsonParseStackValue);
    }

    public JsonParseStackValue pop(){
        return this.stack.pop();
    }

    public JsonParseStackValueTypeEnum peekTopType(){
        return this.stack.peek().getType();
    }

    public JsonParseStackValue peekAndCheck(JsonParseStackValueTypeEnum type){
        JsonParseStackValue obj = this.stack.peek();

        Assert.assertTrue(obj.getType() == type,"json baseStack parse, unexpect top element=" + obj);

        return obj;
    }

    public JsonParseStackValue popAndCheck(JsonParseStackValueTypeEnum type){
        JsonParseStackValue obj = this.stack.pop();

        Assert.assertTrue(obj.getType() == type,"json baseStack parse, unexpect top element=" + obj);

        return obj;
    }

    public int size(){
        return this.stack.size();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }
}
