package com.example.demo.configuration;

import com.example.demo.modules.SheetSource;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.SheetSourceRepository;
import java.util.ArrayList;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  CommandLineRunner persist(SheetSourceRepository sheetSourceRepository) {
    var graph = new GraphConfig();
    ArrayList<CustomEdge> edges = (ArrayList<CustomEdge>) graph.getEdges();
    return args -> {
      final var list = new ArrayList<SheetSource>();

      // TODO: add the sheetTypeCT to the graphConfig
      for (SheetType sheetType : Arrays.asList(SheetType.CT)) {
        list.add(new SheetSource("id", "infoPageIdentityDisplayType", String.class.getTypeName(),
            sheetType));
      }

      for (CustomEdge e: edges) {
        list.add(new SheetSource((String) e.getTargetCustom(), e.getTargetCustom().getClass().getTypeName(),
                 SheetType.valueOf((String) e.getSourceCustom())));
      }
      sheetSourceRepository.saveAllAndFlush(list);
    };
  }
}
