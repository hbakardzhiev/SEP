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
          new Check("Check1", "Change Notice", "name", "null", "comment to show when the check fails ");
      ActionValueType actionType = new ActionValueType("NotEmpty", "", "pls1");
      actionType.add(firstCheck);

      Check secondCheck = new Check("Check2", "Change Notice", "proposedSolution", "null", "comment2");
      ActionValueType actionType2 = new ActionValueType("NotEmpty", "", "pls2");
      actionType2.add(secondCheck);

      Check check6 = new Check("Check6", "Change Notice", "customerApprovalRequired", "No", "comment3");
      ActionValueType actionType6 = new ActionValueType("IsEqual", "String", "pls3");
      actionType6.add(check6);

      Check thirdCheck = new Check("Check3", "Change Notice", "supplierApprovalRequired", "No", "comment3");
      ActionValueType actionType3 = new ActionValueType("IsEqual", "String", "pls3");
      actionType3.add(thirdCheck);

      //Change Requests checks
      Check forthCheck = new Check("Check4", "Change Request", "name", "null", "comment3");
      ActionValueType actionType4 = new ActionValueType("NotEmpty", "", "pls3");
      actionType4.add(forthCheck);

      Check check5 = new Check("Check5", "Change Request", "theRequestPriority", "null", "comment3");
      ActionValueType actionType5 = new ActionValueType("NotEmpty", "", "pls3");
      actionType5.add(check5);

      //put customer Approval when equal is ready

      //Change Tasks checks


      repository.saveAll(List.of(firstCheck, secondCheck, thirdCheck, forthCheck, check5, check6));
    };
  }

}
