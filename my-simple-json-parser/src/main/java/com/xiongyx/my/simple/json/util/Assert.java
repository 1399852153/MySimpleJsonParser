package com.xiongyx.my.simple.json.util;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;

public class Assert {

    public static void assertTrue(boolean b, String message) {
        if(!b){
            throw new MuJsonParserException(message);
        }
    }
}
