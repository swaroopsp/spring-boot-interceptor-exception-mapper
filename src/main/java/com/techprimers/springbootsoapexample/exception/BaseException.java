package com.techprimers.springbootsoapexample.exception;

public class BaseException extends RuntimeException {
    public BaseException(Exception exception, String message){
        super(message, exception);
    }
    public BaseException(String message) {
        super(message);
    }
}
