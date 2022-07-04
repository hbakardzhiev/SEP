package com.example.demo.controller;

import com.example.demo.modules.Check;
import com.example.demo.modules.CheckAndActionName;
import com.example.demo.services.CheckService;
import com.example.demo.services.CheckWrapperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/** Class that defines the API - the GET, POST, DELETE and PUT requests. */
@RestController
@RequestMapping("/check")
public class CheckController {

  private CheckService checkService;

  private CheckWrapperService checkWrapperService;

  public CheckController(CheckService checkService,
                         CheckWrapperService checkWrapperService) {
    this.checkService = checkService;
    this.checkWrapperService = checkWrapperService;
  }

  /**
   * Returns all checks and their action names in the database
   *
   * @return list of all checks and their action names in the database
   */
  @GetMapping
  public List<CheckAndActionName> printAllCheck() {

    final var allChecks = checkService.findAll();

    final var checksAndActions =
        allChecks.stream()
            .map(
                e -> {
                  final var checkAndAction = checkService.toCheckAndActionName(e);
                  return checkAndAction;
                });

    return checksAndActions.collect(Collectors.toList());
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

//    if (theCheck == null) {
//      throw new RuntimeException("Check not found " + name);
//    }

    CheckAndActionName checkAndActionName = checkService.toCheckAndActionName(theCheck);

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
    return checkWrapperService.extractCheck(checkAndActionName);
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

    Check theCheck = checkWrapperService.extractCheck(checkAndActionName);

    return theCheck;
  }

//  /**
//   * Extracts the check and the action and saves/updates the check in the db. Associates the check
//   * with the corresponding action name from the action table. If a new type of attribute is used
//   * which is not in the sheet_source table then the attribute as htmlId is added there as well.
//   *
//   * @param checkAndActionName a check and its action name
//   * @return TODO: make it later void when tested with front end
//   */
//  private Check extractCheck(@RequestBody CheckAndActionName checkAndActionName) {
//    Check theCheck = checkAndActionName.getTheCheck();
//    String actionName = checkAndActionName.getActionName().getActionName();
//
//    actionService.findByName(actionName).add(theCheck);
//
//    checkService.save(theCheck);
//    return theCheck;
//  }



//  private void createSheetSource(Check theCheck) {
//    //  add a new attribute type in the sheet_source table if it does not exist already
//    String typeOfAttribute = theCheck.getAttribute();
//    SheetType sheetType = getSheetType(theCheck.getDocSource());
//    if (!sheetSourceRepository.existsByHtmlIDAndSheetSourceType(typeOfAttribute, sheetType)) {
//      SheetSource sheetSource =
//          new SheetSource(typeOfAttribute, String.class.getTypeName(), sheetType);
//      sheetSourceRepository.save(sheetSource);
//    }
//  }

  /**
   * Associates the docSource with the general sheetType that it has.
   *
   * @param docSource the document source whose sheetType needs to be found
   * @return the sheetType that corresponds to the provided document source
   */
//  private SheetType getSheetType(String docSource) {
//    SheetType sheetType;
//    sheetType =
//        switch (docSource) {
//          case "Change Notice" -> SheetType.CN;
//          case "Change Request" -> SheetType.CR;
//          case "Engineering Change Task",
//                  "Manufacturing Change Task",
//                  "Master Data Change Task",
//                  "Commercial Change Task" -> SheetType.CT;
//          default -> SheetType.DMR;
//        };
//    return sheetType; // prone to mistakes everything which is not correct will be DMR
//  }

  /**
   * Deletes the check from the database with the provided name.
   *
   * @param name the name of the check that is to be deleted
   * @return TODO: make it later void when tested with front end
   * @throws RuntimeException if a check with such name does not exist
   */
  @DeleteMapping("/{name}")
  public String deleteCheck(@PathVariable String name) {
    checkService.deleteByName(name);
    return "Deleted " + name;
  }
}
