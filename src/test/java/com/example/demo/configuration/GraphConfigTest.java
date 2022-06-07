package com.example.demo.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GraphConfigTest {

  /** Method under test: {@link GraphConfig#getEdges()} */
  @Test
  void testGetEdges() {
    assertEquals(12, (new GraphConfig()).getEdges().size());
  }
}
