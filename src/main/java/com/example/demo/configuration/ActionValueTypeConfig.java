package com.example.demo.configuration;

import com.example.demo.modules.ActionValueType;
import com.example.demo.repository.ActionValueTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration file for adding all types of actions in the database.
 */

@Configuration
public class ActionValueTypeConfig {

  @Bean
  CommandLineRunner commandLineRunnerActionTypes(
      ActionValueTypeRepository repository) {
    return args -> {
      final var actions =
          List.of(
              new ActionValueType(
                  "Empty",
                  null, "This attribute value should be empty"),
              new ActionValueType(
                  "NotEmpty",
                  null, "Should not be empty"),
              new ActionValueType(
                  "Contains",
                  "String", "The specified value should be contained in the attribute value"),
              new ActionValueType(
                  "NotContains",
                  "String", "sth"),
              new ActionValueType(
                  "StrictlyGreater",
                  "Integer", "Description"),
              new ActionValueType(
                  "StrictlySmaller",
                  "Integer", "description"),
              new ActionValueType(
                  "GreaterEqual",
                  "Integer", "sth"),
              new ActionValueType(
                  "SmallerEqual",
                  "Integer", "sth"),
              new ActionValueType(
                  "LengthStrictlyGreater",
                  "String", "sth"),
              new ActionValueType(
                  "LengthStrictlySmaller",
                  "Integer", "sth"),
              new ActionValueType(
                  "LengthGreaterEqual",
                  "Integer", "sth"),
              new ActionValueType(
                  "LengthSmallerEqual",
                  "Integer", "sth"),
              new ActionValueType(
                  "DifferentAttrValue",
                  "Integer", "sth"),
              new ActionValueType(
                  "SameAttrValue",
                  "String", "sth"),
              new ActionValueType(
                  "HumanCheck",
                  null, "sth"));

      repository.saveAll(actions);
    };
  }
}