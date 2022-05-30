package com.example.demo.services;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExecutionCheckService {

    @Autowired
    private ParserService parserService;

    @Autowired
    private CheckRepository checkRepository;

    public List<AbstractMap.SimpleEntry<Result,CheckInputValue>> filterDataWithChecks () throws IOException {
        List<Check> checks = checkRepository.findAll();
        var data = parserService.parseEverything();
        final var relevantChecksVal = data.stream().map(element -> {
            final var indexOfHyphen = element.getKey().indexOf("-");
            final var docSource = element.getKey().substring(0, indexOfHyphen - 1);
            final var attribute = element.getValue().getKey();
            final var inputValue = element.getValue().getValue();

            //list of checks relevant for this docSource and attribute
            List<Check> relCheck = checks.stream().filter(check -> Objects.equals(check.getDocSource(), docSource)
                            && Objects.equals(check.getAttribute(), attribute)).collect(Collectors.toList());

            var checkedChecks = relCheck.stream().map( check -> {
                String action = check.getActionValueType().getAction();
                ActionNameString actionNameString = new ActionNameString(action);
                CheckAndActionName checkAndActionName = new CheckAndActionName(check, actionNameString);
                CheckInputValue checkActionInputValue = new CheckInputValue(inputValue, checkAndActionName);

                Result status = executeTheCheck(check, inputValue);
                return new AbstractMap.SimpleEntry<Result, CheckInputValue>(status, checkActionInputValue);
            });

            return checkedChecks.collect(Collectors.toList());

        });
        return relevantChecksVal.flatMap(List::stream).collect(Collectors.toList());

    }


    private Result executeTheCheck(Check check, String inputValue) {
        String actionValue = check.getActionValueType().getValueType();
        Result label = null;
        switch (actionValue) {
            case "null": label = checksNull(inputValue, check); break;
            // front end will give us ""
            case "String": label = checksString(inputValue, check); break;
            case "Integer": label = checksInteger(inputValue, check); break;
        }
        return label;
    }

    private Result checksInteger(String valueInput, Check check) { //InputValue and a check
        Result result = null;
        boolean status;
        String checkAction = check.getActionValueType().getAction();
        Integer length = valueInput.length();
        Integer checkValue = Integer.parseInt(check.getValue()); // check value
        Integer valueInputInt = Integer.parseInt(valueInput); //attribute value
        switch(checkAction) {
            case "StrictlyGreater": status = checkValue > valueInputInt; break;
            case "StrictlySmaller": status = checkValue < valueInputInt; break;
            case "GreaterEqual": status = checkValue >= valueInputInt; break;
            case "SmallerEqual": status = checkValue <= valueInputInt; break;
            case "LengthStrictlyGreater": status = length > checkValue; break;
            case "LengthStrictlySmaller": status = length < checkValue; break;
            case "LengthGreaterEqual": status = length >= checkValue; break;
            case "LengthSmallerEqual": status = length <= checkValue; break;
        }
        result = true ? Result.passed : Result.failed;
        return result;
    }

    private Result checksNull(String attributeValue, Check check){
       Result passed = null; //change it to enum
        String checkAction = check.getActionValueType().getAction();
        switch(checkAction) {
            case "Empty": passed = attributeValue.isEmpty() ? Result.passed : Result.failed; break;
            case "NotEmpty": passed = (! (attributeValue.isEmpty())) ? Result.passed : Result.failed; break;
            case "HumanCheck": passed = Result.humanCheck; break;
        }
        return passed;
    }


    private Result checksString(String attributeValue, Check check) {
        Result result = Result.humanCheck;
        String value = check.getValue();
        String checkAction = check.getActionValueType().getAction();
        switch(checkAction) {
            case "Contains": if (attributeValue.contains(value)) {result = Result.passed;} else {result = Result.failed;} break;
            case "NotContains": if (!attributeValue.contains(value)) {result = Result.passed;} else {result = Result.failed;} break;
            case "IsEqual": if (attributeValue.equals(value)) {result = Result.passed;} else {result = Result.failed;} break;
            case "IsNotEqual": if (!attributeValue.equals(value)) {result = Result.passed;} else {result = Result.failed;} break;
        }
        return result;
    }

}
