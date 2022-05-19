package com.example.demo.controller;

import com.example.demo.modules.SheetType;
import com.example.demo.services.ParserService;
import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ParserController {

  private final ParserService parserService;

  @Autowired
  public ParserController(ParserService demoService) {
    this.parserService = demoService;
  }

  @GetMapping("cn")
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCN() throws IOException {
    return parserService.parseCN();
  }

  @GetMapping("cr")
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCR() throws IOException {
    return parserService.parseCR();
  }

  @GetMapping("ct")
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseCT() throws IOException {
    return parserService.parseCT();
  }

  @GetMapping("dmr")
  public List<SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>>> parseDMR() throws IOException {
    return parserService.parseDMR();
  }
  
}
