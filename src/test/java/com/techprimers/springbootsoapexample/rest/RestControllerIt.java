package com.techprimers.springbootsoapexample.rest;

import com.techprimers.springbootsoapexample.rest.config.RestResponseEntityExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerIt {

//    private MockMvc mvc;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void givenInput_1()
            throws Exception {
        mockMvc.perform(get("/test/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .string("{\n" +
                                "    \"code\": \"400\",\n" +
                                "    \"description\": \"Server error\"\n" +
                                "}"));
    }

    @Test
    public void givenInput_2()
            throws Exception {
        mockMvc.perform(get("/test/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUpgradeRequired())
                .andExpect(content()
                        .string("{\n" +
                                "    \"code\": \"500\",\n" +
                                "    \"description\": \"Server error\"\n" +
                                "}"));
    }

    @Test
    public void givenInput_3()
            throws Exception {
        mockMvc.perform(get("/test/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content()
                        .string("{\n" +
                                "    \"code\": \"500\",\n" +
                                "    \"description\": \"Server error\"\n" +
                                "}"));
    }
}