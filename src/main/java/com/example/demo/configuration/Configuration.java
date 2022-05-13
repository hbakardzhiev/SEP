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
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public ParserRepository parserRepositoryConf() throws IOException {
    return new Parser(Util.startUrl, SheetType.CN);
  }

  @Bean
  public IParserBase parserBaseConf() throws IOException {
    return new ParserBase();
  }

  @Bean
  CommandLineRunner cnPersist(SheetSourceRepository sheetSourceRepository) {
    var graph = new GraphConfig();
    return args -> {
      final var list = new ArrayList<SheetSource>();
      list.add(
          new SheetSource(
              "id",
              "infoPageIdentityDisplayType",
              String.class.getTypeName(),
              graph.converter("name")));

      list.add(new SheetSource("name", String.class.getTypeName(), graph.converter("name")));
      list.add(
          new SheetSource(
              "proposedSolution", String.class.getTypeName(), graph.converter("proposedSolution")));
      list.add(
          new SheetSource(
              "customerApprovalRequired",
              Boolean.class.getTypeName(),
              graph.converter("customerApprovalRequired")));
      list.add(
          new SheetSource(
              "supplierApprovalRequired",
              Boolean.class.getTypeName(),
              graph.converter("supplierApprovalRequired")));
      list.add(
          new SheetSource(
              "theRequestPriority",
              String.class.getTypeName(),
              graph.converter("theRequestPriority")));
      sheetSourceRepository.saveAllAndFlush(list);
    };
  }
}
