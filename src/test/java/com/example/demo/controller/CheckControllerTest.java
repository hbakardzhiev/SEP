package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.modules.Action;
import com.example.demo.modules.ActionNameString;
import com.example.demo.modules.Check;
import com.example.demo.modules.CheckAndActionName;
import com.example.demo.repository.AdminRepoistory;
import com.example.demo.services.ActionService;
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
    private ActionService actionService;

    @MockBean
    private AdminRepoistory adminRepoistory;

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

        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");

        CheckAndActionName checkAndActionName = new CheckAndActionName();
        checkAndActionName.setActionName(new ActionNameString("Action Name"));
        checkAndActionName.setTheCheck(check);
        String content = (new ObjectMapper()).writeValueAsString(checkAndActionName);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#addCheck(CheckAndActionName)}
     */
    @Test
    void testAddCheck2() throws Exception {
        Action action = new Action();
        action.setAction("?");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("?");
        check.setAuthor(123L);
        check.setComments("?");
        check.setDocSource("?");
        check.setName("?");
        check.setValue("42");

        ArrayList<Check> checkList = new ArrayList<>();
        checkList.add(check);
        when(this.checkService.findAll()).thenReturn(checkList);

        Action action1 = new Action();
        action1.setAction("Action");
        action1.setChecks(new ArrayList<>());
        action1.setDescription("The characteristics of someone or something");
        action1.setValueType("42");

        Check check1 = new Check();
        check1.setActiontype(action1);
        check1.setAttribute("Attribute");
        check1.setAuthor(123L);
        check1.setComments("Comments");
        check1.setDocSource("Doc Source");
        check1.setName("Name");
        check1.setValue("42");

        CheckAndActionName checkAndActionName = new CheckAndActionName();
        checkAndActionName.setActionName(new ActionNameString("Action Name"));
        checkAndActionName.setTheCheck(check1);
        String content = (new ObjectMapper()).writeValueAsString(checkAndActionName);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"theCheck\":{\"name\":\"?\",\"docSource\":\"?\",\"attribute\":\"?\",\"value\":\"42\",\"comments\":\"?\"},\"actionName\":{"
                                        + "\"actionName\":\"?\"}}]"));
    }

    /**
     * Method under test: {@link CheckController#deleteCheck(String)}
     */
    @Test
    void testDeleteCheck() throws Exception {
        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
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
        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
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
        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
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
                                "{\"theCheck\":{\"name\":\"Name\",\"docSource\":\"Doc Source\",\"attribute\":\"Attribute\",\"value\":\"42\",\"comments\":"
                                        + "\"Comments\"},\"actionName\":{\"actionName\":\"Action\"}}"));
    }

    /**
     * Method under test: {@link CheckController#getCheck(String)}
     */
    @Test
    void testGetCheck2() throws Exception {
        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
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
                                "{\"theCheck\":{\"name\":\"Name\",\"docSource\":\"Doc Source\",\"attribute\":\"Attribute\",\"value\":\"42\",\"comments\":"
                                        + "\"Comments\"},\"actionName\":{\"actionName\":\"Action\"}}"));
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
        Action action = new Action();
        action.setAction("?");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("?");
        check.setAuthor(123L);
        check.setComments("?");
        check.setDocSource("?");
        check.setName("?");
        check.setValue("42");

        ArrayList<Check> checkList = new ArrayList<>();
        checkList.add(check);
        when(this.checkService.findAll()).thenReturn(checkList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"theCheck\":{\"name\":\"?\",\"docSource\":\"?\",\"attribute\":\"?\",\"value\":\"42\",\"comments\":\"?\"},\"actionName\":{"
                                        + "\"actionName\":\"?\"}}]"));
    }

    /**
     * Method under test: {@link CheckController#printAllCheck()}
     */
    @Test
    void testPrintAllCheck3() throws Exception {
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

        Action action = new Action();
        action.setAction("Action");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("Attribute");
        check.setAuthor(123L);
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");

        CheckAndActionName checkAndActionName = new CheckAndActionName();
        checkAndActionName.setActionName(new ActionNameString("Action Name"));
        checkAndActionName.setTheCheck(check);
        String content = (new ObjectMapper()).writeValueAsString(checkAndActionName);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#updateCheck(CheckAndActionName)}
     */
    @Test
    void testUpdateCheck2() throws Exception {
        Action action = new Action();
        action.setAction("?");
        action.setChecks(new ArrayList<>());
        action.setDescription("The characteristics of someone or something");
        action.setValueType("42");

        Check check = new Check();
        check.setActiontype(action);
        check.setAttribute("?");
        check.setAuthor(123L);
        check.setComments("?");
        check.setDocSource("?");
        check.setName("?");
        check.setValue("42");

        ArrayList<Check> checkList = new ArrayList<>();
        checkList.add(check);
        when(this.checkService.findAll()).thenReturn(checkList);

        Action action1 = new Action();
        action1.setAction("Action");
        action1.setChecks(new ArrayList<>());
        action1.setDescription("The characteristics of someone or something");
        action1.setValueType("42");

        Check check1 = new Check();
        check1.setActiontype(action1);
        check1.setAttribute("Attribute");
        check1.setAuthor(123L);
        check1.setComments("Comments");
        check1.setDocSource("Doc Source");
        check1.setName("Name");
        check1.setValue("42");

        CheckAndActionName checkAndActionName = new CheckAndActionName();
        checkAndActionName.setActionName(new ActionNameString("Action Name"));
        checkAndActionName.setTheCheck(check1);
        String content = (new ObjectMapper()).writeValueAsString(checkAndActionName);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"theCheck\":{\"name\":\"?\",\"docSource\":\"?\",\"attribute\":\"?\",\"value\":\"42\",\"comments\":\"?\"},\"actionName\":{"
                                        + "\"actionName\":\"?\"}}]"));
    }
}

