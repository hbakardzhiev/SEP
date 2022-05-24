package com.example.demo.controller;

import com.example.demo.modules.CheckCategory;
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
    public List<AbstractMap.SimpleEntry<List<CheckCategory>, String>> executeChecksCN() throws IOException {
        return probaService.filterDataWithChecks();
    }

}
