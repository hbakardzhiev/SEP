package com.example.demo.configuration;

import com.example.demo.modules.SheetType;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomEdge extends DefaultEdge{
    public Object getSourceCustom() {
      return getSource();
    }

    public Object getTargetCustom() {
      return getTarget();
    }
}
