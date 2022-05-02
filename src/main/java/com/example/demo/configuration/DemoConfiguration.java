package com.example.demo.configuration;

import com.example.demo.modules.DemoClass;
import com.example.demo.repository.DemoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DemoConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(DemoRepository repository) {
        return args -> {
            var list = Arrays.asList(new DemoClass("Peso"), new DemoClass("USD"));
            repository.saveAllAndFlush(list);
        };
    }
}
