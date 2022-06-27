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
              new Action("Empty", "", "The check passes if the attribute value is empty."),
              new Action("NotEmpty", "", "The check passes if the attribute value is " +
                      "not be empty."),
              new Action(
                  "Contains",
                  "String",
                  "The check passes if the attribute value is contained in the check value."),
              new Action("NotContains", "String", "The attribute value should not" +
                      " contain the check value."),
              new Action("StrictlyGreater", "Integer", "Both attribute value and check" +
                      " value should be integers." +
                      " The check passes if the attribute value is " +
                      "strictly greater than the check value."),
              new Action("StrictlySmaller", "Integer", "Both attribute value and check" +
                      " value should be integers." +
                      "The check passes if the attribute value is " +
                      "strictly smaller than the check value."),
              new Action("GreaterEqual", "Integer", "Both attribute value and check" +
                      " value should be integers." +
                      " The check passes if the attribute value is " +
                      " greater or equal than the check value."),
              new Action("SmallerEqual", "Integer", "Both attribute value and check" +
                      " value should be integers." +
                      " The check passes if the attribute value is " +
                      "smaller or equal than the check value."),
              new Action("LengthStrictlyGreater", "Integer", "Requires an integer check" +
                      " value." +
                      " The check passes if the length of the attribute value is " +
                      "strictly greater than the check value."),
              new Action("LengthStrictlySmaller", "Integer", "Requires an integer check" +
                      " value." +
                              " The check passes if the length of the attribute value is " +
                              "strictly smaller than the check value."),
              new Action("LengthGreaterEqual", "Integer", "Requires an integer check value." +
                              " And the length of the attribute value should be " +
                              "greater or equal than the check value."),
              new Action("LengthSmallerEqual", "Integer", "Requires an integer check" +
                      " value." +
                      " The check passes if the length of the attribute value is " +
                      "strictly greater than the check value."),
              new Action("HumanCheck", "", "This check should be checked manually."),
              new Action("IsEqual", "String", "The check passes if the " +
                      "the attribute value is exactly as the specified check value. "),
              new Action("IsNotEqual", "String", "The check passes if the " +
                      "the attribute value is not equal the check value."));

      repository.saveAll(actions);
    };
  }
}
