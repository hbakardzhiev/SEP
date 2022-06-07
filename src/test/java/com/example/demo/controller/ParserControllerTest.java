// package com.example.demo.controller;
//
// import static org.mockito.Mockito.when;
//
// import com.example.demo.services.ParserService;
//
// import java.util.ArrayList;
// import java.util.stream.Stream;
//
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// @ContextConfiguration(classes = {ParserController.class})
// @ExtendWith(SpringExtension.class)
// class ParserControllerTest {
//
//  @Autowired private ParserController parserController;
//
//  @MockBean private ParserService parserService;
//
//  /** Method under test: {@link ParserController#parseCN()} */
//  @Test
//  void testParseCN() throws Exception {
//    when(this.parserService.parseCN()).thenReturn(Stream.empty());
//    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cn");
//    MockMvcBuilders.standaloneSetup(this.parserController)
//        .build()
//        .perform(requestBuilder)
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//        .andExpect(MockMvcResultMatchers.content().string("[]"));
//  }
//
//  /** Method under test: {@link ParserController#parseCN()} */
//  @Test
//  void testParseCN2() throws Exception {
//    when(this.parserService.parseCN()).thenReturn(Stream.empty());
//    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cn");
//    getResult.contentType("https://example.org/example");
//    MockMvcBuilders.standaloneSetup(this.parserController)
//        .build()
//        .perform(getResult)
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//        .andExpect(MockMvcResultMatchers.content().string("[]"));
//  }
//
//  /** Method under test: {@link ParserController#parseCR()} */
//  @Test
//  void testParseCR() throws Exception {
//    when(this.parserService.parseCR()).thenReturn(Stream.empty());
//    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cr");
//    MockMvcBuilders.standaloneSetup(this.parserController)
//        .build()
//        .perform(requestBuilder)
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//        .andExpect(MockMvcResultMatchers.content().string("[]"));
//  }
//
//  /** Method under test: {@link ParserController#parseCR()} */
//  @Test
//  void testParseCR2() throws Exception {
//    when(this.parserService.parseCR()).thenReturn(Stream.empty());
//    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cr");
//    getResult.contentType("https://example.org/example");
//    MockMvcBuilders.standaloneSetup(this.parserController)
//        .build()
//        .perform(getResult)
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//        .andExpect(MockMvcResultMatchers.content().string("[]"));
//  }
//
//  /** Method under test: {@link ParserController#parseCT()} */
//  @Test
//  void testParseCT() throws Exception {
//    when(this.parserService.parseCT()).thenReturn(Stream.empty());
//    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ct");
//    MockMvcBuilders.standaloneSetup(this.parserController)
//        .build()
//        .perform(requestBuilder)
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//        .andExpect(MockMvcResultMatchers.content().string("[]"));
//  }
//
//  /** Method under test: {@link ParserController#parseCT()} */
//  @Test
//  void testParseCT2() throws Exception {
//    when(this.parserService.parseCT()).thenReturn(Stream.empty());
//    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/ct");
//    getResult.contentType("https://example.org/example");
//    MockMvcBuilders.standaloneSetup(this.parserController)
//        .build()
//        .perform(getResult)
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//        .andExpect(MockMvcResultMatchers.content().string("[]"));
//  }
// }
