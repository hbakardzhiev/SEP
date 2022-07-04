package com.example.demo.services;

import com.example.demo.modules.*;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.SheetSourceRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CheckWrapperService.class})
@ExtendWith(SpringExtension.class)
class CheckWrapperServiceTest {
  @MockBean private ActionService actionService;

  @MockBean private AdminRepository adminRepository;

  @MockBean private CheckRepository checkRepository;

  @Autowired private CheckWrapperService checkWrapperService;

  @MockBean private SheetSourceRepository sheetSourceRepository;

  @Autowired private CheckWrapperService checkWrapperServiceunderTest;

  @MockBean private CheckService checkService;

  @BeforeEach
  void setUp() {
    checkWrapperServiceunderTest =
        new CheckWrapperService(
            actionService, checkRepository, adminRepository, sheetSourceRepository);
    final var securityContext = mock(SecurityContext.class);
    final var applicationUser = mock(UserDetails.class);
    final var authentication = mock(Authentication.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .thenReturn(applicationUser);
    final var admin = new Admin();
    when(adminRepository.findAdminByUsername(applicationUser.toString())).thenReturn(admin);
  }

  @Test
  void save() {
    // given
    Check checkToBeSaved = new Check("Check 1", "CN", "description", "Philips", "comment", 2L);

    // when
    checkWrapperServiceunderTest.save(checkToBeSaved);

    // then: verifies that the save method was invoked with the checkToBeSaved
    ArgumentCaptor<Check> checkArgumentCaptor = ArgumentCaptor.forClass(Check.class);

    // captures the check instance that was used upon saving
    // and verifies that the save method was invoked
    verify(checkRepository).save(checkArgumentCaptor.capture());

    // retrieved the captured check
    Check capturedCheck = checkArgumentCaptor.getValue();

    // check that the captured check is the one that supposed to be saved
    assertThat(capturedCheck).isEqualTo(checkToBeSaved);
  }

  /** Method under test: {@link CheckWrapperService#extractCheck(CheckAndActionName)} */
  @Test
  void testExtractCheck() {
    final var check = new Check("CN_description", "CN", "description", "Philips", "comment");
    final var checkAndActionName = mock(CheckAndActionName.class);
    ActionNameString actionName = new ActionNameString("blaa");
    checkAndActionName.setActionName(actionName);
    when(checkAndActionName.getTheCheck()).thenReturn(check);
    when(checkAndActionName.getActionName()).thenReturn(actionName);
    Action action = mock(Action.class);
    when(actionService.findByName(actionName.getActionName())).thenReturn(action);

    this.checkWrapperService.extractCheck(checkAndActionName);
  }

  /** Method under test: {@link CheckWrapperService#extractCheck(CheckAndActionName)} */
  @Test
  void testExtractCheck2() {
    final var check = new Check("CN_description", "CN", "description", "Philips", "comment");
    final var checkAndActionName = mock(CheckAndActionName.class);
    ActionNameString actionName = new ActionNameString("blaa");
    checkAndActionName.setActionName(actionName);
    when(checkAndActionName.getTheCheck()).thenReturn(check);
    when(checkAndActionName.getActionName()).thenReturn(actionName);

    Action action = new Action();
    action.setAction("Action");
    action.setChecks(new ArrayList<>());
    action.setDescription("The characteristics of someone or something");
    action.setValueType("42");
    when(this.actionService.findByName((String) any())).thenReturn(action);

    this.checkWrapperService.extractCheck(checkAndActionName);
  }

  /** Method under test: {@link CheckWrapperService#extractCheck(CheckAndActionName)} */
  @Test
  void testExtractCheck3() {
    final var check = new Check("CN_description", "CN", "description", "Philips", "comment");
    final var checkAndActionName = mock(CheckAndActionName.class);
    ActionNameString actionName = new ActionNameString("blaa");
    checkAndActionName.setActionName(actionName);
    when(checkAndActionName.getTheCheck()).thenReturn(check);
    when(checkAndActionName.getActionName()).thenReturn(actionName);

    Action action = mock(Action.class);
    doNothing().when(action).add((Check) any());
    doNothing().when(action).setAction((String) any());
    doNothing().when(action).setChecks((java.util.List<Check>) any());
    doNothing().when(action).setDescription((String) any());
    doNothing().when(action).setValueType((String) any());
    action.setAction("Action");
    action.setChecks(new ArrayList<>());
    action.setDescription("The characteristics of someone or something");
    action.setValueType("42");
    when(this.actionService.findByName((String) any())).thenReturn(action);

    this.checkWrapperService.extractCheck(checkAndActionName);
  }
}
