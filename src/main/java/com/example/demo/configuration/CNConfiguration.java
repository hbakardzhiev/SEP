package com.example.demo.configuration;

import com.example.demo.modules.SheetSource;
import com.example.demo.modules.Parser;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.SheetSourceRepository;
import com.example.demo.repository.ParserRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CNConfiguration {

  @Bean
  public ParserRepository parserRepositoryConf() throws IOException {
    return new Parser("Change Notice - Example.html", SheetType.CN);
  }

  @Bean
  CommandLineRunner cnPersist(SheetSourceRepository sheetSourceRepository) {
    return args -> {
      final var list = new ArrayList<SheetSource>();
      final List<Enum<SheetType>> cn = List.of(SheetType.CN);
      final List<Enum<SheetType>> cr = List.of(SheetType.CR);
      final List<Enum<SheetType>> cnAndCr = Stream.concat(cn.stream(), cr.stream()).parallel()
          .collect(Collectors.toList());
      list.add(new SheetSource("id", "infoPageIdentityDisplayType", String.class.getTypeName(),
          cnAndCr));
      list.add(new SheetSource("name", String.class.getTypeName(), cnAndCr));
      list.add(new SheetSource("proposedSolution", String.class.getTypeName(), cn));
      list.add(new SheetSource("customerApprovalRequired", Boolean.class.getTypeName(), cnAndCr));
      list.add(new SheetSource("supplierApprovalRequired", Boolean.class.getTypeName(), cnAndCr));
      list.add(new SheetSource("theRequestPriority", String.class.getTypeName(), cr));
      sheetSourceRepository.saveAll(list);
    };
  }
}
