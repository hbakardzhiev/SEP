package com.example.demo.configuration;

import com.example.demo.modules.*;
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
    CommandLineRunner commandLineCheck(CheckRepository repository) {
//        Check2 check1 = new Check2("check1", "CN", "name", "abc");

        return args -> {
            Check2 firstCheck = new Check2("Check 1",
                    "CN",
                    "name", null);

            Check2 secondCheck = new Check2("Check 2",
                    "CT",
                    "description",
                    "NotContains");
            repository.saveAll(
                    List.of(firstCheck, secondCheck));
        };
    }

}
