package com.techprimers.springbootsoapexample.rest;

import com.techprimers.springbootsoapexample.exception.BadIDException;
import com.techprimers.springbootsoapexample.exception.BusinessException;
import com.techprimers.springbootsoapexample.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/test")
public class RestController {
    @Autowired
    private ExceptionUtils exceptionUtils;

    private String string = null;

    @GetMapping("/{id}")
    public String getSomething(@PathVariable("id") final String id) throws Exception{
        try {
            if (id.equals("1")) {
                throw new IllegalArgumentException("Invalid Input");
            } else if (id.equals("2")) {
                    throw new BadIDException("Business Exception happened");
            } else if (id.equals("3")) {
                return string.getBytes(id).toString();
            } else {
                return id;
            }
        }
        catch(Exception exception){
            throw exceptionUtils.getException(exception, "Exception happned while processing request");
        }
    }
}
