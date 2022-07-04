package com.example.demo.services;

import com.example.demo.modules.Admin;
import com.example.demo.modules.Check;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CheckRepository;
import com.example.demo.repository.SheetSourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckServiceTest {

  @Mock private CheckRepository checkRepository;
  @Mock private SheetSourceRepository sheetSourceRepository;
  @Mock private AdminRepository adminRepository;
  @Mock private ActionService actionService;
  @Autowired private CheckService underTest;
  @Autowired private CheckWrapperService checkWrapperServiceunderTest;

  @BeforeEach
  void setUp() {
    underTest = new CheckService(checkRepository);
    checkWrapperServiceunderTest =
        new CheckWrapperService(
            actionService, checkRepository, adminRepository, sheetSourceRepository);
  }

  @Test
  void canFindAll() {
    // when
    List<Check> checks = underTest.findAll();

    System.out.println(checks);
    // then: verifies that the findAll method was invoked
    verify(checkRepository).findAll();
  }

  @Test
  void canFindByName() {
    // given
    String name = "CN_description";
    Check checkToBeSaved =
        new Check("CN_description", "CN", "description", "Philips", "comment", 1L);
    Optional<Check> optionalCheck = Optional.of(checkToBeSaved);

    given(checkRepository.findById(name)).willReturn(optionalCheck);

    // when
    Check returnedCheck = underTest.findByName(name);

    // then:
    assertThat(returnedCheck).isEqualTo(checkToBeSaved);
  }

  @Test
  @Disabled
  void save() {
    // given
    Check checkToBeSaved = new Check("Check 1", "CN", "description", "Philips", "comment", 2L);
    final var securityContext = mock(SecurityContext.class);
    final var applicationUser = mock(UserDetails.class);
    final var authentication = mock(Authentication.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
    when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .thenReturn(applicationUser);
    final var admin = new Admin();
    when(adminRepository.findAdminByUsername(applicationUser.toString())).thenReturn(admin);
    //    when(admin.getId()).thenReturn(123L);

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

  @Test
  void deleteByName() {
    // given
    String name = "CN_description";
    Check checkProba = new Check("CN_description", "Change notice", "name", "null", "blaaa");
    given(checkRepository.findById(name)).willReturn(Optional.of(checkProba));

    // when
    underTest.deleteByName(name);

    // then: verifies that the save method was invoked with the checkToBeSaved
    ArgumentCaptor<String> checkArgumentCaptor = ArgumentCaptor.forClass(String.class);

    // captures the check instance that was used upon saving
    // and verifies that the save method was invoked
    verify(checkRepository).deleteById(checkArgumentCaptor.capture());

    // retrieved the captured check
    String capturedName = checkArgumentCaptor.getValue();

    // check that the captured check is the one that supposed to be saved
    assertThat(capturedName).isEqualTo(name);
  }
}
