package com.example.demo.services;

import com.example.demo.modules.ActionValueType;
import com.example.demo.modules.Check2;
import com.example.demo.repository.ActionValueTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionValueTypeService {

    private ActionValueTypeRepository actionValueTypeRepository;

    @Autowired
    public ActionValueTypeService(ActionValueTypeRepository repository) {
        this.actionValueTypeRepository = repository;
    }

    public List<ActionValueType> findAll(){
        return actionValueTypeRepository.findAll();
    }

    public ActionValueType findByName(String name) {
        Optional<ActionValueType> result = actionValueTypeRepository.findById(name);

        ActionValueType theAction = null;

        if (result.isPresent()) {
            theAction = result.get();
        } else {
            throw new RuntimeException("Action not found " + name);
        }
        return theAction;
    }

    public void save(ActionValueType theAction) {
        actionValueTypeRepository.save(theAction);
    }

    public void deleteByName(String name) {
        actionValueTypeRepository.deleteById(name);
    }
}
