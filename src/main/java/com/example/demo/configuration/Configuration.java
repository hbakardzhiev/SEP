package com.example.demo.configuration;

import com.example.demo.modules.SheetSource;
import com.example.demo.modules.SheetType;
import com.example.demo.repository.SheetSourceRepository;
import java.util.ArrayList;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Saves the records in the database. */
@org.springframework.context.annotation.Configuration
public class Configuration {

  /**
   * create password encoder
   *
   * @return
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * persist all entities
   *
   * @param sheetSourceRepository repository used to save entities
   * @return
   */
  @Bean
  CommandLineRunner persist(SheetSourceRepository sheetSourceRepository) {
    var graph = new GraphConfig();
    ArrayList<CustomEdge> edges = (ArrayList<CustomEdge>) graph.getEdges();
    return args -> {
      final var list = new ArrayList<SheetSource>();

      for (var sheetType : List.of(SheetType.CT, SheetType.CN, SheetType.CR, SheetType.DMR)) {
        list.add(
            new SheetSource(
                "id", "infoPageIdentityDisplayType", String.class.getTypeName(), sheetType));
      }

      for (CustomEdge e : edges) {
        list.add(
            new SheetSource(
                (String) e.getTargetCustom(),
                e.getTargetCustom().getClass().getTypeName(),
                SheetType.valueOf((String) e.getSourceCustom())));
      }
      sheetSourceRepository.saveAllAndFlush(list);
    };
  }

  /**
   * sets CORS on all links
   *
   * @return
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
      }
    };
  }
}
