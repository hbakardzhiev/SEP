package com.example.demo.controller;

import com.example.demo.modules.ActionTypes;
import com.example.demo.modules.Check2;
import com.example.demo.services.ActionValueTypeService;
import com.example.demo.services.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actionTypes")
public class ActionValueTypeController {
    private ActionValueTypeService actionValueTypeService;

    @Autowired
    public ActionValueTypeController(ActionValueTypeService actValTypeService) {
        this.actionValueTypeService = actValTypeService;
    }

    //Update the list of related checks
    //Each type of action can be made with an empty list of checks
    @PutMapping("/{actionTypeChecks}") //??
    public void updateActionTypeChecks(
            @PathVariable("actionTypeChecks") String action,
            @RequestParam(required = false) Check2 theNewCheck
            ){
        actionValueTypeService.updateChecksOfActionType(action, theNewCheck);
    }
}
