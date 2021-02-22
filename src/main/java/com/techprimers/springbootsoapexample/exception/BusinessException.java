package com.techprimers.springbootsoapexample.exception;

public class BusinessException extends BaseException {

    public BusinessException(Exception exception, String message) {
        super(exception, message);
    }

    public BusinessException(String message) {
        super(message);
    }
}
