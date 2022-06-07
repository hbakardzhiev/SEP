package com.example.demo.services;

import com.example.demo.modules.Action;
import com.example.demo.repository.ActionRepository;
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
public class ActionServiceTest {

  @Mock private ActionRepository actionRepository;
  private ActionService underTest;

  @BeforeEach
  void setUp() {
    underTest = new ActionService(actionRepository);
  }

  @Test
  void canFindAll() {

    List<Action> actions = underTest.findAll();

    System.out.println(actions);

    verify(actionRepository).findAll();
  }

  @Test
  void canFindByName() {

    String name = "Test";
    Action actionToBeSaved = new Action("Test", "String", "Desc");
    Optional<Action> optionalAction = Optional.of(actionToBeSaved);

    given(actionRepository.findById(name)).willReturn(optionalAction);

    Action returnedAction = underTest.findByName(name);

    assertThat(returnedAction).isEqualTo(actionToBeSaved);
  }

  @Test
  @Disabled
  void canFindByNameException() {

    String name = "Not_exist_test";
    Optional<Action> optionalAction = Optional.empty();

    given(actionRepository.findById(name)).willReturn(optionalAction);

    assertThatThrownBy(() -> underTest.findByName(name))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("Action not found " + name);
  }

  @Test
  void save() {

    Action actionToBeSaved = new Action("Check 1", "Integer", "Description");

    underTest.save(actionToBeSaved);

    ArgumentCaptor<Action> actionArgumentCaptor = ArgumentCaptor.forClass(Action.class);

    verify(actionRepository).save(actionArgumentCaptor.capture());

    Action capturedAction = actionArgumentCaptor.getValue();

    assertThat(capturedAction).isEqualTo(actionToBeSaved);
  }

  @Test
  void deleteByName() {

    String name = "Test";

    underTest.deleteByName(name);

    ArgumentCaptor<String> actionArgumentCaptor = ArgumentCaptor.forClass(String.class);

    verify(actionRepository).deleteById(actionArgumentCaptor.capture());

    String capturedName = actionArgumentCaptor.getValue();

    assertThat(capturedName).isEqualTo(name);
  }
}
