package com.xiongyx.my.simple.json;

import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.reader.StaticJsonTokenReader;
import com.xiongyx.my.simple.json.parser.stackbase.StackBaseJsonParser;

public class TestStackBaseJsonParser {

    public static void main(String[] args) {
        String json = "123";
        StackBaseJsonParser jsonParser = new StackBaseJsonParser(new StaticJsonTokenReader(json));
        JsonElement obj = jsonParser.doParse();
        System.out.println("格式化之前：\n" + json);
        System.out.println("格式化之后：\n" + obj.buildBeautyJsonString());
    }
}
