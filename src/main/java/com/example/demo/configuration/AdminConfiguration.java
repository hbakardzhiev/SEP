package com.example.demo.configuration;

import com.example.demo.modules.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerAdmin (AdminRepository repository) {
        return args -> {
            Admin def_admin = new Admin("admin", "admin@phillips", "admin");

            repository.save(def_admin);

        };
    }
}
