package com.example.demo.controller;

import static org.mockito.Mockito.when;

import com.example.demo.services.ActionService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ActionController.class})
@ExtendWith(SpringExtension.class)
class ActionControllerTest {
  @Autowired private ActionController actionController;

  @MockBean private ActionService actionService;

  /** Method under test: {@link ActionController#getAllAction()} */
  @Test
  void testGetAllAction() throws Exception {
    when(this.actionService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/action");
    MockMvcBuilders.standaloneSetup(this.actionController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /** Method under test: {@link ActionController#getAllAction()} */
  @Test
  void testGetAllAction2() throws Exception {
    when(this.actionService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/action");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.actionController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
}
