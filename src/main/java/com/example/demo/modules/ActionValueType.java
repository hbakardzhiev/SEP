package com.example.demo.modules;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actions")
public class ActionValueType {

    @Id
    @Column(name = "action")
    private String action;

    @Column(name = "valueType")
    private String valueType;

    @OneToMany(mappedBy = "actionValueType")
    private List<Check2> checks;

    public ActionValueType() {}

    public ActionValueType(String action, String valueType) {
        this.action = action;
        this.valueType = valueType;
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

    public List<Check2> getChecks() {
        return checks;
    }

    public void setChecks(List<Check2> checks) {
        this.checks = checks;
    }

    public void add(Check2 tempCheck) {
        if (checks == null) {
            checks = new ArrayList<>();
        }

        checks.add(tempCheck);

        tempCheck.setActionValueType(this);
    }

    @Override
    public String toString() {
        return "ActionValueType{" +
                ", action='" + action + '\'' +
                ", valueType='" + valueType + '\'' +
                '}';
    }
}
