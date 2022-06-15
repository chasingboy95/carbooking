package com.yomean.carbooking.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ReturnMessage exceptionHandler(ServiceException e) {
        log.error(e.getLocalizedMessage());
        return ReturnMessage.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnMessage exceptionHandler(Exception e) {
        log.error(e.getLocalizedMessage());
        return ReturnMessage.fail(ReturnCode.ERROR);
    }

}

