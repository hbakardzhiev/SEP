package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AdminTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Admin#Admin(String, String)}
     *   <li>{@link Admin#setId(Long)}
     *   <li>{@link Admin#setPassword(String)}
     *   <li>{@link Admin#setUsername(String)}
     *   <li>{@link Admin#getAdminRole()}
     *   <li>{@link Admin#getId()}
     *   <li>{@link Admin#getPassword()}
     *   <li>{@link Admin#getUsername()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Admin actualAdmin = new Admin("janedoe", "iloveyou");
        actualAdmin.setId(123L);
        actualAdmin.setPassword("iloveyou");
        actualAdmin.setUsername("janedoe");
        assertNull(actualAdmin.getAdminRole());
        assertEquals(123L, actualAdmin.getId().longValue());
        assertEquals("iloveyou", actualAdmin.getPassword());
        assertEquals("janedoe", actualAdmin.getUsername());
    }
}

