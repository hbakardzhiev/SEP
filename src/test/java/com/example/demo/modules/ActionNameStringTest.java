package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ActionNameStringTest {
    /**
     * Method under test: {@link ActionNameString#ActionNameString()}
     */
    @Test
    void testConstructor() {
        assertNull((new ActionNameString()).getActionName());
        assertEquals("Action Name", (new ActionNameString("Action Name")).getActionName());
    }

    /**
     * Method under test: {@link ActionNameString#equals(Object)}
     */
    @Test
    void testEquals() {
        assertNotEquals(new ActionNameString("Action Name"), null);
        assertNotEquals(new ActionNameString("Action Name"), "Different type to ActionNameString");
    }

    /**
     * Method under test: {@link ActionNameString#equals(Object)}
     */
    @Test
    void testEquals2() {
        ActionNameString actionNameString = new ActionNameString("Action Name");
        assertEquals(actionNameString, actionNameString);
        int expectedHashCodeResult = actionNameString.hashCode();
        assertEquals(expectedHashCodeResult, actionNameString.hashCode());
    }

    /**
     * Method under test: {@link ActionNameString#equals(Object)}
     */
    @Test
    void testEquals3() {
        ActionNameString actionNameString = new ActionNameString("Action Name");
        ActionNameString actionNameString1 = new ActionNameString("Action Name");
        assertEquals(actionNameString, actionNameString1);
        int notExpectedHashCodeResult = actionNameString.hashCode();
        assertNotEquals(notExpectedHashCodeResult, actionNameString1.hashCode());
    }

    /**
     * Method under test: {@link ActionNameString#equals(Object)}
     */
    @Test
    void testEquals4() {
        ActionNameString actionNameString = new ActionNameString(null);
        assertNotEquals(actionNameString, new ActionNameString("Action Name"));
    }
}

