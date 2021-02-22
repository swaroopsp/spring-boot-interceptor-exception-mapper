package com.techprimers.springbootsoapexample.utils;

import com.techprimers.springbootsoapexample.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtils {
    public Exception getException(Exception exception, String message){
        if(exception instanceof BusinessException){
            return new BusinessException(exception, message);
        } else{
            return exception;
        }
    }
}
