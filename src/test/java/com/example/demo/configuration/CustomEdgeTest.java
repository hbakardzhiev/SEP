package com.example.demo.configuration;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CustomEdgeTest {

  /**
   * Method under test: {@link CustomEdge#getSourceCustom()}
   */
  @Test
  void testGetSourceCustom() {
    assertNull((new CustomEdge()).getSourceCustom());
  }

  /**
   * Method under test: {@link CustomEdge#getTargetCustom()}
   */
  @Test
  void testGetTargetCustom() {
    assertNull((new CustomEdge()).getTargetCustom());
  }
}

