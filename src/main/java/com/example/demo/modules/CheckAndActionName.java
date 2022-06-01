package com.example.demo.modules;

import java.util.Objects;

/**
 * Class that defines what will be sent to the front end and received, contains the check and the
 * action name.
 */
public class CheckAndActionName {

  public Check theCheck;
  public ActionNameString actionName;

  public CheckAndActionName() {}

  public CheckAndActionName(Check theCheck, ActionNameString actionName) {
    this.theCheck = theCheck;
    this.actionName = actionName;
  }

  public Check getTheCheck() {
    return theCheck;
  }

  public void setTheCheck(Check theCheck) {
    this.theCheck = theCheck;
  }

  public ActionNameString getActionName() {
    return actionName;
  }

  public void setActionName(ActionNameString actionName) {
    this.actionName = actionName;
  }

  @Override
  public String toString() {
    return "CheckAndActionName{" + "theCheck=" + theCheck + ", actionName=" + actionName + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CheckAndActionName that = (CheckAndActionName) o;
    return Objects.equals(theCheck, that.theCheck) && Objects.equals(actionName, that.actionName);
  }
}
