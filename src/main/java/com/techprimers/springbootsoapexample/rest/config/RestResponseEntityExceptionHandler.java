package com.techprimers.springbootsoapexample.rest.config;

import com.techprimers.springbootsoapexample.exception.BadIDException;
import com.techprimers.springbootsoapexample.exception.BusinessException;
import com.techprimers.springbootsoapexample.exception.NonBusinessException;
import com.techprimers.springbootsoapexample.exception.ServiceFault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@EnableWebMvc
public class RestResponseEntityExceptionHandler{

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        log.error("Exception happened", ex);
        ServiceFault serviceFault = ServiceFault.builder().code("400").description("bad request").build();
        return new ResponseEntity<Object>(
                serviceFault, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ BadIDException.class })
    public ResponseEntity<Object> handleBadIDException(
            Exception ex, WebRequest request) {
        log.error("Business Exception happened", ex);
        ServiceFault serviceFault = ServiceFault.builder().code("500").description("Server error").build();
        return new ResponseEntity<Object>(
                serviceFault, new HttpHeaders(), HttpStatus.UPGRADE_REQUIRED);
    }

    @ExceptionHandler({NonBusinessException.class })
    public ResponseEntity<Object> handleNonBusinessException (
            Exception ex, WebRequest request) {
        log.error("Non business Exception happened", ex);
        ServiceFault serviceFault = ServiceFault.builder().code("500").description("Server error").build();
        return new ResponseEntity<Object>(
                serviceFault, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> noHandlerFoundException(
            NoHandlerFoundException ex) {
        log.error("Non business Exception happened", ex);
        ServiceFault serviceFault = ServiceFault.builder().code("500").description("Server error").build();
        return new ResponseEntity<Object>(
                serviceFault, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @Override
//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String,String> responseBody = new HashMap<>();
//        responseBody.put("path",request.getContextPath());
//        responseBody.put("message","The URL you have reached is not in service at this time (404).");
//        return new ResponseEntity<Object>(responseBody,HttpStatus.NOT_FOUND);
//    }
}