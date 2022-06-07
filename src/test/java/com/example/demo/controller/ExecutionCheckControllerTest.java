package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.demo.Util;
import com.example.demo.services.ExecutionCheckService;
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

@ContextConfiguration(classes = {ExecutionCheckController.class})
@ExtendWith(SpringExtension.class)
class ExecutionCheckControllerTest {
  @Autowired
  private ExecutionCheckController executionCheckController;

  @MockBean
  private ExecutionCheckService executionCheckService;

  /**
   * Method under test:
   */
  @Test
  void testExecuteChecksAll() throws Exception {
    when(this.executionCheckService.filterDataWithChecks(Util.CHANGE_NOTICE_EXAMPLE_HTML)).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder =
            MockMvcRequestBuilders.post("/executedChecks/all");
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test:
   */
  @Test
  void testExecuteChecksAll2() throws Exception {
    when(this.executionCheckService.filterDataWithChecks(Util.CHANGE_NOTICE_EXAMPLE_HTML)).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.post("/executedChecks/all");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
            .build()
            .perform(getResult)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link ExecutionCheckController#executeChecksAll(String)}
   */
  @Test
  void testExecuteChecksAll3() throws Exception {
    when(this.executionCheckService.filterDataWithChecks((String) any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/executedChecks/all")
            .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
            .content(objectMapper.writeValueAsString(new String()));
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
}
