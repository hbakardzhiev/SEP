package com.example.demo.controller;

import com.example.demo.modules.*;
import com.example.demo.Util;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.SheetSourceRepository;
import com.example.demo.services.ActionService;
import com.example.demo.services.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.cors.CorsConfiguration;

/** Class that defines the API - the GET, POST, DELETE and PUT requests. */
@RestController
@RequestMapping("/check")
@CrossOrigin(CorsConfiguration.ALL)
public class CheckController {

  private CheckService checkService;

  @Autowired private ActionService actionService;

  @Autowired private AdminRepository adminRepository;

  @Autowired private SheetSourceRepository sheetSourceRepository;

  public CheckController(CheckService checkService) {
    this.checkService = checkService;
  }

  /**
   * Returns all checks and their action names in the database
   *
   * @return list of all checks and their action names in the database
   */
  @GetMapping
  public List<CheckAndActionName> printAllCheck() {

    List<Check> allChecks = checkService.findAll();

    var checksAndActions =
        allChecks.stream()
            .map(
                e -> {
                  CheckAndActionName checkAndAction = toCheckAndActionName(e);
                  return checkAndAction;
                });

    return checksAndActions.collect(Collectors.toList());
  }

  private CheckAndActionName toCheckAndActionName(Check check) {
    ActionNameString actionNameString = new ActionNameString(check.getActionType().getAction());
    CheckAndActionName checkAndActionName = new CheckAndActionName(check, actionNameString);
    return checkAndActionName;
  }

  /**
   * Retrieves the check with the given name.
   *
   * @param name the name of the searched check
   * @return the check with the specified name
   * @throws RuntimeException if a check with such name does not exist
   */
  @GetMapping("/{name}")
  public CheckAndActionName getCheck(@PathVariable String name) {

    Check theCheck = checkService.findByName(name);

    if (theCheck == null) {
      throw new RuntimeException("Check not found " + name);
    }

    ActionNameString action = new ActionNameString(theCheck.getActionType().getAction());
    CheckAndActionName checkAndActionName = new CheckAndActionName(theCheck, action);

    return checkAndActionName;
  }

  /**
   * Adds the given check in the database if it does not exist already and associates the check with
   * the action type from the action table.
   *
   * @param checkAndActionName the check and the action name
   * @return TODO: make it later void when tested with front end
   */

  /*
  JSON - use the following in postman to see that it is working;
  {
      "theCheck":{
          "name" : "check1",
          "docSource" : "CN",
          "attribute" : "name",
          "value" : "orange",
          "comments" : "hey comment"
      },
      "actionName": {
          "actionName" : "Contains"
      }
  }
     */
  @PostMapping
  public Check addCheck(@RequestBody CheckAndActionName checkAndActionName) {

    Check theCheck = extractCheck(checkAndActionName);

    return theCheck;
  }

  /**
   * Updates an existing check in the database.
   *
   * @param checkAndActionName the updated check with the action name
   * @return TODO: make it later void when tested with front end
   */

  /*
  JSON - use the following in postman to see that it is working;
  {
      "theCheck":{
          "name" : "check1",
          "docSource" : "CT",
          "attribute" : "name",
          "comments" : "noooooooo comment"
      },
      "actionName": {
          "actionName" : "NotEmpty"
      }
  }
     */
  @PutMapping
  public Check updateCheck(@RequestBody CheckAndActionName checkAndActionName) {

    Check theCheck = extractCheck(checkAndActionName);

    return theCheck;
  }

  /**
   * Extracts the check and the action and saves/updates the check in the db. Associates the check
   * with the corresponding action name from the action table.
   *
   * @param checkAndActionName a check and its action name
   * @return TODO: make it later void when tested with front end
   */
  private Check extractCheck(@RequestBody CheckAndActionName checkAndActionName) {
    Check theCheck = checkAndActionName.theCheck;
    // theCheck.setAttribute(theCheck.getAttribute().toLowerCase().replaceAll("\\s", ""));

    String actionName = checkAndActionName.actionName.getActionName();

    String username = Util.getUsernameFromPrincipal();
    Long adminId = adminRepository.findAdminByUsername(username).getId();

    theCheck.setAuthor(adminId);

    Action theAction = actionService.findByName(actionName);

    theAction.add(theCheck);

//    add a new attribute type in the sheet_source table

    String typeOfAttribute = theCheck.getAttribute();
    SheetType sheetType = getSheetType(theCheck.getDocSource());
    SheetSource sheetSource = new SheetSource(typeOfAttribute, String.class.getTypeName(), sheetType);
    if (!sheetSourceRepository.existsByHtmlIDAndSheetSourceType(typeOfAttribute, sheetType)) {
      sheetSourceRepository.save(sheetSource);
    }
    checkService.save(theCheck);
    return theCheck;
  }

  private SheetType getSheetType(String docSource) {
    SheetType sheetType;
    sheetType =
            switch (docSource) {
              case "Change Notice" -> SheetType.CN;
              case "Change Request" -> SheetType.CR;
              case "Engineering Change Task" -> SheetType.CT;
              case "Manufacturing Change Task" -> SheetType.CT;
              case "Master Data Change Task" -> SheetType.CT;
              case "Commercial Change Task" -> SheetType.CT;
              default -> SheetType.DMR;
            };
    return sheetType; //prone to mistakes everything which is not correct will be DMR
  }

  /**
   * Deletes the check from the database with the provided name.
   *
   * @param name the name of the check that is to be deleted
   * @return TODO: make it later void when tested with front end
   * @throws RuntimeException if a check with such name does not exist
   */
  @DeleteMapping("/{name}")
  public String deleteCheck(@PathVariable String name) {
    Check theCheck = checkService.findByName(name);

    if (theCheck == null) {
      throw new RuntimeException("Check not found " + name);
    }
    checkService.deleteByName(name);
    return "Deleted " + name;
  }
}
