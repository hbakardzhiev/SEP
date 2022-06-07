package com.example.demo.controller;

import com.example.demo.services.ParserService;
import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * An exemplary controller built for web scrapping. It can be deleted in the future but it is useful
 * to quickly see the scraped data in json format.
 */
@RestController
@RequestMapping("/")
public class ParserController {

  private final ParserService parserService;

  @Autowired
  public ParserController(ParserService demoService) {
    this.parserService = demoService;
  }

  @GetMapping("allData")
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseAll(@RequestBody String input)
      throws IOException {
    return parserService.parseEverything(input);
  }
}
