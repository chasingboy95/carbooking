package com.yomean.carbooking.common;

public enum ReturnCode {

    SUCCESS(200, "success"),

    ERROR(500, "server error"),

    BEEN_BOOKED(1001, "this car has been booked now"),
    EMPTY_USER(1002, "user info can't be empty"),
    EMPTY_CAR(1003, "car info can't be empty"),
    FINISHED_ORDER(1004, "finished order can't be modify"),
    INVALID_ID_OR_PASSWORD(1005, "invalid id or password"),
    NOT_LOGIN(1006, "not login"),
    TIME_IS_BEFORE_NOW(1007, "time can't before now"),
    START_TIME_IS_AFTER_END_TIME(1008, "start time can't after end time"),
    EMPTY_ID_OR_PASSWORD(1009, "user id or password cant be empty"),
    SYSTEM_BUSY_TRY_LATE(1010, "system is busy now, try this late"),
    TIME_BOOKED(1010, "the time range has been booked, please choose again"),
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
