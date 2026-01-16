package com.xiongyx.my.simple.json;

import com.xiongyx.my.simple.json.constants.JsonTestStrConstants;
import com.xiongyx.my.simple.json.model.JsonParseResult;
import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.reader.StaticJsonTokenReader;
import com.xiongyx.my.simple.json.parser.reader.StreamJsonTokenReader;
import com.xiongyx.my.simple.json.parser.recursive.RecursiveJsonParser;
import com.xiongyx.my.simple.json.parser.stackbase.StackBaseJsonParser;
import com.xiongyx.my.simple.json.util.JackSonUtil;
import com.xiongyx.my.simple.json.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;

public class TestParser {

    @Test
    public void testStreamJsonTokenReader(){
        for(String json : JsonTestStrConstants.testNormalJsonArr) {
            JsonParseResult myRecursiveStreamJsonParserResult = getParseResultByMyRecursiveStreamJsonParser(json);
            JsonParseResult myStackBaseStreamJsonParserResult = getParseResultByMyStackBaseStreamJsonParser(json);
            JsonParseResult jacksonResult = getParseResultByJacksonParser(json);

            TestUtil.allEquals(myRecursiveStreamJsonParserResult.getCompactJsonString(),
                myStackBaseStreamJsonParserResult.getCompactJsonString(),
                jacksonResult.getCompactJsonString());
            TestUtil.allEquals(myRecursiveStreamJsonParserResult.getBeautyJsonString(),
                myStackBaseStreamJsonParserResult.getBeautyJsonString(),
                jacksonResult.getBeautyJsonString());
        }

        for(String json : JsonTestStrConstants.testJsonArr) {
            JsonParseResult myJsonParserResult = getParseResultByMyRecursiveStreamJsonParser(json);

            // 不报错就算成功
        }

        for(String json : JsonTestStrConstants.testBaseJsonArr) {
            JsonParseResult myJsonParserResult = getParseResultByMyStaticJsonParser(json);

            // 不报错就算成功
            System.out.println(myJsonParserResult.getCompactJsonString() + "\n" + myJsonParserResult.getBeautyJsonString());
            System.out.println("===========================");
        }
    }

    @Test
    public void testStaticJsonTokenReader(){
        for(String json : JsonTestStrConstants.testNormalJsonArr) {
            JsonParseResult myJsonParserResult = getParseResultByMyStaticJsonParser(json);
            JsonParseResult jacksonResult = getParseResultByJacksonParser(json);

            Assert.assertEquals(myJsonParserResult.getCompactJsonString(),jacksonResult.getCompactJsonString());
            Assert.assertEquals(myJsonParserResult.getBeautyJsonString(),jacksonResult.getBeautyJsonString());
        }

        for(String json : JsonTestStrConstants.testJsonArr) {
            JsonParseResult myJsonParserResult = getParseResultByMyStaticJsonParser(json);

            // 不报错就算成功
        }

        for(String json : JsonTestStrConstants.testBaseJsonArr) {
            JsonParseResult myJsonParserResult = getParseResultByMyStaticJsonParser(json);

            // 不报错就算成功
            System.out.println(myJsonParserResult.getCompactJsonString() + "\n" + myJsonParserResult.getBeautyJsonString());
            System.out.println("===========================");
        }
    }

    private static JsonParseResult getParseResultByMyRecursiveStreamJsonParser(String originalJson){
        RecursiveJsonParser recursiveJsonParser = new RecursiveJsonParser(new StreamJsonTokenReader(originalJson));

        JsonElement obj = recursiveJsonParser.doParse();

        return new JsonParseResult(obj.buildCompactJsonString(), obj.buildBeautyJsonString());
    }

    private static JsonParseResult getParseResultByMyStackBaseStreamJsonParser(String originalJson){
        StackBaseJsonParser stackBaseJsonParser = new StackBaseJsonParser(new StreamJsonTokenReader(originalJson));

        JsonElement obj = stackBaseJsonParser.doParse();

        return new JsonParseResult(obj.buildCompactJsonString(), obj.buildBeautyJsonString());
    }

    private static JsonParseResult getParseResultByMyStaticJsonParser(String originalJson){
        RecursiveJsonParser recursiveJsonParser = new RecursiveJsonParser(new StaticJsonTokenReader(originalJson));

        JsonElement obj = recursiveJsonParser.doParse();

        return new JsonParseResult(obj.buildCompactJsonString(), obj.buildBeautyJsonString());
    }

    private static JsonParseResult getParseResultByJacksonParser(String originalJson){
        Object obj = JackSonUtil.string2Obj(originalJson,Object.class);

        return new JsonParseResult(JackSonUtil.obj2String(obj), JackSonUtil.obj2StringPretty(obj));
    }

    public static void main(String[] args) {
//        String json = JsonTestStrConstants.json6;

        String json = "{\"k1 \\u0041\\u4e2d\\u6587\\uD83D\\uDE00 垂直制表符\\u000B 换页符\\u000C\":20.00}";

        JsonParseResult myJsonParserResult = getParseResultByMyRecursiveStreamJsonParser(json);
        JsonParseResult jacksonResult = getParseResultByJacksonParser(json);

        int diffIndex = findStringDiff(myJsonParserResult.getCompactJsonString(),jacksonResult.getCompactJsonString());
        if(diffIndex != -1) {
            System.out.println(diffIndex + " " + myJsonParserResult.getCompactJsonString().indexOf(diffIndex) + " " + jacksonResult.getCompactJsonString().indexOf(diffIndex));
        }

        System.out.println(myJsonParserResult);
        System.out.println(jacksonResult);
    }

    private static int findStringDiff(String str1, String str2){
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for(int i=0;i<str1.length();i++){
            stringBuilder1.append(str1.charAt(i));
            stringBuilder2.append(str2.charAt(i));

            if(str1.charAt(i)!=str2.charAt(i)){
                return i;
            }
        }

        return -1;
    }
}
