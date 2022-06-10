package com.example.demo.controller;

import com.example.demo.services.ParserService;
import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

/**
 * An exemplary controller built for web scrapping. It can be deleted in the future but it is useful
 * to quickly see the scraped data in json format.
 */
@RestController
@RequestMapping("/")
@CrossOrigin(CorsConfiguration.ALL)
public class ParserController {

  /** Autowired ParserService to access the parsing methods */
  private final ParserService parserService;

  @Autowired
  public ParserController(ParserService demoService) {
    this.parserService = demoService;
  }

  /**
   * Calls the ParserService and parses every type of document
   *
   * @param input String that specifies which url you have to parse
   * @return list of entries containing the parsed data
   * @throws IOException
   */
  @GetMapping("allData")
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseAll(
      @RequestBody String input) throws IOException {
    return parserService.parseEverything(input);
  }
}
