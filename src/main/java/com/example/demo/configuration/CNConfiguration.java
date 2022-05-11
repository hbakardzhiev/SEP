package com.example.demo.configuration;

import com.example.demo.modules.CN;
import com.example.demo.modules.Parser;
import com.example.demo.repository.CNRepository;
import com.example.demo.repository.ParserRepository;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CNConfiguration {

  @Bean
  public ParserRepository parserRepositoryConf() throws IOException {
    return new Parser("Change Notice - Example.html");
  }

  @Bean
  CommandLineRunner cnPersist(CNRepository cnRepository) {
    return args -> {
      var list = new ArrayList<CN>();
      list.add(new CN("id", "infoPageIdentityDisplayType", String.class.getTypeName()));
      list.add(new CN("description", String.class.getTypeName()));
      list.add(new CN("phiChangeType", ArrayList.class.getTypeName()));
      list.add(new CN("name", String.class.getTypeName()));
      list.add(new CN("proposedSolution", String.class.getTypeName()));
      list.add(new CN("customerApprovalRequired", Boolean.class.getTypeName()));
      list.add(new CN("supplierApprovalRequired", Boolean.class.getTypeName()));
      cnRepository.saveAll(list);
    };
  }
}
