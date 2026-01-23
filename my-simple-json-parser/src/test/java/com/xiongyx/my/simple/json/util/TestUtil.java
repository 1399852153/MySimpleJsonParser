package com.xiongyx.my.simple.json.util;

import com.xiongyx.my.simple.json.exception.MuJsonParserException;
import com.xiongyx.my.simple.json.parser.model.JsonArray;
import com.xiongyx.my.simple.json.parser.model.JsonElement;
import com.xiongyx.my.simple.json.parser.model.JsonObject;

public class TestUtil {

    public static void allEquals(Object... objects){
        if(objects.length == 0){
            return;
        }

        Object obj = objects[0];
        for(int i=1; i< objects.length; i++){
            Object item = objects[i];
            if(!obj.equals(item)){
                throw new MuJsonParserException(String.format("%s != %s",obj, item));
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

    public static int getSpecialJsonArrayLevel(JsonElement obj){
        if(!(obj instanceof JsonArray)){
            return 0;
        }
        JsonArray jsonArray = (JsonArray)obj;
        int level = 0;
        JsonArray currentArr = jsonArray;
        while(currentArr.getArray() != null && !currentArr.getArray().isEmpty()){
            JsonElement jsonElement = currentArr.getArray().get(0);
            if(jsonElement instanceof JsonArray){
                currentArr = (JsonArray) jsonElement;
                level++;
            }else{
                return level;
            }
        }

        return level;
    }
}
