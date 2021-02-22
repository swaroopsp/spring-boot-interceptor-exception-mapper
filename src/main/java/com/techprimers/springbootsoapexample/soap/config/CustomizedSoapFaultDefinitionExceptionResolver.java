//package com.techprimers.springbootsoapexample.soap.config;
//
//import org.springframework.stereotype.Component;
//import org.springframework.ws.context.MessageContext;
//import org.springframework.ws.server.EndpointExceptionResolver;
//
//
//@Component
//public class CustomizedSoapFaultDefinitionExceptionResolver implements EndpointExceptionResolver {
//
//    public boolean resolveException(MessageContext messageContext, Object endpoint, Exception ex) {
//        if (ex instanceof RuntimeException) {
//            System.out.println("1111111111111111");
//        }
//        return false;
//    }
//}
