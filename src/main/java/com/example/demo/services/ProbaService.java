package com.example.demo.services;

import com.example.demo.modules.Check;
import com.example.demo.modules.CheckInputValue;
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

    public List<List<AbstractMap.SimpleEntry<String,CheckInputValue>>> filterDataWithChecks () throws IOException {
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
                String status = executeTheCheck(checkInputValue);
                return new AbstractMap.SimpleEntry<String, CheckInputValue>(status, checkInputValue);
            });

            return checkedChecks.collect(Collectors.toList());
//
        });
        return relevantChecksVal.collect(Collectors.toList());

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

    private String executeTheCheck(CheckInputValue checkInputValue) {
        Check check = checkInputValue.getCheck();
        String value = checkInputValue.getInputValue();
        String checkValue = check.getValue();

        String label = null;
        switch (checkValue) {
            case "null": label = checksNull(value, check); // front end will give us ""
//            case "Integer":
//            case "String":
        }
        return label;
    }

    private String checksNull(String attributeValue, Check check){
        String passed = null; //change it to enum
        String checkAction = check.getActionValueType().getAction();
        switch(checkAction) {
            case "Empty": passed = String.valueOf(attributeValue.isEmpty());
            case "NotEmpty": passed = String.valueOf(! (attributeValue.isEmpty()));
//            case "HumanCheck":
        }
        return passed;
    }

//    if checks are with value integer or value String

}
