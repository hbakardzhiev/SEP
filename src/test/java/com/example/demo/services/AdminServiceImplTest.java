package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.modules.Admin;
import com.example.demo.repository.AdminRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminServiceImplTest {
  @MockBean private AdminRepository adminRepository;

  @Autowired private AdminServiceImpl adminServiceImpl;

  @MockBean private PasswordEncoder passwordEncoder;

  /** Method under test: {@link AdminServiceImpl#addAdmin(Admin)} */
  @Test
  void testAddAdmin() {
    when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

    Admin admin = new Admin();
    admin.setAdminRole("Admin Role");
    admin.setEmail("jane.doe@example.org");
    admin.setId(123L);
    admin.setPassword("iloveyou");
    admin.setUsername("janedoe");
    when(this.adminRepository.save((Admin) any())).thenReturn(admin);

    Admin admin1 = new Admin();
    admin1.setAdminRole("Admin Role");
    admin1.setEmail("jane.doe@example.org");
    admin1.setId(123L);
    admin1.setPassword("iloveyou");
    admin1.setUsername("janedoe");
    assertSame(admin, this.adminServiceImpl.addAdmin(admin1));
    verify(this.passwordEncoder).encode((CharSequence) any());
    verify(this.adminRepository).save((Admin) any());
    assertEquals("ADMIN", admin1.getAdminRole());
    assertEquals("secret", admin1.getPassword());
  }

  /** Method under test: {@link AdminServiceImpl#addAdmin(Admin)} */
  @Test
  void testAddAdmin2() {
    when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
    when(this.adminRepository.save((Admin) any()))
        .thenThrow(new UsernameNotFoundException("ADMIN"));

    Admin admin = new Admin();
    admin.setAdminRole("Admin Role");
    admin.setEmail("jane.doe@example.org");
    admin.setId(123L);
    admin.setPassword("iloveyou");
    admin.setUsername("janedoe");
    assertThrows(UsernameNotFoundException.class, () -> this.adminServiceImpl.addAdmin(admin));
    verify(this.passwordEncoder).encode((CharSequence) any());
    verify(this.adminRepository).save((Admin) any());
  }

  /** Method under test: {@link AdminServiceImpl#getAdminById(Long)} */
  @Test
  void testGetAdminById() {
    Admin admin = new Admin();
    admin.setAdminRole("Admin Role");
    admin.setEmail("jane.doe@example.org");
    admin.setId(123L);
    admin.setPassword("iloveyou");
    admin.setUsername("janedoe");
    when(this.adminRepository.getById((Long) any())).thenReturn(admin);
    assertSame(admin, this.adminServiceImpl.getAdminById(123L));
    verify(this.adminRepository).getById((Long) any());
  }

  /** Method under test: {@link AdminServiceImpl#getAdminById(Long)} */
  @Test
  void testGetAdminById2() {
    when(this.adminRepository.getById((Long) any()))
        .thenThrow(new UsernameNotFoundException("Msg"));
    assertThrows(UsernameNotFoundException.class, () -> this.adminServiceImpl.getAdminById(123L));
    verify(this.adminRepository).getById((Long) any());
  }

  /** Method under test: {@link AdminServiceImpl#deleteAdmin(Long)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testDeleteAdmin() throws IllegalAccessException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "org.springframework.security.core.Authentication.getPrincipal()" because the return value of
    // "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
    //       at com.example.demo.services.AdminServiceImpl.deleteAdmin(AdminServiceImpl.java:53)
    //   In order to prevent deleteAdmin(Long)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   deleteAdmin(Long).
    //   See https://diff.blue/R013 to resolve this issue.

    this.adminServiceImpl.deleteAdmin(123L);
  }

  /** Method under test: {@link AdminServiceImpl#getAdmins()} */
  @Test
  void testGetAdmins() {
    ArrayList<Admin> adminList = new ArrayList<>();
    when(this.adminRepository.findAll()).thenReturn(adminList);
    List<Admin> actualAdmins = this.adminServiceImpl.getAdmins();
    assertSame(adminList, actualAdmins);
    assertTrue(actualAdmins.isEmpty());
    verify(this.adminRepository).findAll();
  }

  /** Method under test: {@link AdminServiceImpl#getAdmins()} */
  @Test
  void testGetAdmins2() {
    when(this.adminRepository.findAll()).thenThrow(new UsernameNotFoundException("Msg"));
    assertThrows(UsernameNotFoundException.class, () -> this.adminServiceImpl.getAdmins());
    verify(this.adminRepository).findAll();
  }

  /** Method under test: {@link AdminServiceImpl#loadUserByUsername(String)} */
  @Test
  void testLoadUserByUsername() throws UsernameNotFoundException {
    Admin admin = new Admin();
    admin.setAdminRole("Admin Role");
    admin.setEmail("jane.doe@example.org");
    admin.setId(123L);
    admin.setPassword("iloveyou");
    admin.setUsername("janedoe");
    when(this.adminRepository.findAdminByUsername((String) any())).thenReturn(admin);
    UserDetails actualLoadUserByUsernameResult =
        this.adminServiceImpl.loadUserByUsername("janedoe");
    assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
    assertTrue(actualLoadUserByUsernameResult.isEnabled());
    assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
    assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
    assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
    assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
    assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
    verify(this.adminRepository).findAdminByUsername((String) any());
  }

  /** Method under test: {@link AdminServiceImpl#loadUserByUsername(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testLoadUserByUsername2() throws UsernameNotFoundException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.IllegalArgumentException: Cannot pass null or empty values to constructor
    //       at org.springframework.util.Assert.isTrue(Assert.java:121)
    //       at org.springframework.security.core.userdetails.User.<init>(User.java:110)
    //       at org.springframework.security.core.userdetails.User.<init>(User.java:87)
    //       at
    // com.example.demo.services.AdminServiceImpl.loadUserByUsername(AdminServiceImpl.java:96)
    //   In order to prevent loadUserByUsername(String)
    //   from throwing IllegalArgumentException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   loadUserByUsername(String).
    //   See https://diff.blue/R013 to resolve this issue.

    Admin admin = mock(Admin.class);
    when(admin.getPassword()).thenReturn("iloveyou");
    when(admin.getUsername()).thenReturn("");
    doNothing().when(admin).setAdminRole((String) any());
    doNothing().when(admin).setEmail((String) any());
    doNothing().when(admin).setId((Long) any());
    doNothing().when(admin).setPassword((String) any());
    doNothing().when(admin).setUsername((String) any());
    admin.setAdminRole("Admin Role");
    admin.setEmail("jane.doe@example.org");
    admin.setId(123L);
    admin.setPassword("iloveyou");
    admin.setUsername("janedoe");
    when(this.adminRepository.findAdminByUsername((String) any())).thenReturn(admin);
    this.adminServiceImpl.loadUserByUsername("janedoe");
  }
}
