package com.techprimers.springbootsoapexample.utils;

import com.techprimers.springbootsoapexample.exception.BaseException;
import com.techprimers.springbootsoapexample.exception.BusinessException;
import com.techprimers.springbootsoapexample.exception.NonBusinessException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtils {
    public BaseException getException(Exception exception, String message){
        if(exception instanceof BusinessException){
            return new BusinessException(exception, message);
        } else{
            return new NonBusinessException(exception, message);
        }
    }
}
