package com.example.demo.services;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/** Service layer for Check class - holds the relevant business logic. */
@Service
public class CheckService {

  private CheckRepository checkRepository;

  /** Constructor to use the correct repository */
  @Autowired
  public CheckService(CheckRepository repository) {
    this.checkRepository = repository;
  }

  /**
   * Returns the list of all checks in the database
   *
   * @return list of checks
   */
  public List<Check> findAll() {
    return checkRepository.findAll();
  }

  /**
   * Returns the check
   *
   * @param name the name of the check to be found
   * @return the check that has the name given as param
   */
  public Check findByName(String name) {
    Optional<Check> result = checkRepository.findById(name);

    Check theCheck = null;

    if (result.isPresent()) {
      theCheck = result.get();
    } else {
      throw new RuntimeException("Check not found " + name);
    }
    return theCheck;
  }

  public CheckAndActionName toCheckAndActionName(Check check) {
    ActionNameString actionNameString = new ActionNameString(check.getActionType().getAction());
    CheckAndActionName checkAndActionName = new CheckAndActionName(check, actionNameString);
    return checkAndActionName;
  }

  /**
   * Deletes the check that is already in the database
   *
   * @param name the name of the check to be deleted
   */
  public void deleteByName(String name) {
    Check theCheck = this.findByName(name);

    if (theCheck == null) {
      throw new RuntimeException("Check not found " + name);
    }
    checkRepository.deleteById(name);
  }
}
