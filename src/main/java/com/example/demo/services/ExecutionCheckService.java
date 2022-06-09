package com.example.demo.services;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import java.time.OffsetDateTime;
import org.joda.time.DateTime;
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
   * Retrieves all checks form the DB and gathers all parsed data. Associates each parsed element
   * with the list of all checks where the correct checks need to be found.
   *
   * @return list of entries, where each entry has as a Key - "output" and the value of the entry is
   *     the status - passed, failed, attention point and the check itself and the inputValue
   * @throws IOException if the parsing of the data fails
   */
  public List<SimpleEntry<String, ExecutedCheckOutput>> filterDataWithChecks(String input)
      throws IOException {
    List<Check> checks = checkRepository.findAll();
    var data = parserService.parseEverything(input);
    final var relevantChecksVal =
        data.getKey().stream()
            .map(
                element -> {
                  Stream<SimpleEntry<String, ExecutedCheckOutput>> checkedChecks =
                      mapSimpleEntry(checks, element , data.getValue());

                  return checkedChecks.collect(Collectors.toList());
                });
    return relevantChecksVal.flatMap(List::stream).collect(Collectors.toList());
  }

  /**
   * Associates each parsed entry that consists of document Source, attribute and value of the
   * attribute with the corresponding list of checks that needs to be performed on it. The
   * association is made based on specific document type (docSource) and attribute name.
   *
   * @param checks all available checks in the DB
   * @param element is one entry from the data that consists of key - the type of the document
   *     (Change Notice - some number) the attribute and the value that needs to be checked
   * @param dateTime the time of the parsing
   * @return stream of entries of type ExecutedCheckOutput
   */
  private Stream<SimpleEntry<String, ExecutedCheckOutput>> mapSimpleEntry(
      List<Check> checks,
      SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>> element,
      OffsetDateTime dateTime) {

    final var indexOfHyphen = element.getKey().indexOf("-");
    final var docSource = element.getKey().substring(0, indexOfHyphen - 1);
    final var tempKey = element.getValue().getKey();
    final var attribute = switch (tempKey) {
      // The cases are not exhaustive yet
      case "proposedSolution" -> "solution";
      case "theRequestPriority" -> "requestpriority";
      default -> tempKey.toLowerCase();
    };
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
              Result status = executeTheCheck(check, inputValue);
              ExecutedCheckOutput checkActionInputValue =
                  new ExecutedCheckOutput(status, inputValue, checkAndActionName, dateTime);

              return new SimpleEntry<String, ExecutedCheckOutput>("output", checkActionInputValue);
            });

    return checkedChecks;
  }

  /**
   * Based on the action value (the type of the value that is needed for the specific action) the
   * check is propagated to one of the corresponding method.
   *
   * @param check the check that needs to be executed on the @param inputValue
   * @param attributeValue the value that is scraped from Windchill and needs to be checked
   * @return the status of type Result when the check is executed
   */
  private Result executeTheCheck(Check check, String attributeValue) {
    String actionValue = check.getActionValueType().getValueType();
    Result result;
    switch (actionValue) {
      case "":
        result = checksNull(attributeValue, check);
        break;
      case "String":
        result = checksString(attributeValue, check);
        break;
      case "Integer":
        result = checksInteger(attributeValue, check);
        break;
      default:
        throw new IllegalStateException("Unexpected value type: " + actionValue);
    }
    ;
    return result;
  }

  /**
   * Performs all checks that required a value of type Integer. The value is the accompanying value
   * that is inputted when the check is created and should be a number.
   *
   * @param attributeValue the value that needs to be checked
   * @param check the check that needs to be performed
   * @return the result status when the check is performed
   * @throws IllegalStateException if the check action is none of the specified
   */
  private Result checksInteger(String attributeValue, Check check) { // InputValue and a check
    Result result = null;
    boolean status;
    String checkAction = check.getActionValueType().getAction();
    int length = attributeValue.length();
    int checkValue = Integer.parseInt(check.getValue()); // check value
    int valueInputInt = Integer.parseInt(attributeValue);
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

  /**
   * Performs all checks that required a value of type null, which is an indication that there is no
   * additional value needed to perform those type of checks.
   *
   * @param attributeValue the value that needs to be checked
   * @param check the check that needs to be performed
   * @return the result status when the check is performed
   * @throws IllegalStateException if the check action is none of the specified
   */
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

  /**
   * Performs all checks that required a value of type String. The value is the accompanying value
   * that is inputted when the check is created.
   *
   * @param attributeValue the value that needs to be checked
   * @param check the check that needs to be performed
   * @return the result status when the check is performed
   * @throws IllegalStateException if the check action is none of the specified
   */
  private Result checksString(String attributeValue, Check check) {
    Result result;
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
