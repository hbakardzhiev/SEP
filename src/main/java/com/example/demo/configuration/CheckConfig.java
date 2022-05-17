package com.example.demo.configuration;

import com.example.demo.modules.*;
import com.example.demo.repository.ActionValueTypeRepository;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.DemoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class CheckConfig {

    /*@Bean
    CommandLineRunner commandLineCheck(CheckRepository repository, ActionValueTypeRepository repository2) {
//        Check2 check1 = new Check2("check1", "CN", "name", "abc");

        return args -> {
            Check2 firstCheck = new Check2("Check 1",
                    "CN",
                    "name", null);

            ActionValueType actionType = new ActionValueType("NotEmpty", null, "pls1");
            actionType.add(firstCheck);
            Check2 secondCheck = new Check2("Check 2",
                    "CT",
                    "description",
                    "banana");
            ActionValueType actionType2 = new ActionValueType("Contains", "String", "pls2");
            actionType2.add(secondCheck);
//            repository2.saveAll(List.of(actionType, actionType2));
            Check2 thirdCheck = new Check2("Check3",
                    "CT", "description",
                    "pineapple");
//            Optional<ActionValueType> actionType3 = repository2.findById("Contains");
            // assume not null
//            if(actionType3.isPresent()) {
//                ActionValueType actionType31 = actionType3.get();
//                actionType31.add(thirdCheck);
//            }
            ActionValueType actionType3 = new ActionValueType("Contains", "String", "pls3");
            repository.saveAll(
                    List.of(firstCheck, secondCheck, thirdCheck));
        };
    }*/

}
