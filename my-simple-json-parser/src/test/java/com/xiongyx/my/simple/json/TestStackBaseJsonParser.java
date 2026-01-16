package com.xiongyx.my.simple.json;

import com.xiongyx.my.simple.json.constants.JsonTestStrConstants;
import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.reader.StaticJsonTokenReader;
import com.xiongyx.my.simple.json.parser.stackbase.StackBaseJsonParser;

public class TestStackBaseJsonParser {

    public static void main(String[] args) {
        String json = JsonTestStrConstants.json7;

//        String json = "{\n" +
//            "    \"a\": {\n" +
//            "        \"b1\": 123,\n" +
//            "        \"b2\": \"321\",\n" +
//            "        \"b3\": [\"aa\",{\"k1\":\"v1\",\"k2\":\"v2\"},321]\n" +
//            "    },\n" +
//            "    \"a2\" : [{},\"21\"]\n" +
//            "}";
        StackBaseJsonParser stackBaseJsonParser = new StackBaseJsonParser(new StaticJsonTokenReader(json));

        JsonElement obj = stackBaseJsonParser.doParse();

        System.out.println(obj.buildCompactJsonString());
        System.out.println(obj.buildBeautyJsonString());

    }
}
