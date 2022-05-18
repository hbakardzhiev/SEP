package com.example.demo;

import com.example.demo.modules.Check2;
import com.example.demo.repository.CheckRepository;
import com.example.demo.services.CheckService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CheckServiceTest {

    @Mock
    private CheckRepository checkRepository;
    private CheckService underTest;

    @BeforeEach
    void setUp(){
        underTest = new CheckService(checkRepository);
    }

    @Test
    void canFindAll() {
        //when
        List<Check2> checks = underTest.findAll();

        System.out.println(checks);
        //then: verifies that the findAll method was invoked
        verify(checkRepository).findAll();

    }

    @Test
    void canFindByName() {
        //given
        String name = "CN_description";
        Check2 checkToBeSaved = new Check2(
                "CN_description",
                "CN",
                "description",
                "Philips",
                "comment");
        Optional<Check2> optionalCheck = Optional.of(checkToBeSaved);

        given(checkRepository.findById(name))
                .willReturn(optionalCheck);

        //when
        Check2 returnedCheck = underTest.findByName(name);

        //then:
        assertThat(returnedCheck).isEqualTo(checkToBeSaved);
    }

    @Test
    void canFindByNameException() {
        //given
        String name = "CN_description";
        Optional<Check2> optionalCheck = Optional.empty();

        //upon triggering the method findById on the
        // repository will return optionalCheck
        given(checkRepository.findById(name))
                .willReturn(optionalCheck);


       //then
        assertThatThrownBy(() -> underTest.findByName(name))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Check not found " + name);
    }

    @Test
    void save() {
        //given
        Check2 checkToBeSaved = new Check2(
             "Check 1",
                "CN",
                "description",
                "Philips",
                "comment");

        //when
        underTest.save(checkToBeSaved);

        //then: verifies that the save method was invoked with the checkToBeSaved
        ArgumentCaptor<Check2> checkArgumentCaptor =
                ArgumentCaptor.forClass(Check2.class);

        //captures the check instance that was used upon saving
        // and verifies that the save method was invoked
        verify(checkRepository).save(checkArgumentCaptor.capture());

        //retrieved the captured check
        Check2 capturedCheck = checkArgumentCaptor.getValue();

        //check that the captured check is the one that supposed to be saved
        assertThat(capturedCheck).isEqualTo(checkToBeSaved);
    }

    @Test
    void deleteByName() {
        //given
        String name = "CN_description";

        //when
        underTest.deleteByName(name);

        //then: verifies that the save method was invoked with the checkToBeSaved
        ArgumentCaptor<String> checkArgumentCaptor =
                ArgumentCaptor.forClass(String.class);

        //captures the check instance that was used upon saving
        // and verifies that the save method was invoked
        verify(checkRepository).deleteById(checkArgumentCaptor.capture());

        //retrieved the captured check
        String capturedName = checkArgumentCaptor.getValue();

        //check that the captured check is the one that supposed to be saved
        assertThat(capturedName).isEqualTo(name);
    }
}