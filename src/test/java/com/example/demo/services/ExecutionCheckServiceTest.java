package com.example.demo.services;

import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.SheetSourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExecutionCheckServiceTest {

    @Autowired
    private ExecutionCheckService underTest;

    @Mock
    private CheckRepository checkRepository;

    @Mock
    private ParserService parserService;

    @BeforeEach
    void setUp() {
        underTest = new ExecutionCheckService(parserService, checkRepository);
    }

    @Test
    void filterDataWithChecksPassed() throws IOException {
        //given
        List<AbstractMap.SimpleEntry< Result, CheckInputValue >> expected  =
                new ArrayList<>();
        Check checkTest = new Check("Check 1", "Change Notice", "name", "null", "comment");
        ActionValueType actionType = new ActionValueType("NotEmpty", "", "pls1");
        actionType.add(checkTest);

        ActionNameString actionTest = new ActionNameString("NotEmpty");
        expected.add(new AbstractMap.SimpleEntry<>(Result.passed,
                new CheckInputValue("CN title name", new CheckAndActionName(checkTest, actionTest))));

        given(checkRepository.findAll()).willReturn(List.of(checkTest));
        given(parserService.parseEverything()).willReturn(List.of(new AbstractMap.SimpleImmutableEntry<>(
                "Change Notice - CN000001, CN title name, E0011 LocationId002, A", new AbstractMap.SimpleImmutableEntry<>(
                        "name", "CN title name"
        )
        )));

        //when
        var actual = underTest.filterDataWithChecks();

        //then: verifies that the findAll, parsedEverything were invoked and check the result
        verify(checkRepository).findAll();
        verify(parserService).parseEverything();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void filterDataWithChecksFailed() throws IOException {
        //given: name is put to empty to check whether the check will fail
        List<AbstractMap.SimpleEntry< Result, CheckInputValue >> expected  =
                new ArrayList<>();
        Check checkTest = new Check("Check 1", "Change Notice", "name", "null", "comment");
        ActionValueType actionType = new ActionValueType("NotEmpty", "", "pls1");
        actionType.add(checkTest);

        ActionNameString actionTest = new ActionNameString("NotEmpty");
        expected.add(new AbstractMap.SimpleEntry<>(Result.failed,
                new CheckInputValue("", new CheckAndActionName(checkTest, actionTest))));

        given(checkRepository.findAll()).willReturn(List.of(checkTest));
        given(parserService.parseEverything()).willReturn(List.of(new AbstractMap.SimpleImmutableEntry<>(
                "Change Notice - CN000001, CN title name, E0011 LocationId002, A", new AbstractMap.SimpleImmutableEntry<>(
                "name", ""
        )
        )));

        //when
        var actual = underTest.filterDataWithChecks();

        //then: verifies that the findAll, parsedEverything were invoked and check the result
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void filterDataWithChecksHuman() throws IOException {
        //given: name is put to empty to check whether the check will fail
        List<AbstractMap.SimpleEntry< Result, CheckInputValue >> expected  =
                new ArrayList<>();
        Check checkTest = new Check("Check 1", "Change Notice", "description", "null", "comment");
        ActionValueType actionType = new ActionValueType("HumanCheck", "", "pls1");
        actionType.add(checkTest);

        ActionNameString actionTest = new ActionNameString("HumanCheck");
        expected.add(new AbstractMap.SimpleEntry<>(Result.humanCheck,
                new CheckInputValue("", new CheckAndActionName(checkTest, actionTest))));

        given(checkRepository.findAll()).willReturn(List.of(checkTest));
        given(parserService.parseEverything()).willReturn(List.of(new AbstractMap.SimpleImmutableEntry<>(
                "Change Notice - CN000001, CN title name, E0011 LocationId002, A", new AbstractMap.SimpleImmutableEntry<>(
                "description", ""
        )
        )));

        //when
        var actual = underTest.filterDataWithChecks();

        //then: verifies that the findAll, parsedEverything were invoked and check the result
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}