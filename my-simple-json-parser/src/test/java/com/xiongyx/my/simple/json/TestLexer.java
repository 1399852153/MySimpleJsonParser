package com.xiongyx.my.simple.json;

import com.xiongyx.my.simple.json.constants.JsonTestStrConstants;
import com.xiongyx.my.simple.json.lexer.StaticJsonLexer;
import com.xiongyx.my.simple.json.lexer.enums.JsonTokenTypeEnum;
import com.xiongyx.my.simple.json.lexer.model.JsonToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestLexer {

    @Test
    public void testTokenParse() {
        // 不报错就算成功
        for(String json : JsonTestStrConstants.testBaseJsonArr){
            StaticJsonLexer staticJsonLexer = new StaticJsonLexer(json);
            List<JsonToken> jsonTokenList = staticJsonLexer.doLex();
            System.out.println(jsonTokenList);
            Assert.assertEquals(jsonTokenList.get(jsonTokenList.size() - 1).getType(), JsonTokenTypeEnum.EOF);
        }

        for(String json : JsonTestStrConstants.testJsonArr){
            StaticJsonLexer staticJsonLexer = new StaticJsonLexer(json);
            List<JsonToken> jsonTokenList = staticJsonLexer.doLex();
            System.out.println(jsonTokenList.size());
            Assert.assertEquals(jsonTokenList.get(jsonTokenList.size() - 1).getType(), JsonTokenTypeEnum.EOF);
        }
    }


}
