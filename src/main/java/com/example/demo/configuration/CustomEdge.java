package com.example.demo.configuration;

import org.jgrapht.graph.DefaultEdge;

public class CustomEdge extends DefaultEdge {
  public Object getSourceCustom() {
    return getSource();
  }

  public Object getTargetCustom() {
    return getTarget();
  }
}
