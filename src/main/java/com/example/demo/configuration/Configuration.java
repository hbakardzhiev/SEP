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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
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
//    var graph = new GraphConfig();
    return args -> {
      final var list = new ArrayList<SheetSource>();
      final var types = new HashSet<SheetType>();
      types.add(SheetType.CN);
      types.add(SheetType.CR);

      for (SheetType sheetType : Arrays.asList(SheetType.CN, SheetType.CR)) {
        list.add(new SheetSource("id", "infoPageIdentityDisplayType", String.class.getTypeName(),
            sheetType));
        list.add(new SheetSource("id", "infoPageIdentityDisplayType", String.class.getTypeName(),
            sheetType));
        list.add(new SheetSource("name", String.class.getTypeName(), sheetType));
        list.add(new SheetSource("customerApprovalRequired", Boolean.class.getTypeName(),
            sheetType));
        list.add(new SheetSource("supplierApprovalRequired", Boolean.class.getTypeName(),
            sheetType));
      }
      for (var cnType : Arrays.asList(SheetType.CN)) {
        list.add(
            new SheetSource("proposedSolution", String.class.getTypeName(), cnType));
      }
      for (var crType : Arrays.asList(SheetType.CR)) {
        list.add(
            new SheetSource("theRequestPriority", String.class.getTypeName(), crType));
      }

      sheetSourceRepository.saveAllAndFlush(list);
    };
  }
}
