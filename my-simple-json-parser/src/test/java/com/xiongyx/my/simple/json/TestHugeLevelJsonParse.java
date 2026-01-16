package com.xiongyx.my.simple.json;

import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.reader.StreamJsonTokenReader;
import com.xiongyx.my.simple.json.parser.recursive.RecursiveJsonParser;
import com.xiongyx.my.simple.json.parser.stackbase.StackBaseJsonParser;
import com.xiongyx.my.simple.json.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;

public class TestHugeLevelJsonParse {

    @Test
    public void testHugeLevelJsonParse() {
        int level = 3500;
        String hugeLevelJson = TestUtil.buildHugeLevelJson(level);

        // 3500层的深度，会StackOverflowError栈溢出
        Error recursiveJsonParseEx = null;
        try{
            RecursiveJsonParser recursiveJsonParser = new RecursiveJsonParser(new StreamJsonTokenReader(hugeLevelJson));
            JsonElement obj = recursiveJsonParser.doParse();
        }catch (Error e){
            recursiveJsonParseEx = e;
        }

        Assert.assertTrue(recursiveJsonParseEx instanceof StackOverflowError);
        System.out.println("level = " + level + " has StackOverflowError!");

        // jackson默认json深度为1000，超过了会报错
//        {
//            long start = System.currentTimeMillis();
//            Object obj = JackSonUtil.string2Obj(hugeLevelJson,Object.class);
//            System.out.println("jackson parse cost=" + (System.currentTimeMillis() - start));
//        }

        // 基于堆栈的能正确的解析出来，不会StackOverflowError栈溢出
        {
            long start = System.currentTimeMillis();
            StackBaseJsonParser stackBaseJsonParser = new StackBaseJsonParser(new StreamJsonTokenReader(hugeLevelJson));
            JsonElement obj = stackBaseJsonParser.doParse();
            int arrayLevel = TestUtil.getSpecialJsonArrayLevel(obj);
            Assert.assertEquals(arrayLevel, level - 1);
            System.out.println("stackBaseJsonParser parse cost=" + (System.currentTimeMillis() - start) + ",arrayLevel=" + arrayLevel);
        }
    }
}
