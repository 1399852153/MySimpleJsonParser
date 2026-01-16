package com.xiongyx.my.simple.json.lexer.enums;

public enum JsonTokenTypeEnum {
    LEFT_BRACE("{"),
    RIGHT_BRACE("}"),

    LEFT_BRACKET("["),
    RIGHT_BRACKET("]"),

    COMMA(","),
    COLON(":"),

    TRUE("true"),
    FALSE("false"),
    NULL("null"),

    STRING("string"),
    NUMBER("number"),

    EOF("EOF"),
    ;
    private final String key;

    JsonTokenTypeEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    /**
     * 基础类型的value（string、number、true、false、null）
     * */
    public boolean isPrimitiveValue(){
        return this == STRING || this == NUMBER || this == NULL ||this == TRUE || this == FALSE;
    }
}
