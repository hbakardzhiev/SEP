package com.example.demo.services;

import com.example.demo.modules.ActionValueType;
import com.example.demo.repository.ActionValueTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Service layer for ActionValueType class - holds the relevant business logic. */
@Service
public class ActionValueTypeService {

  private ActionValueTypeRepository actionValueTypeRepository;

  /** Constructor to use the correct repository */
  @Autowired
  public ActionValueTypeService(ActionValueTypeRepository repository) {
    this.actionValueTypeRepository = repository;
  }

  /**
   * Returns the list of all actions in the database
   *
   * @return list of actions
   */
  public List<ActionValueType> findAll() {
    return actionValueTypeRepository.findAll();
  }

  /**
   * Returns the action
   *
   * @param name the name of the action to be found
   * @return the action that has the name given as param
   * @throws RuntimeException if no action with the name is found
   */
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

  /**
   * Saves the action
   *
   * @param theAction the action to be saved in the database
   */
  public void save(ActionValueType theAction) {
    actionValueTypeRepository.save(theAction);
  }

  /**
   * Deletes the action that is already in the database
   *
   * @param name the name of the action to be deleted
   */
  public void deleteByName(String name) {
    actionValueTypeRepository.deleteById(name);
  }
}
