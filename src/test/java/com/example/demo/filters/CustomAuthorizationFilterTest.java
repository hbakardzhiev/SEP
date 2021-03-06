package com.example.demo.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class CustomAuthorizationFilterTest {
  /**
   * Method under test: {@link
   * CustomAuthorizationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse, FilterChain)}
   */
  @Test
  void testDoFilterInternal() throws IOException, ServletException {
    CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter();
    MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
    Response response = new Response();
    FilterChain filterChain = mock(FilterChain.class);
    doNothing()
        .when(filterChain)
        .doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    customAuthorizationFilter.doFilterInternal(mockHttpServletRequest, response, filterChain);
    verify(filterChain)
        .doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
    assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
    assertFalse(mockHttpServletRequest.isAsyncSupported());
    assertFalse(mockHttpServletRequest.isAsyncStarted());
    assertTrue(mockHttpServletRequest.isActive());
    assertTrue(
        mockHttpServletRequest.getSession()
            instanceof org.springframework.mock.web.MockHttpSession);
    assertEquals("", mockHttpServletRequest.getServletPath());
    assertEquals(80, mockHttpServletRequest.getServerPort());
    assertEquals("localhost", mockHttpServletRequest.getServerName());
    assertEquals("http", mockHttpServletRequest.getScheme());
    assertEquals("", mockHttpServletRequest.getRequestURI());
    assertEquals(80, mockHttpServletRequest.getRemotePort());
    assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
    assertEquals("127.0.0.1", mockHttpServletRequest.getRemoteAddr());
    assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
    assertEquals("", mockHttpServletRequest.getMethod());
    assertEquals(80, mockHttpServletRequest.getLocalPort());
    assertEquals("localhost", mockHttpServletRequest.getLocalName());
    assertEquals("127.0.0.1", mockHttpServletRequest.getLocalAddr());
    assertTrue(
        mockHttpServletRequest.getInputStream()
            instanceof org.springframework.mock.web.DelegatingServletInputStream);
    assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
    assertEquals("", mockHttpServletRequest.getContextPath());
    assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
  }

  /**
   * Method under test: {@link
   * CustomAuthorizationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse, FilterChain)}
   */
  @Test
  void testDoFilterInternal3() throws IOException, ServletException {
    CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter();
    MockHttpServletRequest request = new MockHttpServletRequest();
    Response response = new Response();
    FilterChain filterChain = mock(FilterChain.class);
    doThrow(new ServletException("An error occurred"))
        .when(filterChain)
        .doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    assertThrows(
        ServletException.class,
        () -> customAuthorizationFilter.doFilterInternal(request, response, filterChain));
    verify(filterChain)
        .doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
  }
}
