package com.example.demo.modules;

import java.util.Objects;

/** Class for the action name. */
public class ActionNameString {

  private String actionName;

  public String getActionName() {
    return actionName;
  }

  public ActionNameString() {}

  public ActionNameString(String actionName) {
    this.actionName = actionName;
  }

  @Override
  public String toString() {
    return "ActionNameString{" + "actionName='" + actionName + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ActionNameString that = (ActionNameString) o;
    return Objects.equals(actionName, that.actionName);
  }
}
