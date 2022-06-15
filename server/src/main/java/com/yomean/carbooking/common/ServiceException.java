package com.yomean.carbooking.common;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private ReturnCode returnCode;
    private Integer code;
    private String message;
    
    public ServiceException() {
        super();
    }

    public ServiceException(ReturnCode returnCode) {
        super("{code:" + returnCode.code() + ",message:" + returnCode.message() + "}");
        this.returnCode = returnCode;
        this.code = returnCode.code();
        this.message = returnCode.message();
    }

    public ServiceException(Integer code, String message) {
        super("{code:" + code + ",message:" + message + "}");
        this.code = code;
        this.message = message;
    }
    public ServiceException(Integer code, String message, Object... args) {
        super("{code:" + code + ",message:" + String.format(message, args) + "}");
        this.code = code;
        this.message = String.format(message, args);
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

}
