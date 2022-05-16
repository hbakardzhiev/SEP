package com.example.demo.services;

import com.example.demo.modules.ActionTypes;
import com.example.demo.modules.ActionValueType;
import com.example.demo.modules.Check2;
import com.example.demo.repository.ActionValueTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ActionValueTypeService {
    private ActionValueTypeRepository actionValueTypeRepository;

    @Autowired
    public ActionValueTypeService(ActionValueTypeRepository repository) {
        this.actionValueTypeRepository = repository;
    }

    //TODO: add a new actionType

    @Transactional
    public void updateChecksOfActionType(String actionType, Check2 theNewCheck) {
        ActionValueType actionValueType = actionValueTypeRepository.getById(actionType);

        if (actionValueType == null){
            throw new RuntimeException("Such an action type is not found " + actionType);
        }
        List<Check2> associatedChecks = actionValueType.getChecks();
        associatedChecks.add(theNewCheck);
        actionValueType.setChecks(associatedChecks);
    }
    //TODO: update actionType name or value of type or description???
    //TODO: delete an actionType
}
