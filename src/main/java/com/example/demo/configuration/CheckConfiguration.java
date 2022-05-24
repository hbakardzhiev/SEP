package com.example.demo.configuration;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/** Configuration file for adding checks to the database. */
@Configuration
public class CheckConfiguration {

  @Bean
  CommandLineRunner commandLineCheck(CheckRepository repository) {

    return args -> {
      Check firstCheck =
          new Check("Check1", "CN", "name", "null", "comment to show when the check fails ");

      ActionValueType actionType = new ActionValueType("NotEmpty", "null", "pls1");
      actionType.add(firstCheck);

      Check secondCheck = new Check("Check2", "CT", "description", "banana", "comment2");
      ActionValueType actionType2 = new ActionValueType("Contains", "String", "pls2");
      actionType2.add(secondCheck);

      Check thirdCheck = new Check("Check3", "CT", "description", "pineapple", "comment3");
      ActionValueType actionType3 = new ActionValueType("Contains", "String", "pls3");
      actionType3.add(thirdCheck);

      repository.saveAll(List.of(firstCheck, secondCheck, thirdCheck));
    };
  }
}
