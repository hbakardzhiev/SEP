package com.example.demo.services;

import com.example.demo.modules.Action;
import com.example.demo.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Service layer for Action class - holds the relevant business logic. */
@Service
public class ActionService {

  private ActionRepository actionRepository;

  /** Constructor to use the correct repository */
  @Autowired
  public ActionService(ActionRepository repository) {
    this.actionRepository = repository;
  }

  /**
   * Returns the list of all actions in the database
   *
   * @return list of actions
   */
  public List<Action> findAll() {
    return actionRepository.findAll();
  }

  /**
   * Returns the action
   *
   * @param name the name of the action to be found
   * @return the action that has the name given as param
   * @throws RuntimeException if no action with the name is found
   */
  public Action findByName(String name) {
    Optional<Action> result = actionRepository.findById(name);

    Action theAction = null;

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
  public void save(Action theAction) {
    actionRepository.save(theAction);
  }

  /**
   * Deletes the action that is already in the database
   *
   * @param name the name of the action to be deleted
   */
  public void deleteByName(String name) {
    actionRepository.deleteById(name);
  }
}
