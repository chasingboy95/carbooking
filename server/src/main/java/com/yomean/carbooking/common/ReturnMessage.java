package com.yomean.carbooking.common;

import lombok.Data;

@Data
public class ReturnMessage<T> {
    private Integer code;
    private String message;
    private T data;

    private ReturnMessage() {
    }

    private ReturnMessage(Integer code) {
        this.code = code;
    }

    private ReturnMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ReturnMessage(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ReturnMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ReturnMessage<T> success() {
        return new ReturnMessage<>(200);
    }

    public static <T> ReturnMessage<T> success(T data) {
        return new ReturnMessage<>(200, "success", data);
    }

    public static <T> ReturnMessage<T> success(String message, T data) {
        return new ReturnMessage<>(200, message, data);
    }

    public static <T> ReturnMessage<T> success(ReturnCode returnCode) {
        return new ReturnMessage<>(returnCode.code(), returnCode.message());
    }

    public static <T> ReturnMessage<T> success(ReturnCode returnCode, T data) {
        return new ReturnMessage<>(returnCode.code(), returnCode.message(), data);
    }

    public static <T> ReturnMessage<T> fail() {
        return new ReturnMessage<>(-1);
    }

    public static <T> ReturnMessage<T> fail(String message) {
        return new ReturnMessage<>(-1, message);
    }

    public static <T> ReturnMessage<T> fail(String message, T data) {
        return new ReturnMessage<>(-1, message, data);
    }

    public static <T> ReturnMessage<T> fail(Integer code, String message) {
        return new ReturnMessage<>(code, message);
    }

    public static <T> ReturnMessage<T> fail(ReturnCode returnCode) {
        return new ReturnMessage<>(returnCode.code(), returnCode.message());
    }
}