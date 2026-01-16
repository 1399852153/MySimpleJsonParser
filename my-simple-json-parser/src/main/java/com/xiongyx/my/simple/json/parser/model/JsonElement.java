package com.xiongyx.my.simple.json.parser.model;

import java.util.List;
import java.util.Map;

public abstract class JsonElement {

    /**
     * 4个空格缩进
     * */
    private static final String BEAUTY_INDENT = "    ";
    private static final String BEAUTY_KV_INDENT = " ";
    private static final String BEAUTY_LINE_BREAK = "\n";

    public String buildCompactJsonString(){
        StringBuilder jsonStringBuilder = new StringBuilder();

        // 紧凑json，没有缩进，没有换行, kv没有空格分割
        buildJsonString(this,jsonStringBuilder,"","","","");

        return jsonStringBuilder.toString();
    }

    public String buildBeautyJsonString(){
        StringBuilder jsonStringBuilder = new StringBuilder();

        buildJsonString(this,jsonStringBuilder,"",BEAUTY_LINE_BREAK,BEAUTY_INDENT,BEAUTY_KV_INDENT);

        return jsonStringBuilder.toString();
    }

    private static void buildJsonString(JsonElement jsonElement, StringBuilder jsonStringBuilder, String currentIndent,
                                        String lineBreak, String indent, String kvIndent){
        if(jsonElement instanceof JsonPrimitiveStr){
            jsonStringBuilder.append(jsonElement);
            return;
        }

        if(jsonElement instanceof JsonArray){
            JsonArray jsonArray  = (JsonArray) jsonElement;
            jsonStringBuilder.append("[").append(lineBreak);
            List<JsonElement> jsonArrayList = jsonArray.getArray();
            int i=0;
            for(JsonElement arrayItem : jsonArrayList){
                jsonStringBuilder.append(currentIndent).append(indent);
                // 递归下去，currentIndent多缩进一层
                buildJsonString(arrayItem,jsonStringBuilder,currentIndent + indent,lineBreak,indent,kvIndent);
                if(i != jsonArrayList.size()-1){
                    jsonStringBuilder.append(",");
                }

                jsonStringBuilder.append(lineBreak);
                i++;
            }

            jsonStringBuilder.append(currentIndent).append("]");
        }

        if(jsonElement instanceof JsonObject){
            JsonObject jsonObject  = (JsonObject) jsonElement;
            jsonStringBuilder.append("{").append(lineBreak);

            Map<String, JsonElement> objMap = jsonObject.getObjMap();

            int i=0;
            for(Map.Entry<String, JsonElement> entry : objMap.entrySet()){
                String key = entry.getKey();
                JsonElement value = entry.getValue();

                // key是string类型的，字面量里自带双引号的
                jsonStringBuilder.append(currentIndent).append(indent).append(key).append(kvIndent).append(":").append(kvIndent);
                // 递归下去，currentIndent多缩进一层
                buildJsonString(value,jsonStringBuilder,  currentIndent + indent, lineBreak,indent,kvIndent);

                if(i != objMap.size()-1){
                    jsonStringBuilder.append(",");
                }

                jsonStringBuilder.append(lineBreak);
                i++;
            }

            jsonStringBuilder.append(currentIndent).append("}");
        }
    }
}
