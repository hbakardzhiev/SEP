package com.example.demo.configuration;

import com.example.demo.modules.SheetType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

public class GraphConfig {

  private Graph<String, DefaultEdge> g = new Multigraph<>(DefaultEdge.class);

  public GraphConfig() {
    this.populateGraph();
  }

  private void addMultipleEdges(String attribute) {
    for (SheetType s : SheetType.values()) {
      g.addEdge(s.toString(), attribute);
    }
  }

  public List<Enum<SheetType>> converter(String attr) {
    ArrayList<Enum<SheetType>> list = new ArrayList<>();
    Set<DefaultEdge> edges = g.incomingEdgesOf(attr);

    for (DefaultEdge e : edges) {
      list.add(SheetType.valueOf(g.getEdgeSource(e)));
    }
    return list;
  }

  private void populateGraph() {
    g.addVertex(String.valueOf(SheetType.CN));
    g.addVertex(String.valueOf(SheetType.CT));
    g.addVertex(String.valueOf(SheetType.CR));
    g.addVertex(String.valueOf(SheetType.DMR));
    g.addVertex("name");
    g.addVertex("proposedSolution");
    g.addVertex("customerApprovalRequired");
    g.addVertex("supplierApprovalRequired");
    g.addVertex("theRequestPriority");

    addMultipleEdges("name");
    g.addEdge(String.valueOf(SheetType.CN), "proposedSolution");
    g.addEdge(String.valueOf(SheetType.CR), "theRequestPriority");
    g.addEdge(String.valueOf(SheetType.CN), "customerApprovalRequired");
    g.addEdge(String.valueOf(SheetType.CR), "customerApprovalRequired");
    g.addEdge(String.valueOf(SheetType.CN), "supplierApprovalRequired");
    g.addEdge(String.valueOf(SheetType.CR), "supplierApprovalRequired");
  }

}
