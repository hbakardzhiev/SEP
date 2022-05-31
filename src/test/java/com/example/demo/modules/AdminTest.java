package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AdminTest {
  /** Method under test: {@link Admin#Admin(String, String, String)} */
  @Test
  void testConstructor() {
    Admin actualAdmin = new Admin("janedoe", "jane.doe@example.org", "iloveyou");

    assertEquals("ADMIN", actualAdmin.getAdminRole());
    assertEquals("janedoe", actualAdmin.getUsername());
    assertEquals("iloveyou", actualAdmin.getPassword());
    assertNull(actualAdmin.getId());
    assertEquals("jane.doe@example.org", actualAdmin.getEmail());
  }
}
