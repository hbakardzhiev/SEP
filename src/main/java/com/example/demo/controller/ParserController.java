package com.example.demo.controller;

import com.example.demo.modules.DemoClass;
import com.example.demo.services.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/show-parsed-data")
public class ParserController {
    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService demoService) {
        this.parserService = demoService;
    }

    @GetMapping
    public String parse() {
        return parserService.parse();
    }


}