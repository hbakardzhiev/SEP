package com.example.demo.controller;

import com.example.demo.modules.Check;
import com.example.demo.services.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {

    private CheckService checkService;

    @Autowired
    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping
    public List<Check> printAllCheck(){
        return checkService.getChecks();
    }

    @GetMapping("/{name}")
    public Check getCheck(@PathVariable String name) {
        Check theCheck = checkService.findByName(name);
        if (theCheck == null) {
            throw new RuntimeException("Check not found " + name);
        }
        return theCheck;
    }

    @PostMapping
    public Check addCheck(@RequestBody Check theCheck) {
        checkService.save(theCheck);
        return theCheck;
    }

    @PutMapping
    public Check updateCheck(@RequestBody Check theCheck) {
        checkService.save(theCheck);
        return theCheck;
    }

    @DeleteMapping("/{name}")
    public String deleteEmployee(@PathVariable String name) {
        Check theCheck = checkService.findByName(name);

        if (theCheck == null) {
            throw new RuntimeException("Check not found " + name);
        }
        checkService.deleteByName(name);
        return "Deleted " + name;
    }
}
