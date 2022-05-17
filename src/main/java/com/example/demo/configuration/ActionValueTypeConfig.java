package com.example.demo.configuration;

import com.example.demo.modules.ActionTypes;
import com.example.demo.modules.ActionValueType;
import com.example.demo.modules.Check2;
import com.example.demo.repository.ActionValueTypeRepository;
import com.example.demo.services.ActionValueTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ActionValueTypeConfig {

    @Bean
    CommandLineRunner commandLineRunnerActionTypes(
            ActionValueTypeRepository repository) {
        return args -> {
            List<ActionValueType> actions = Arrays.asList(
                    new ActionValueType(
                            "Empty",
                            null, "This attribute value should be empty"),
                    new ActionValueType(
                           "NotEmpty",
                            null, "Should not be empty, pls"),
                    new ActionValueType(
                            "Contains",
                            "String", "The specified value should be contained in the attribute value")
//                    new ActionValueType(
//                            ActionTypes.NotContains,
//                            "String"),
//                    new ActionValueType(
//                            ActionTypes.StrictlyGreater,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.StrictlySmaller,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.GreaterEqual,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.SmallerEqual,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.LengthStrictlyGreater,
//                            "String"),
//                    new ActionValueType(
//                            ActionTypes.LengthStrictlySmaller,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.LengthGreaterEqual,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.LengthSmallerEqual,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.DifferentAttrValue,
//                            "Integer"),
//                    new ActionValueType(
//                            ActionTypes.SameAttrValue,
//                            "String"),
//                    new ActionValueType(
//                            ActionTypes.HumanCheck,
//                            null)
            );

            repository.saveAll(actions);
        };
    }
}