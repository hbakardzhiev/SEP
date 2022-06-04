package com.example.demo.configuration;

import com.example.demo.modules.Action;
import com.example.demo.repository.ActionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/** Configuration file for adding all types of actions in the database. */
@Configuration
public class ActionConfig {

  @Bean
  CommandLineRunner commandLineRunnerActionTypes(ActionRepository repository) {
    return args -> {
      final var actions =
          List.of(
              new Action("Empty", "", "This attribute value should be empty"),
              new Action("NotEmpty", "", "Should not be empty"),
              new Action(
                  "Contains",
                  "String",
                  "The specified value should be contained in the attribute value"),
              new Action("NotContains", "String", "sth"),
              new Action("StrictlyGreater", "Integer", "Description"),
              new Action("StrictlySmaller", "Integer", "description"),
              new Action("GreaterEqual", "Integer", "sth"),
              new Action("SmallerEqual", "Integer", "sth"),
              new Action("LengthStrictlyGreater", "Integer", "sth"),
              new Action("LengthStrictlySmaller", "Integer", "sth"),
              new Action("LengthGreaterEqual", "Integer", "sth"),
              new Action("LengthSmallerEqual", "Integer", "sth"),
              new Action("DifferentAttrValue", "String", "sth"),
              new Action("SameAttrValue", "String", "sth"),
              new Action("HumanCheck", "", "sth"),
              new Action("IsEqual", "String", "sth"),
              new Action("IsNotEqual", "String", "sth"));

      repository.saveAll(actions);
    };
  }
}
