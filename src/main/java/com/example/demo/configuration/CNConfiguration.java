package com.example.demo.configuration;

import com.example.demo.modules.SheetSource;
import com.example.demo.modules.Parser;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.SheetSourceRepository;
import com.example.demo.repository.ParserRepository;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CNConfiguration {

  @Bean
  public ParserRepository parserRepositoryConf() throws IOException {
    return new Parser("Change Notice - Example.html", SheetType.CN);
  }


  Graph<URI, DefaultEdge> g = new Multigraph<>(DefaultEdge.class);


  private void addMultipleEdges(Graph g, String attribute) {
    for (SheetType s: SheetType.values()) {
      g.addEdge(s, attribute);
    }
  }

  public List converter(Graph g, String attr) {
    ArrayList<Enum<SheetType>> list = new ArrayList<>();
    Set<DefaultEdge> edges = g.incomingEdgesOf(attr);
    for (DefaultEdge e : edges) {
      list.add((Enum<SheetType>) g.getEdgeSource(e));
    }
    return list;
  }

  public void populateGraph(Graph g) {
    g.addVertex(SheetType.CN);
    g.addVertex(SheetType.CT);
    g.addVertex(SheetType.CR);
    g.addVertex(SheetType.DMR);
    g.addVertex("name");
    g.addVertex("proposedSolution");
    g.addVertex("customerApprovalRequired");
    g.addVertex("supplierApprovalRequired");
    g.addVertex("theRequestPriority");

    addMultipleEdges(g, "name");
    g.addEdge(SheetType.CN, "proposedSolution");
    g.addEdge(SheetType.CR, "theRequestPriority");
    g.addEdge(SheetType.CN, "customerApprovalRequired");
    g.addEdge(SheetType.CR, "customerApprovalRequired");
    g.addEdge(SheetType.CN, "supplierApprovalRequired");
    g.addEdge(SheetType.CR, "supplierApprovalRequired");
  }
  @Bean
  CommandLineRunner cnPersist(SheetSourceRepository sheetSourceRepository) {
    populateGraph(g);
    return args -> {
      final var list = new ArrayList<SheetSource>();
      final List<Enum<SheetType>> cn = List.of(SheetType.CN);
      final List<Enum<SheetType>> cr = List.of(SheetType.CR);
      final List<Enum<SheetType>> cnAndCr = Stream.concat(cn.stream(), cr.stream()).parallel()
          .collect(Collectors.toList());
      list.add(new SheetSource("id", "infoPageIdentityDisplayType", String.class.getTypeName(),
              converter(g ,"name")));


      list.add(new SheetSource("name", String.class.getTypeName(), converter(g ,"name")));
      list.add(new SheetSource("proposedSolution", String.class.getTypeName(), converter(g ,"proposedSolution")));
      list.add(new SheetSource("customerApprovalRequired", Boolean.class.getTypeName(), converter(g ,"customerApprovalRequired")));
      list.add(new SheetSource("supplierApprovalRequired", Boolean.class.getTypeName(), converter(g ,"supplierApprovalRequired")));
      list.add(new SheetSource("theRequestPriority", String.class.getTypeName(), converter(g ,"theRequestPriority")));
      sheetSourceRepository.saveAll(list);
    };
  }

}
