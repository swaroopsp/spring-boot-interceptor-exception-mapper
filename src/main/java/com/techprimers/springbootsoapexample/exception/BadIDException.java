package com.techprimers.springbootsoapexample.exception;

public class BadIDException extends BusinessException{

    public BadIDException(Exception exception, String message) {
        super(exception, message);
    }

    public BadIDException(String message) {
        super(message);
    }
}
