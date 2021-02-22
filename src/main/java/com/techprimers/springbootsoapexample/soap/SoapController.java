package com.techprimers.springbootsoapexample.soap;

import com.techprimers.springbootsoapexample.domain.GetUserRequest;
import com.techprimers.springbootsoapexample.domain.GetUserResponse;
import com.techprimers.springbootsoapexample.soap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SoapController {

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = "http://techprimers.com/spring-boot-soap-example",
            localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        if(StringUtils.isEmpty(request.getName())) {
            throw new IllegalArgumentException("Invalid input");
        }
        response.setUser(userService.getUsers(request.getName()));
        return response;
    }
}