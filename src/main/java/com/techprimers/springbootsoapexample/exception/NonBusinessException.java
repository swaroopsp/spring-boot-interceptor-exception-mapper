package com.techprimers.springbootsoapexample.exception;

public class NonBusinessException extends BaseException{
    public NonBusinessException(String message) {
        super(message);
    }

    public NonBusinessException(Exception exception, String message) {
        super(exception, message);
    }
}
