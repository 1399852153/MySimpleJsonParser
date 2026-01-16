package com.xiongyx.my.simple.json.util;

public class Assert {

    public static void assertTrue(boolean b, String message) {
        if(!b){
            throw new RuntimeException(message);
        }
    }
}
