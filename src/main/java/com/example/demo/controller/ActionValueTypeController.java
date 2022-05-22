package com.example.demo.controller;

import com.example.demo.modules.ActionValueType;
import com.example.demo.services.ActionValueTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Defines the needed API for the table with the actions and value types.
 */
@RestController
@RequestMapping("/action")
public class ActionValueTypeController {

    private ActionValueTypeService actionValueTypeService;

    public ActionValueTypeController(ActionValueTypeService actionValueTypeService) {
        this.actionValueTypeService = actionValueTypeService;
    }

    /**
     * Retrieves all actions from the database.
     * @return list of all actions in the database
     */
    @GetMapping
    public List<ActionValueType> getAllAction() {
        return actionValueTypeService.findAll();
    }

    /*@GetMapping
    public ActionValueType getByAction(String action){
        return actionValueTypeService.findByName(action);
    }*/
}
