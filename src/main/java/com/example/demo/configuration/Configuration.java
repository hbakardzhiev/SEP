package com.example.demo.configuration;

import com.example.demo.Util;
import com.example.demo.modules.IParserBase;
import com.example.demo.modules.ParserBase;
import com.example.demo.modules.SheetSource;
import com.example.demo.modules.Parser;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.SheetSourceRepository;
import com.example.demo.repository.ParserRepository;
import java.io.IOException;
import java.util.*;

import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public ParserRepository parserRepositoryConf() throws IOException {
    return new Parser(Util.startUrl, SheetType.CN);
  }

  @Bean
  public ParserBase parserBaseConf() throws IOException {
    return new ParserBase();
  }


  @Bean
  CommandLineRunner cnPersist(SheetSourceRepository sheetSourceRepository) {
    var graph = new GraphConfig();
    ArrayList<CustomEdge> edges = (ArrayList<CustomEdge>) graph.getEdges();
    return args -> {
      final var list = new ArrayList<SheetSource>();
      final var types = new HashSet<SheetType>();
      types.add(SheetType.CN);
      types.add(SheetType.CR);

      for (SheetType sheetType: Arrays.asList(SheetType.CN, SheetType.CR, SheetType.CT)) {
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
