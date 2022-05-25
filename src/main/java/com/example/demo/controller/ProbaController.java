package com.example.demo.controller;

import com.example.demo.modules.CheckInputValue;
import com.example.demo.services.ProbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

@RestController
@RequestMapping("/executedChecks") //change later
public class ProbaController {

    @Autowired
    private ProbaService probaService;

    @GetMapping("/cn")
    public List<List<AbstractMap.SimpleEntry<String, CheckInputValue>>> executeChecksCN() throws IOException {
        return probaService.filterDataWithChecks();
    }

}
