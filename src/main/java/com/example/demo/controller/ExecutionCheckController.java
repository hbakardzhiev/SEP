package com.example.demo.controller;

import com.example.demo.modules.CheckInputValue;
import com.example.demo.modules.Result;
import com.example.demo.services.ExecutionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

@RestController
@RequestMapping("/executedChecks") //change later
public class ExecutionCheckController {

    @Autowired
    private ExecutionCheckService executionCheckService;

    @GetMapping("/cn")
    public List<AbstractMap.SimpleEntry<Result, CheckInputValue>> executeChecksCN() throws IOException {
        return executionCheckService.filterDataWithChecks();
    }

}
