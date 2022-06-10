package com.example.demo.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ActionTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Action#Action()}
   *   <li>{@link Action#setAction(String)}
   *   <li>{@link Action#setChecks(List)}
   *   <li>{@link Action#setDescription(String)}
   *   <li>{@link Action#setValueType(String)}
   * </ul>
   */
  @Test
  void testConstructor() {
    Action actualAction = new Action();
    actualAction.setAction("Action");
    ArrayList<Check> checkList = new ArrayList<>();
    actualAction.setChecks(checkList);
    actualAction.setDescription("The characteristics of someone or something");
    actualAction.setValueType("42");
    assertEquals("Action", actualAction.getAction());
    assertSame(checkList, actualAction.getChecks());
    assertEquals("The characteristics of someone or something", actualAction.getDescription());
    assertEquals("42", actualAction.getValueType());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Action#Action(String, String, String)}
   *   <li>{@link Action#setAction(String)}
   *   <li>{@link Action#setChecks(List)}
   *   <li>{@link Action#setDescription(String)}
   *   <li>{@link Action#setValueType(String)}
   * </ul>
   */
  @Test
  void testConstructor2() {
    Action actualAction = new Action("Action", "42", "The characteristics of someone or something");
    actualAction.setAction("Action");
    ArrayList<Check> checkList = new ArrayList<>();
    actualAction.setChecks(checkList);
    actualAction.setDescription("The characteristics of someone or something");
    actualAction.setValueType("42");
    assertEquals("Action", actualAction.getAction());
    assertSame(checkList, actualAction.getChecks());
    assertEquals("The characteristics of someone or something", actualAction.getDescription());
    assertEquals("42", actualAction.getValueType());
  }

  /** Method under test: {@link Action#add(Check)} */
  @Test
  void testAdd() {
    Action action = new Action();

    Action action1 = new Action();
    action1.setAction("Action");
    action1.setChecks(new ArrayList<>());
    action1.setDescription("The characteristics of someone or something");
    action1.setValueType("42");

    Check check = new Check();
    check.setActiontype(action1);
    check.setAttribute("Attribute");
    check.setComments("Comments");
    check.setDocSource("Doc Source");
    check.setName("Name");
    check.setValue("42");
    action.add(check);
    Action action2 = check.getActiontype();
    assertSame(action, action2);
    assertEquals(1, action2.getChecks().size());
  }
}
