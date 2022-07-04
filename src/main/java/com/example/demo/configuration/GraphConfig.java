package com.example.demo.configuration;

import com.example.demo.modules.SheetType;
import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.graph.Multigraph;

/** Graph class that is used to extract which SheetSource to which page should be linked to. */
public class GraphConfig {

  private Graph<String, CustomEdge> g = new Multigraph<>(CustomEdge.class);
  private ArrayList<CustomEdge> edges;

  public GraphConfig() {
    this.populateGraph();
  }

  /**
   * Get all edges of the graph
   * @return list of edges
   */
  public List<CustomEdge> getEdges() {
    return new ArrayList(g.edgeSet());
  }

  /**
   * add all edges in the graph
   * @param attribute string attributes needed
   */
  private void addMultipleEdges(String attribute) {
    for (SheetType s : SheetType.values()) {
      g.addEdge(s.toString(), attribute);
    }
  }

  /**
   * fill in graph with all sheetTypes embedded in
   * the system
   */
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
    g.addVertex("description");
    g.addVertex("infoPageIdentityDisplayType");
    g.addVertex("phiSendNative");
    g.addVertex("phiDMR");
    g.addVertex("designcategory");
    g.addVertex("number");
    g.addVertex("phiPhantomManufacturingPart");
    g.addVertex("maturity");

    addMultipleEdges("name");
    addMultipleEdges("infoPageIdentityDisplayType");
    g.addEdge(String.valueOf(SheetType.CN), "proposedSolution");
    g.addEdge(String.valueOf(SheetType.CR), "theRequestPriority");
    g.addEdge(String.valueOf(SheetType.CN), "customerApprovalRequired");
    g.addEdge(String.valueOf(SheetType.CR), "customerApprovalRequired");
    g.addEdge(String.valueOf(SheetType.CN), "supplierApprovalRequired");
    g.addEdge(String.valueOf(SheetType.CR), "supplierApprovalRequired");
    g.addEdge(String.valueOf(SheetType.CN), "description");
    g.addEdge(String.valueOf(SheetType.CT), "description");
    g.addEdge(String.valueOf(SheetType.DMR), "phiSendNative");
    g.addEdge(String.valueOf(SheetType.DMR), "phiDMR");
    g.addEdge(String.valueOf(SheetType.DMR), "designcategory");
    g.addEdge(String.valueOf(SheetType.DMR), "number");
    g.addEdge(String.valueOf(SheetType.DMR), "phiPhantomManufacturingPart");
    g.addEdge(String.valueOf(SheetType.DMR), "maturity");
  }
}
