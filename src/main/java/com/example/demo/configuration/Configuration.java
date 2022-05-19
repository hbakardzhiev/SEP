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
  CommandLineRunner cnPersist(SheetSourceRepository sheetSourceRepository) {
//    var graph = new GraphConfig();
    return args -> {
      final var list = new ArrayList<SheetSource>();

      for (SheetType sheetType : Arrays.asList(SheetType.CN, SheetType.CR, SheetType.CT)) {
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
