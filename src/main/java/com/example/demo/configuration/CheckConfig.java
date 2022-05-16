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

@Configuration
public class CheckConfig {

    @Bean
    CommandLineRunner commandLineCheck(CheckRepository repository, ActionValueTypeRepository repository2) {
//        Check2 check1 = new Check2("check1", "CN", "name", "abc");

        return args -> {
            Check2 firstCheck = new Check2("Check 1",
                    "CN",
                    "name", null);

            ActionValueType actionType = new ActionValueType("NotEmpty", null);
            actionType.add(firstCheck);
            Check2 secondCheck = new Check2("Check 2",
                    "CT",
                    "description",
                    "banana");
            ActionValueType actionType2 = new ActionValueType("Contains", "String");
            actionType2.add(secondCheck);

            Check2 thirdCheck = new Check2("Check 3",
                    "CT",
                    "description",
                    "pineapple");
            ActionValueType actionType3 = new ActionValueType("Contains", "String");
            actionType3.add(thirdCheck);

            repository.saveAll(
                    List.of(firstCheck, secondCheck, thirdCheck));
        };
    }

}
