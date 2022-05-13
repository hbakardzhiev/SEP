package com.example.demo.controller;

import com.example.demo.modules.SheetType;
import com.example.demo.services.ParserService;
import java.io.IOException;
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

  @GetMapping("elements")
  public String parseElements() throws IOException {
    return parserService.parseElements();
  }

  @GetMapping("cr")
  public String parseCR() throws IOException {
    return parserService.parseCR(SheetType.CR);
  }
  
}
