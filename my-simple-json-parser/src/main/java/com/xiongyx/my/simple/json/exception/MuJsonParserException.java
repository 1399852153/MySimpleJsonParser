package com.xiongyx.my.simple.json.exception;

public class MuJsonParserException extends RuntimeException {

    public MuJsonParserException() {
    }

    public MuJsonParserException(String message) {
        super(message);
    }

    public MuJsonParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
