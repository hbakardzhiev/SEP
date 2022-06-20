package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.demo.UtilTests;
import com.example.demo.modules.DateExecutedChecks;
import com.example.demo.services.ExecutionCheckService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
  @Autowired private ExecutionCheckController executionCheckController;

  @MockBean private ExecutionCheckService executionCheckService;

  /** Method under test: */
  @Test
  void testExecuteChecksAll() throws Exception {
    when(this.executionCheckService.filterDataWithChecks(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML))
        .thenReturn(new DateExecutedChecks(null, null));
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/executedChecks/all");
    requestBuilder.content(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML);
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
  }

  /** Method under test: */
  @Test
  void testExecuteChecksAll2() throws Exception {
    when(this.executionCheckService.filterDataWithChecks(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML))
        .thenReturn(new DateExecutedChecks(null, null));
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.post("/executedChecks/all");
    getResult.content(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML);
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
  }

  /** Method under test: {@link ExecutionCheckController#executeChecksAll(String)} */
  @Test
  void testExecuteChecksAll3() throws Exception {
    when(this.executionCheckService.filterDataWithChecks((String) any()))
        .thenReturn(new DateExecutedChecks(null, null));
    MockHttpServletRequestBuilder contentTypeResult =
        MockMvcRequestBuilders.post("/executedChecks/all").contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder =
        contentTypeResult.content(objectMapper.writeValueAsString(new String()));
    MockMvcBuilders.standaloneSetup(this.executionCheckController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"date\":null,\"executedChecks\":null}"));
  }
}
