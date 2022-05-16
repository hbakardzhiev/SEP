package com.example.demo.configuration;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.DemoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CheckConfig {

    @Bean
    CommandLineRunner commandLineCheck(CheckRepository repository) {
        Check2 check1 = new Check2("check1", "CN", "name", "abc");

        return args -> {
            var list = Arrays.asList(check1);
            repository.saveAll(list);
        };
    }

}
