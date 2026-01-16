package com.xiongyx.my.simple.json.util;

public class CommonStringUtil {

    public static boolean is1_9(char ch){
        return ch >= '1' && ch <= '9';
    }

    public static boolean is0_9(char ch){
        return ch >= '0' && ch <= '9';
    }

    public static boolean isWhitespace(char ch){
        return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
    }

    public static boolean isHex(char ch){
        if(is0_9(ch)){
            return true;
        }

        if(ch >= 'a' && ch <= 'f'){
            return true;
        }

        if(ch >= 'A' && ch <= 'F'){
            return true;
        }

        return false;
    }
}
