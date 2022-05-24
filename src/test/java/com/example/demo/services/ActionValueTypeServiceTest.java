package com.example.demo.services;

import com.example.demo.modules.ActionValueType;
import com.example.demo.repository.ActionValueTypeRepository;
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
public class ActionValueTypeServiceTest {

  @Mock private ActionValueTypeRepository actionValueTypeRepository;
  private ActionValueTypeService underTest;

  @BeforeEach
  void setUp() {
    underTest = new ActionValueTypeService(actionValueTypeRepository);
  }

  @Test
  void canFindAll() {

    List<ActionValueType> actions = underTest.findAll();

    System.out.println(actions);

    verify(actionValueTypeRepository).findAll();
  }

  @Test
  void canFindByName() {

    String name = "Test";
    ActionValueType actionToBeSaved = new ActionValueType("Test", "String", "Desc");
    Optional<ActionValueType> optionalAction = Optional.of(actionToBeSaved);

    given(actionValueTypeRepository.findById(name)).willReturn(optionalAction);

    ActionValueType returnedAction = underTest.findByName(name);

    assertThat(returnedAction).isEqualTo(actionToBeSaved);
  }

  @Test
  @Disabled
  void canFindByNameException() {

    String name = "Not_exist_test";
    Optional<ActionValueType> optionalAction = Optional.empty();

    given(actionValueTypeRepository.findById(name)).willReturn(optionalAction);

    assertThatThrownBy(() -> underTest.findByName(name))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("Action not found " + name);
  }

  @Test
  void save() {

    ActionValueType actionToBeSaved = new ActionValueType("Check 1", "Integer", "Description");

    underTest.save(actionToBeSaved);

    ArgumentCaptor<ActionValueType> actionArgumentCaptor =
        ArgumentCaptor.forClass(ActionValueType.class);

    verify(actionValueTypeRepository).save(actionArgumentCaptor.capture());

    ActionValueType capturedAction = actionArgumentCaptor.getValue();

    assertThat(capturedAction).isEqualTo(actionToBeSaved);
  }

  @Test
  void deleteByName() {

    String name = "Test";

    underTest.deleteByName(name);

    ArgumentCaptor<String> actionArgumentCaptor = ArgumentCaptor.forClass(String.class);

    verify(actionValueTypeRepository).deleteById(actionArgumentCaptor.capture());

    String capturedName = actionArgumentCaptor.getValue();

    assertThat(capturedName).isEqualTo(name);
  }
}
