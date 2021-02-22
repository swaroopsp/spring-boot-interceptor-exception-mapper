package com.techprimers.springbootsoapexample.soap.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapMessage;

@Slf4j
@Component
public class SoapLoggerInterceptor implements EndpointInterceptor {
    @Override
    public void afterCompletion(MessageContext arg0, Object arg1, Exception arg2) throws Exception {
        log.info("Server afterCompletion");
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object arg1) throws Exception {
        SoapBody soapBody = getSoapBody(messageContext);
        SoapFault soapFault = soapBody.getFault();
        log.error("Server Response Message", soapFault.getFaultStringOrReason());
        throw new RuntimeException(String.format("Error occured while invoking SOAP service - %s ", soapFault.getFaultStringOrReason()));
    }

    @Override
    public boolean handleRequest(MessageContext messageContext, Object arg1) throws Exception {
        log.info("Server Request Message", messageContext.getRequest());

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object arg1) throws Exception {
        log.info("Server Response Message", messageContext.getResponse());
        return true;
    }

    private SoapBody getSoapBody(MessageContext messageContext) {
        SoapMessage soapMessage = (SoapMessage) messageContext.getResponse();
        SoapEnvelope soapEnvelope = soapMessage.getEnvelope();
        return soapEnvelope.getBody();
    }
}
