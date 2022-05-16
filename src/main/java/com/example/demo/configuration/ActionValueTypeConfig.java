package com.example.demo.configuration;

import com.example.demo.modules.ActionValueType;
import com.example.demo.modules.Check2;
import com.example.demo.repository.ActionValueTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ActionValueTypeConfig {

    @Bean
    CommandLineRunner commandLineAction(ActionValueTypeRepository repository) {
        ActionValueType action1 = new ActionValueType("action1", "String");
        //Check2 check1 = new Check2("check1", "CN", "name", "abc");
        //action1.add(check1);

        return args -> {
            var list = Arrays.asList(action1);
            repository.saveAll(list);
        };
    }
}
