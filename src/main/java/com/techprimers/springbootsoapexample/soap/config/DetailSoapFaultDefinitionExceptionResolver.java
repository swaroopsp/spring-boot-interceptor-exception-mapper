package com.techprimers.springbootsoapexample.soap.config;


import com.techprimers.springbootsoapexample.exception.ServiceFault;
import com.techprimers.springbootsoapexample.exception.ServiceFaultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.WebServiceFaultException;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import javax.xml.namespace.QName;

@Slf4j
public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    private static final QName CODE = new QName("code");
    private static final QName DESCRIPTION = new QName("description");

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        log.warn("Exception processed ", ex);
        if (ex instanceof WebServiceFaultException) {
            ServiceFault serviceFault = ((ServiceFaultException) ex).getServiceFault();
            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(CODE).addText(serviceFault.getCode());
            detail.addFaultDetailElement(DESCRIPTION).addText(serviceFault.getDescription());
        } else if(ex instanceof RuntimeException){
            log.error("RuntimeException happened", ex);
        }
    }

}