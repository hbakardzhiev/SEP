package com.example.demo.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines the action table which consists of the actionTypes (PK), the type of the value
 * that the user needs to input and description of the action.
 */
@Data
@Entity
@NoArgsConstructor
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
  @OneToMany(mappedBy = "actiontype")
  @JsonIgnore
  private List<Check> checks = new ArrayList<>();

  public Action(String action, String valueType, String description) {
    this.action = action;
    this.valueType = valueType;
    this.description = description;
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

    tempCheck.setActiontype(this);
  }

  @Override
  public String toString() {
    return "Action{" + ", action='" + action + '\'' + ", valueType='" + valueType + '\'' + '}';
  }
}
