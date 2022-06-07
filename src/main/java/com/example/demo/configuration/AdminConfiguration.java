package com.example.demo.configuration;

import com.example.demo.modules.ActionValueType;
import com.example.demo.modules.Admin;
import com.example.demo.repository.ActionValueTypeRepository;
import com.example.demo.repository.AdminRepoistory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerAdmin (AdminRepoistory repository) {
        return args -> {
            Admin def_admin = new Admin("admin", "admin@phillips", "admin");

            repository.save(def_admin);

        };
    }
}
