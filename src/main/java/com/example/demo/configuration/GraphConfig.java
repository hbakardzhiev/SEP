package com.example.demo.configuration;

import com.example.demo.modules.SheetType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

public class GraphConfig {


  private Graph<String, CustomEdge> g = new Multigraph<>(CustomEdge.class);
  private ArrayList<CustomEdge> edges;

  public GraphConfig() {
    this.populateGraph();
  }

  public List<CustomEdge> getEdges() {
    Set<CustomEdge> edges = new HashSet<>();
    ArrayList arr = new ArrayList();
    for (CustomEdge e: g.edgeSet()) {
      arr.add(e);
    }
    //ArrayList arr = (ArrayList) List.of(g.edgeSet());
    System.out.println("4urulik");
    return arr;
  }

  private void addMultipleEdges(String attribute) {
    for (SheetType s : SheetType.values()) {
      g.addEdge(s.toString(), attribute);
    }
  }

  public List<SheetType> converter(String attr) {
    ArrayList<SheetType> list = new ArrayList<>();
    Set<CustomEdge> edges = g.incomingEdgesOf(attr);

    for (CustomEdge e : edges) {
      list.add(SheetType.valueOf(g.getEdgeSource(e)));
    }
    return list;
  }

  private void populateGraph() {
    edges = new ArrayList<>();

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
