package com.techprimers.springbootsoapexample.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class BusinessException extends BaseException {

    public BusinessException(Exception exception, String message) {
        super(exception, message);
    }

    public BusinessException(String message) {
        super(message);
    }
}
