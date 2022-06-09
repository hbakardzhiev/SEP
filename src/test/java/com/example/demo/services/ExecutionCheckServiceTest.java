package com.example.demo.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.demo.UtilTests;
import com.example.demo.modules.*;
import com.example.demo.repository.CheckRepository;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

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
    // given

    Check checkTest = new Check("Check 1", "Change Notice", "name", "null", "comment");
    Action actionType = new Action("NotEmpty", "", "pls1");
    actionType.add(checkTest);

    ActionNameString actionTest = new ActionNameString("NotEmpty");
    DateExecutedChecks expected = new DateExecutedChecks(null,
            List.of(new AbstractMap.SimpleEntry<>("output",
                    new ExecutedCheckOutput(Result.passed, "CN title name",
                            new CheckAndActionName(checkTest, actionTest)))));

    given(checkRepository.findAll()).willReturn(List.of(checkTest));
    given(parserService.parseEverything(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML)).willReturn(
        new SimpleImmutableEntry<>(List.of(new SimpleImmutableEntry<>(
            "Change Notice - CN000001, CN title name, E0011 LocationId002, A",
            new SimpleImmutableEntry<>("name", "CN title name"))), null));

    // when
    var actual = underTest.filterDataWithChecks(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML);

    // then: verifies that the findAll, parsedEverything were invoked and check the result
    verify(checkRepository).findAll();
    verify(parserService).parseEverything(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML);
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void filterDataWithChecksFailed() throws IOException {
    // given: name is put to empty to check whether the check will fail
    Check checkTest = new Check("Check 1", "Change Notice", "name", "null", "comment");
    Action actionType = new Action("NotEmpty", "", "pls1");
    actionType.add(checkTest);

    ActionNameString actionTest = new ActionNameString("NotEmpty");
    DateExecutedChecks expected = new DateExecutedChecks(null,
            List.of(new AbstractMap.SimpleEntry<>("output",
        new ExecutedCheckOutput(Result.failed, "",
                new CheckAndActionName(checkTest, actionTest)))));

    given(checkRepository.findAll()).willReturn(List.of(checkTest));
    given(parserService.parseEverything(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML)).willReturn(
        new AbstractMap.SimpleImmutableEntry<>(List.of(new AbstractMap.SimpleImmutableEntry<>(
            "Change Notice - CN000001, CN title name, E0011 LocationId002, A",
            new AbstractMap.SimpleImmutableEntry<>("name", ""))), null));

    // when
    var actual = underTest.filterDataWithChecks(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML);

    // then: verifies that the findAll, parsedEverything were invoked and check the result
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void filterDataWithChecksHuman() throws IOException {
    // given: name is put to empty to check whether the check will fail
    OffsetDateTime timeGiven = OffsetDateTime.now();
    Check checkTest = new Check("Check 1", "Change Notice", "description", "null", "comment");
    Action actionType = new Action("HumanCheck", "", "pls1");
    actionType.add(checkTest);

    ActionNameString actionTest = new ActionNameString("HumanCheck");
    DateExecutedChecks expected = new DateExecutedChecks(timeGiven,
            List.of(new AbstractMap.SimpleEntry<>("output",
            new ExecutedCheckOutput(Result.humanCheck, "",
                    new CheckAndActionName(checkTest, actionTest)))));


    given(checkRepository.findAll()).willReturn(List.of(checkTest));
    given(parserService.parseEverything(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML)).willReturn(
        new AbstractMap.SimpleImmutableEntry<>(List.of(new AbstractMap.SimpleImmutableEntry<>(
            "Change Notice - CN000001, CN title name, E0011 LocationId002, A",
            new AbstractMap.SimpleImmutableEntry<>("description", ""))), timeGiven));

    // when
    var actual = underTest.filterDataWithChecks(UtilTests.CHANGE_NOTICE_EXAMPLE_HTML);

    // then: verifies that the findAll, parsedEverything were invoked and check the result
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }
}
