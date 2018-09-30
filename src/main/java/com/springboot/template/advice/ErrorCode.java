package com.springboot.template.advice;

/**
 * Created by jinyu on 2018/9/30.
 */
public enum ErrorCode {
    ILLEGAL_PARAMS("ILLEGAL_PARAMS", "request params invalid"),
    SERVER_ERROR("SERVER_ERROR", "server is busy"),
    CLASS_NOT_FOUND("CLASS_NOT_FOUND", "class not found");


    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
