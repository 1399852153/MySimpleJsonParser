package com.xiongyx.my.simple.json.util;

import com.xiongyx.my.simple.json.parser.model.JsonArray;

public class TestUtil {

    public static void allEquals(Object... objects){
        if(objects.length == 0){
            return;
        }

        Object obj = objects[0];
        for(int i=1; i< objects.length; i++){
            Object item = objects[i];
            if(!obj.equals(item)){
                throw new RuntimeException(String.format("%s != %s",obj, item));
            }
        }
    }

    public static String buildHugeLevelJson(int level){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<level; i++){
            stringBuilder.append("[");
        }

        for(int i=0; i<level; i++){
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }

    public static int getSpecialJsonArrayLevel(JsonArray jsonArray){
        // todo

        return 0;
    }
}
