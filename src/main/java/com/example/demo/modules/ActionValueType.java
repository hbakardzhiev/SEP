package com.example.demo.modules;

import javax.persistence.*;

@Entity
@Table(name = "actions")
public class ActionValueType {

    @Id
    @Column(name = "action")
    private ActionTypes action;

    @Column(name = "valueType")
    private String valueType;

    public ActionValueType(ActionTypes action, String valueType) {
        this.action = action;
        this.valueType = valueType;
    }

    public ActionTypes getAction() {
        return action;
    }

    public void setAction(ActionTypes action) {
        this.action = action;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
