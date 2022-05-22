package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.modules.ActionValueType;
import com.example.demo.modules.Check;
import com.example.demo.modules.CheckAndActionName;
import com.example.demo.services.ActionValueTypeService;
import com.example.demo.services.CheckService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CheckController.class})
@ExtendWith(SpringExtension.class)
class CheckControllerTest {
    @MockBean
    private ActionValueTypeService actionValueTypeService;

    @Autowired
    private CheckController checkController;

    @MockBean
    private CheckService checkService;

    /**
     * Method under test: {@link CheckController#addCheck(CheckAndActionName)}
     */
    @Test
    void testAddCheck() throws Exception {
        when(this.checkService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/check")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CheckAndActionName()));
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#deleteCheck(String)}
     */
    @Test
    void testDeleteCheck() throws Exception {
        ActionValueType actionValueType = new ActionValueType();
        actionValueType.setAction("Action");
        actionValueType.setChecks(new ArrayList<>());
        actionValueType.setDescription("The characteristics of someone or something");
        actionValueType.setValueType("42");

        Check check = new Check();
        check.setActionValueType(actionValueType);
        check.setAttribute("Attribute");
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");
        doNothing().when(this.checkService).deleteByName((String) any());
        when(this.checkService.findByName((String) any())).thenReturn(check);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/check/{name}", "Name");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted Name"));
    }

    /**
     * Method under test: {@link CheckController#deleteCheck(String)}
     */
    @Test
    void testDeleteCheck2() throws Exception {
        ActionValueType actionValueType = new ActionValueType();
        actionValueType.setAction("Action");
        actionValueType.setChecks(new ArrayList<>());
        actionValueType.setDescription("The characteristics of someone or something");
        actionValueType.setValueType("42");

        Check check = new Check();
        check.setActionValueType(actionValueType);
        check.setAttribute("Attribute");
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");
        doNothing().when(this.checkService).deleteByName((String) any());
        when(this.checkService.findByName((String) any())).thenReturn(check);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/check/{name}", "Name");
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted Name"));
    }

    /**
     * Method under test: {@link CheckController#getCheck(String)}
     */
    @Test
    void testGetCheck() throws Exception {
        ActionValueType actionValueType = new ActionValueType();
        actionValueType.setAction("Action");
        actionValueType.setChecks(new ArrayList<>());
        actionValueType.setDescription("The characteristics of someone or something");
        actionValueType.setValueType("42");

        Check check = new Check();
        check.setActionValueType(actionValueType);
        check.setAttribute("Attribute");
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");
        when(this.checkService.findByName((String) any())).thenReturn(check);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check/{name}", "Name");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"docSource\":\"Doc Source\",\"attribute\":\"Attribute\",\"value\":\"42\",\"comments\":\"Comments\"}"));
    }

    /**
     * Method under test: {@link CheckController#getCheck(String)}
     */
    @Test
    void testGetCheck2() throws Exception {
        ActionValueType actionValueType = new ActionValueType();
        actionValueType.setAction("Action");
        actionValueType.setChecks(new ArrayList<>());
        actionValueType.setDescription("The characteristics of someone or something");
        actionValueType.setValueType("42");

        Check check = new Check();
        check.setActionValueType(actionValueType);
        check.setAttribute("Attribute");
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");
        when(this.checkService.findByName((String) any())).thenReturn(check);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/check/{name}", "Name");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"docSource\":\"Doc Source\",\"attribute\":\"Attribute\",\"value\":\"42\",\"comments\":\"Comments\"}"));
    }

    /**
     * Method under test: {@link CheckController#getCheck(String)}
     */
    @Test
    void testGetCheck3() throws Exception {
        when(this.checkService.findAll()).thenReturn(new ArrayList<>());
        when(this.checkService.findByName((String) any())).thenThrow(new RuntimeException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check/{name}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#printAllCheck()}
     */
    @Test
    void testPrintAllCheck() throws Exception {
        when(this.checkService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#printAllCheck()}
     */
    @Test
    void testPrintAllCheck2() throws Exception {
        when(this.checkService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/check");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#updateCheck(CheckAndActionName)}
     */
    @Test
    void testUpdateCheck() throws Exception {
        when(this.checkService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/check")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CheckAndActionName()));
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

