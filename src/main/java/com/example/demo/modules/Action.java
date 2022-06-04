package com.example.demo.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines the action table which consists of the actionTypes (PK), the type of the value
 * that the user needs to input and description of the action.
 */
@Entity
@Table(name = "actions")
public class Action {

  @Id
  @Column(name = "action")
  private String action;

  @Column(name = "valueType")
  private String valueType;

  @Column(name = "description")
  private String description;

  // the checks associated with this action name
  @OneToMany(mappedBy = "action")
  @JsonIgnore
  private List<Check> checks = new ArrayList<>();

  public Action() {}

  public Action(String action, String valueType, String description) {
    this.action = action;
    this.valueType = valueType;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getValueType() {
    return valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }

  public List<Check> getChecks() {
    return checks;
  }

  public void setChecks(List<Check> checks) {
    this.checks = checks;
  }

  /**
   * Adds a check to the list of checks of this.action
   *
   * @param tempCheck the check that is to be added to the list of checks
   */
  public void add(Check tempCheck) {
    //        if (checks == null) {
    //            checks = new ArrayList<>();
    //        }

    checks.add(tempCheck);

    tempCheck.setActionValueType(this);
  }

  @Override
  public String toString() {
    return "Action{"
        + ", action='"
        + action
        + '\''
        + ", valueType='"
        + valueType
        + '\''
        + '}';
  }
}
