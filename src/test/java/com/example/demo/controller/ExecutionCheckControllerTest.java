package com.example.demo.controller;

import static org.mockito.Mockito.when;

import com.example.demo.services.ExecutionCheckService;

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

@ContextConfiguration(classes = {ExecutionCheckController.class})
@ExtendWith(SpringExtension.class)
class ExecutionCheckControllerTest {
  @Autowired private ExecutionCheckController executionCheckController;

  @MockBean private ExecutionCheckService executionCheckService;

  /** Method under test: {@link ExecutionCheckController#executeChecksAll()} */
  @Test
  void testExecuteChecksAll() throws Exception {
    when(this.executionCheckService.filterDataWithChecks()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/executedChecks/all");
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /** Method under test: {@link ExecutionCheckController#executeChecksAll()} */
  @Test
  void testExecuteChecksAll2() throws Exception {
    when(this.executionCheckService.filterDataWithChecks()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/executedChecks/all");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
}
