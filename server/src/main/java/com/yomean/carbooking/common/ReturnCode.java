package com.yomean.carbooking.common;

public enum ReturnCode {

    SUCCESS(200, "success"),

    ERROR(500, "server error"),

    BEEN_BOOKED(1001, "this car has been booked now"),
    EMPTY_USER(1002, "user info can't be empty"),
    EMPTY_CAR(1003, "car info can't be empty"),
    FINISHED_ORDER(1004, "finished order can't be modify"),
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
