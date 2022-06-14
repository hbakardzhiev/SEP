package com.example.demo.services;

import com.example.demo.modules.ActionNameString;
import com.example.demo.modules.ActionTypes;
import com.example.demo.modules.Check;
import com.example.demo.modules.CheckAndActionName;
import com.example.demo.modules.DateExecutedChecks;
import com.example.demo.modules.ExecutedCheckOutput;
import com.example.demo.modules.Result;
import com.example.demo.repository.CheckRepository;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public DateExecutedChecks filterDataWithChecks(String input) throws IOException {
    List<Check> checks = checkRepository.findAll();
    var data = parserService.parseEverything(input);
    final var relevantChecksVal =
        data.getKey().stream()
            .map(
                element -> {
                  Stream<SimpleEntry<String, ExecutedCheckOutput>> checkedChecks =
                      mapSimpleEntry(checks, element);

                  return checkedChecks.collect(Collectors.toList());
                })
            .flatMap(List::stream)
            .collect(Collectors.toList());
    DateExecutedChecks dateExecutedChecks =
        new DateExecutedChecks(data.getValue(), relevantChecksVal);
    return dateExecutedChecks;
  }

  /**
   * Associates each parsed entry that consists of document Source, attribute and value of the
   * attribute with the corresponding list of checks that needs to be performed on it. The
   * association is made based on specific document type (docSource) and attribute name.
   *
   * @param checks all available checks in the DB
   * @param element is one entry from the data that consists of key - the type of the document
   *     (Change Notice - some number) the attribute and the value that needs to be checked
   * @return stream of entries of type ExecutedCheckOutput
   */
  private Stream<SimpleEntry<String, ExecutedCheckOutput>> mapSimpleEntry(
      List<Check> checks,
      SimpleImmutableEntry<String, SimpleImmutableEntry<String, String>> element) {

    final var indexOfHyphen = element.getKey().indexOf("-");
    final var indexComma = element.getKey().indexOf(",");
    final var docSource = element.getKey().substring(0, indexOfHyphen - 1);
    final var docSourceUnique = element.getKey().substring(0, indexComma);
    final var attribute = element.getValue().getKey(); //tempKey
//    final var attribute =
//        switch (tempKey) {
//            // The cases are not exhaustive yet
//          case "proposedSolution" -> "solution";
//          case "theRequestPriority" -> "requestpriority";
//          default -> tempKey.toLowerCase();
//        };
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
              String action = check.getActionType().getAction();
              Check tempCheck = check;
              tempCheck.setDocSource(docSourceUnique);
              ActionNameString actionNameString = new ActionNameString(action);
              CheckAndActionName checkAndActionName =
                  new CheckAndActionName(check, actionNameString);
              Result status = executeTheCheck(tempCheck, inputValue);
              ExecutedCheckOutput checkActionInputValue =
                  new ExecutedCheckOutput(status, inputValue, checkAndActionName);

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
    String actionValue = check.getActionType().getValueType();
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
    String checkAction = check.getActionType().getAction();
    int length = attributeValue.length();
    int checkValue = Integer.parseInt(check.getValue()); // check value
    try {
        int valueInputInt = Integer.parseInt(attributeValue);
        status =
          switch (ActionTypes.valueOf(checkAction)) {
            case StrictlyGreater -> checkValue > valueInputInt;
            case StrictlySmaller -> checkValue < valueInputInt;
            case GreaterEqual -> checkValue >= valueInputInt;
            case SmallerEqual -> checkValue <= valueInputInt;
            default -> throw new IllegalStateException("Unexpected value: " + checkAction);
          };
        result = true ? Result.passed : Result.failed;
        return result;
    } catch(NumberFormatException numberFormatException){
        status = switch (ActionTypes.valueOf(checkAction)) {
            case LengthStrictlyGreater -> length > checkValue;
            case LengthStrictlySmaller -> length < checkValue;
            case LengthGreaterEqual -> length >= checkValue;
            case LengthSmallerEqual -> length <= checkValue;
            default -> throw new IllegalStateException("Unexpected value: " + checkAction);
          };
        result = true ? Result.passed : Result.failed;
        return result;
    }
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
    String checkAction = check.getActionType().getAction();
    result =
        switch (ActionTypes.valueOf(checkAction)) {
          case Empty -> attributeValue.isEmpty() ? Result.passed : Result.failed;
          case NotEmpty -> (!(attributeValue.isEmpty())) ? Result.passed : Result.failed;
          case HumanCheck -> Result.humanCheck;
          default -> throw new IllegalStateException("Unexpected value: " + checkAction);
        };
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
    String checkAction = check.getActionType().getAction();
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
