package com.example.demo.controller;

import com.example.demo.modules.ActionValueType;
import com.example.demo.services.ActionValueTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionValueTypeController {

    private ActionValueTypeService actionValueTypeService;

    public ActionValueTypeController(ActionValueTypeService actionValueTypeService) {
        this.actionValueTypeService = actionValueTypeService;
    }

    @GetMapping
    public List<ActionValueType> getAllAction() {
        return actionValueTypeService.findAll();
    }


}
