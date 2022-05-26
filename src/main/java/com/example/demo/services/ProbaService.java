package com.example.demo.services;

import com.example.demo.modules.Check;
import com.example.demo.modules.CheckInputValue;
import com.example.demo.modules.Result;
import com.example.demo.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProbaService {

    @Autowired
    private ParserService perserService;

    @Autowired
    private CheckRepository checkRepository;

    public List<AbstractMap.SimpleEntry<Result,CheckInputValue>> filterDataWithChecks () throws IOException {
        List<Check> checks = checkRepository.findAll();
        var data = perserService.parseCN();
        final var relevantChecksVal = data.stream().map(element -> {
            final var docSource = element.getKey().split("-")[0];//TODO: change it
            final var attribute = element.getValue().getKey();

            //list of checks relevant for this docSource and attribute
            List<Check> relCheck = checks.stream().filter(check -> Objects.equals(check.getDocSource(), docSource)
                            && Objects.equals(check.getAttribute(), attribute)).collect(Collectors.toList());

            var checkedChecks = relCheck.stream().map( check -> {
                CheckInputValue checkInputValue = new CheckInputValue(element.getValue().getValue(), check);
                Result status = executeTheCheck(checkInputValue);
                return new AbstractMap.SimpleEntry<Result, CheckInputValue>(status, checkInputValue);
            });

            return checkedChecks.collect(Collectors.toList());
//
        });
        return relevantChecksVal.flatMap(List::stream).collect(Collectors.toList());

    }

//    private  List<AbstractMap.SimpleEntry<List<CheckInputValue>, String>>
//                    executeChecks(Stream<AbstractMap.SimpleEntry<List<Check>, String>> relevantChecksValue) {
//
//        var checkCategories = relevantChecksValue.map(simpleEntry -> {
//            //retrieve the relevant checks and the attribute value
//            List<Check> relevantChecks = simpleEntry.getKey();
//            String attributeValue = simpleEntry.getValue();
//
//            //execute each of the checks on the attribute value in a separate method
//            Stream<CheckCategory> checkCategories1 = relevantChecks.stream()
//                    .map(check -> {
//                        CheckCategory checkCategory = executeTheCheck(attributeValue, check);
//                        return checkCategory;
//                    });
//            return new AbstractMap.SimpleEntry<List<CheckCategory>,String>(
//                    checkCategories1.collect(Collectors.toList()),attributeValue);
//            });
//        return checkCategories.collect(Collectors.toList());
//    }

    private Result executeTheCheck(CheckInputValue checkInputValue) {
        Check check = checkInputValue.getCheck();
        String value = checkInputValue.getInputValue(); // this is inputValue
        String actionValue = check.getActionValueType().getValueType();

        Result label = null;
        switch (actionValue) {
            case "null": label = checksNull(value, check); break;
            // front end will give us ""
            case "String": label = checksString(value, check); break;
            case "Integer": label = checksInteger(value, check); break;
        }
        return label;
    }

    private Result checksInteger(String valueInput, Check check) { //InputValue and a check
        Result result = null;
        boolean status;
        String checkAction = check.getActionValueType().getAction();
        Integer length = valueInput.length();
//        if(check.getValue() == null){
//            throw
//        }
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
        }
        return result;
    }

}
