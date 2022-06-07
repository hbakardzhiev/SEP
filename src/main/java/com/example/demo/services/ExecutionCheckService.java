package com.example.demo.services;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.AbstractMap.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Service layer for implementation of the execution of checks */
@Service
public class ExecutionCheckService {

  @Autowired private ParserService parserService;

  @Autowired private CheckRepository checkRepository;

  public ExecutionCheckService(ParserService parserService, CheckRepository checkRepository) {
    this.parserService = parserService;
    this.checkRepository = checkRepository;
  }

  /**
   * Associates each parsed entry that consists of document Source, attribute and value of the
   * attribute with the corresponding list of checks that needs to be performed on it. The
   * association is made based on specific document type (docSource) and attribute name.
   *
   * @return list of entries, where each entry has as a Key the status of the check - passed,
   *     failed, attention point and the value of the entry is the check itself and the inputValue
   * @throws IOException if the parsing of the data fails
   */
  public List<SimpleEntry<Result, CheckInputValue>> filterDataWithChecks(String input)
      throws IOException {
    List<Check> checks = checkRepository.findAll();
    var data = parserService.parseEverything(input);
    final var relevantChecksVal =
        data.stream()
            .map(
                element -> {
                  Stream<SimpleEntry<Result, CheckInputValue>> checkedChecks =
                      mapSimpleEntry(checks, element);

                  return checkedChecks.collect(Collectors.toList());
                });
    return relevantChecksVal.flatMap(List::stream).collect(Collectors.toList());
  }

  private Stream<SimpleEntry<Result, CheckInputValue>> mapSimpleEntry(
      List<Check> checks,
      SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>> element) {

    final var indexOfHyphen = element.getKey().indexOf("-");
    final var docSource = element.getKey().substring(0, indexOfHyphen - 1);
    var tempattribute = element.getValue().getKey();
    switch (tempattribute) {
        // The cases are not exhaustive yet
      case "proposedSolution":
        tempattribute = "solution";
        break;
      case "theRequestPriority":
        tempattribute = "requestpriority";
        break;
      default:
        tempattribute = tempattribute.toLowerCase();
    }
    final var attribute = tempattribute;
    final var inputValue = element.getValue().getValue();

    // list of checks relevant for this docSource and attribute
    var relCheck =
        checks.stream()
            .filter(
                check ->
                    Objects.equals(check.getDocSource(), docSource)
                        && Objects.equals(check.getAttribute(), attribute));

    var checkedChecks =
        relCheck.map(
            check -> {
              String action = check.getActionValueType().getAction();
              ActionNameString actionNameString = new ActionNameString(action);
              CheckAndActionName checkAndActionName =
                  new CheckAndActionName(check, actionNameString);
              CheckInputValue checkActionInputValue =
                  new CheckInputValue(inputValue, checkAndActionName);

              Result status = executeTheCheck(check, inputValue);
              return new SimpleEntry<Result, CheckInputValue>(status, checkActionInputValue);
            });
    return checkedChecks;
  }

  /**
   * Based on the action value (the type of the value that is needed for the specific action) the
   * check is propagated to one of the corresponding method.
   *
   * @param check the check that needs to be executed on the @param inputValue
   * @param inputValue the value that is scraped from Windchill and needs to be checked
   * @return the status of type Result when executing the check
   */
  private Result executeTheCheck(Check check, String inputValue) {
    String actionValue = check.getActionValueType().getValueType();
    Result result;
    switch (actionValue) {
      case "":
        result = checksNull(inputValue, check);
        break;
      case "String":
        result = checksString(inputValue, check);
        break;
      case "Integer":
        result = checksInteger(inputValue, check);
        break;
      default:
        throw new IllegalStateException("Unexpected value type: " + actionValue);
    }
    ;
    return result;
  }

  private Result checksInteger(String valueInput, Check check) { // InputValue and a check
    Result result = null;
    boolean status;
    String checkAction = check.getActionValueType().getAction();
    int length = valueInput.length();
    int checkValue = Integer.parseInt(check.getValue()); // check value
    int valueInputInt = Integer.parseInt(valueInput); // attribute value
    switch (ActionTypes.valueOf(checkAction)) {
      case StrictlyGreater:
        status = checkValue > valueInputInt;
        break;
      case StrictlySmaller:
        status = checkValue < valueInputInt;
        break;
      case GreaterEqual:
        status = checkValue >= valueInputInt;
        break;
      case SmallerEqual:
        status = checkValue <= valueInputInt;
        break;
      case LengthStrictlyGreater:
        status = length > checkValue;
        break;
      case LengthStrictlySmaller:
        status = length < checkValue;
        break;
      case LengthGreaterEqual:
        status = length >= checkValue;
        break;
      case LengthSmallerEqual:
        status = length <= checkValue;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + checkAction);
    }
    ;
    result = true ? Result.passed : Result.failed;
    return result;
  }

  private Result checksNull(String attributeValue, Check check) {
    Result result;
    String checkAction = check.getActionValueType().getAction();
    switch (ActionTypes.valueOf(checkAction)) {
      case Empty:
        result = attributeValue.isEmpty() ? Result.passed : Result.failed;
        break;
      case NotEmpty:
        result = (!(attributeValue.isEmpty())) ? Result.passed : Result.failed;
        break;
      case HumanCheck:
        result = Result.humanCheck;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + checkAction);
    }
    return result;
  }

  private Result checksString(String attributeValue, Check check) {
    Result result = Result.humanCheck;
    String value = check.getValue();
    String checkAction = check.getActionValueType().getAction();
    switch (ActionTypes.valueOf(checkAction)) {
      case Contains:
        if (attributeValue.contains(value)) {
          result = Result.passed;
        } else {
          result = Result.failed;
        }
        break;
      case NotContains:
        if (!attributeValue.contains(value)) {
          result = Result.passed;
        } else {
          result = Result.failed;
        }
        break;
      case IsEqual:
        if (attributeValue.equals(value)) {
          result = Result.passed;
        } else {
          result = Result.failed;
        }
        break;
      case IsNotEqual:
        if (!attributeValue.equals(value)) {
          result = Result.passed;
        } else {
          result = Result.failed;
        }
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + checkAction);
    }
    return result;
  }
}
