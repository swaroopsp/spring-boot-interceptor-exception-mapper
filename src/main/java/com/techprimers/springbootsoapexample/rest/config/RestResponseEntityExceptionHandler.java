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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

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
                serviceFault, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NonBusinessException.class })
    public ResponseEntity<Object> handleNonBusinessException (
            Exception ex, WebRequest request) {
        log.error("Non business Exception happened", ex);
        ServiceFault serviceFault = ServiceFault.builder().code("500").description("Server error").build();
        return new ResponseEntity<Object>(
                serviceFault, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}