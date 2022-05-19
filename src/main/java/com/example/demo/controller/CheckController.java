package com.example.demo.controller;

import com.example.demo.modules.ActionValueType;
import com.example.demo.modules.Check2;
import com.example.demo.modules.CheckAndActionName;
import com.example.demo.services.ActionValueTypeService;
import com.example.demo.services.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {


    private CheckService checkService;

    @Autowired
    private ActionValueTypeService actionValueTypeService;

    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping
    public List<Check2> printAllCheck(){
        return checkService.findAll();
    }

    @GetMapping("/{name}")
    public Check2 getCheck(@PathVariable String name) {

        Check2 theCheck = checkService.findByName(name);


        if (theCheck == null) {
            throw new RuntimeException("Check not found " + name);
        }

        return theCheck;
    }

    @PostMapping
    public Check2 addCheck(@RequestBody CheckAndActionName checkAndActionName) {

        if (checkService.findByName(checkAndActionName.theCheck.getName())==null) {
            Check2 theCheck = checkAndActionName.theCheck;
            String actionName = checkAndActionName.actionName.getActionName();

            ActionValueType theAction = actionValueTypeService.findByName(actionName);

            theAction.add(theCheck);

            checkService.save(theCheck);

            return theCheck;
        } else {
            return null;
        }
    }

    @PutMapping
    public Check2 updateCheck(@RequestBody CheckAndActionName checkAndActionName) {

        Check2 theCheck = checkAndActionName.theCheck;
        String actionName = checkAndActionName.actionName.getActionName();

        ActionValueType theAction = actionValueTypeService.findByName(actionName);

        theAction.add(theCheck);

        checkService.save(theCheck);
        return theCheck;
    }

    @DeleteMapping("/{name}")
    public String deleteCheck(@PathVariable String name) {
        Check2 theCheck = checkService.findByName(name);

        if (theCheck == null) {
            throw new RuntimeException("Check not found " + name);
        }
        checkService.deleteByName(name);
        return "Deleted " + name;
    }
}
