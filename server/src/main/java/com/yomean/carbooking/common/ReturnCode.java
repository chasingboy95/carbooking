package com.yomean.carbooking.common;

public enum ReturnCode {

    SUCCESS(200, "success"),

    ERROR(500, "server error"),
    ;

    private Integer code;
    private String message;

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    ReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
