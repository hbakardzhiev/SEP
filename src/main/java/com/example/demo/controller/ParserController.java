package com.example.demo.controller;

import com.example.demo.modules.CN;
import com.example.demo.services.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/show-parsed-data")
public class ParserController {
  private final ParserService parserService;

  @Autowired
  public ParserController(ParserService demoService) {
    this.parserService = demoService;
  }

  @GetMapping
  public CN parse() {
    return parserService.parse();
  }
}
