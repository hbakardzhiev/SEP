package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ActionValueTypeTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ActionValueType#ActionValueType()}
     *   <li>{@link ActionValueType#setAction(String)}
     *   <li>{@link ActionValueType#setChecks(List)}
     *   <li>{@link ActionValueType#setDescription(String)}
     *   <li>{@link ActionValueType#setValueType(String)}
     * </ul>
     */
    @Test
    void testConstructor() {
        ActionValueType actualActionValueType = new ActionValueType();
        actualActionValueType.setAction("Action");
        ArrayList<Check> checkList = new ArrayList<>();
        actualActionValueType.setChecks(checkList);
        actualActionValueType.setDescription("The characteristics of someone or something");
        actualActionValueType.setValueType("42");
        assertEquals("Action", actualActionValueType.getAction());
        assertSame(checkList, actualActionValueType.getChecks());
        assertEquals("The characteristics of someone or something", actualActionValueType.getDescription());
        assertEquals("42", actualActionValueType.getValueType());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ActionValueType#ActionValueType(String, String, String)}
     *   <li>{@link ActionValueType#setAction(String)}
     *   <li>{@link ActionValueType#setChecks(List)}
     *   <li>{@link ActionValueType#setDescription(String)}
     *   <li>{@link ActionValueType#setValueType(String)}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ActionValueType actualActionValueType = new ActionValueType("Action", "42",
                "The characteristics of someone or something");
        actualActionValueType.setAction("Action");
        ArrayList<Check> checkList = new ArrayList<>();
        actualActionValueType.setChecks(checkList);
        actualActionValueType.setDescription("The characteristics of someone or something");
        actualActionValueType.setValueType("42");
        assertEquals("Action", actualActionValueType.getAction());
        assertSame(checkList, actualActionValueType.getChecks());
        assertEquals("The characteristics of someone or something", actualActionValueType.getDescription());
        assertEquals("42", actualActionValueType.getValueType());
    }

    /**
     * Method under test: {@link ActionValueType#add(Check)}
     */
    @Test
    void testAdd() {
        ActionValueType actionValueType = new ActionValueType();

        ActionValueType actionValueType1 = new ActionValueType();
        actionValueType1.setAction("Action");
        actionValueType1.setChecks(new ArrayList<>());
        actionValueType1.setDescription("The characteristics of someone or something");
        actionValueType1.setValueType("42");

        Check check = new Check();
        check.setActionValueType(actionValueType1);
        check.setAttribute("Attribute");
        check.setComments("Comments");
        check.setDocSource("Doc Source");
        check.setName("Name");
        check.setValue("42");
        actionValueType.add(check);
        ActionValueType actionValueType2 = check.getActionValueType();
        assertSame(actionValueType, actionValueType2);
        assertEquals(1, actionValueType2.getChecks().size());
    }
}

