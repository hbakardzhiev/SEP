package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class ExecutedCheckOutputTest {
    /**
     * Method under test: default or parameterless constructor of {@link ExecutedCheckOutput}
     */
    @Test
    void testConstructor() {
        CheckAndActionName checkAndActionName = new CheckAndActionName();
        ExecutedCheckOutput actualExecutedCheckOutput = new ExecutedCheckOutput(Result.failed, "42", checkAndActionName);

        CheckAndActionName checkAndActionName1 = actualExecutedCheckOutput.getCheckAndActionName();
        assertSame(checkAndActionName, checkAndActionName1);
        assertEquals(Result.failed, actualExecutedCheckOutput.getStatus());
        assertEquals("42", actualExecutedCheckOutput.getInputValue());
        assertNull(checkAndActionName1.getActionName());
        assertNull(checkAndActionName1.getTheCheck());
        assertSame(checkAndActionName1, checkAndActionName);
    }

    /**
     * Method under test: {@link ExecutedCheckOutput#equals(Object)}
     */
    @Test
    void testEquals() {
        assertNotEquals(new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName()), null);
        assertNotEquals(new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName()),
                "Different type to ExecutedCheckOutput");
    }

    /**
     * Method under test: {@link ExecutedCheckOutput#equals(Object)}
     */
    @Test
    void testEquals2() {
        ExecutedCheckOutput executedCheckOutput = new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName());
        assertEquals(executedCheckOutput, executedCheckOutput);
        int expectedHashCodeResult = executedCheckOutput.hashCode();
        assertEquals(expectedHashCodeResult, executedCheckOutput.hashCode());
    }

    /**
     * Method under test: {@link ExecutedCheckOutput#equals(Object)}
     */
    @Test
    void testEquals3() {
        ExecutedCheckOutput executedCheckOutput = new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName());
        ExecutedCheckOutput executedCheckOutput1 = new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName());

        assertEquals(executedCheckOutput, executedCheckOutput1);
        int notExpectedHashCodeResult = executedCheckOutput.hashCode();
        assertNotEquals(notExpectedHashCodeResult, executedCheckOutput1.hashCode());
    }

    /**
     * Method under test: {@link ExecutedCheckOutput#equals(Object)}
     */
    @Test
    void testEquals4() {
        ExecutedCheckOutput executedCheckOutput = new ExecutedCheckOutput(null, "42", new CheckAndActionName());
        assertNotEquals(executedCheckOutput, new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName()));
    }

    /**
     * Method under test: {@link ExecutedCheckOutput#equals(Object)}
     */
    @Test
    void testEquals5() {
        ExecutedCheckOutput executedCheckOutput = new ExecutedCheckOutput(Result.failed, "Input Value",
                new CheckAndActionName());
        assertNotEquals(executedCheckOutput, new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName()));
    }

    /**
     * Method under test: {@link ExecutedCheckOutput#equals(Object)}
     */
    @Test
    void testEquals6() {
        ExecutedCheckOutput executedCheckOutput = new ExecutedCheckOutput(Result.failed, "42", null);
        assertNotEquals(executedCheckOutput, new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName()));
    }

    /**
     * Method under test: {@link ExecutedCheckOutput#equals(Object)}
     */
    @Test
    void testEquals7() {
        ExecutedCheckOutput executedCheckOutput = new ExecutedCheckOutput(Result.failed, "42",
                mock(CheckAndActionName.class));
        assertNotEquals(executedCheckOutput, new ExecutedCheckOutput(Result.failed, "42", new CheckAndActionName()));
    }
}

