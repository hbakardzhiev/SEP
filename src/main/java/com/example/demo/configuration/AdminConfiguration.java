package com.example.demo.configuration;

import com.example.demo.modules.Admin;
import com.example.demo.services.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfiguration {

  /**
   * create default admin
   *
   * @param service adminService
   * @return void
   */
  @Bean
  CommandLineRunner commandLineRunnerAdmin(AdminService service) {
    return args -> {
      Admin def_admin = new Admin("admin", "admin@phillips", "admin");

      service.addAdmin(def_admin);
    };
  }
}
