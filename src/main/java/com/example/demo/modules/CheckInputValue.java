package com.example.demo.modules;

public class CheckInputValue {
    private String inputValue; //the value which is in Windchill
    private CheckAndActionName checkAndActionName;

    public CheckInputValue(String inputValue, CheckAndActionName checkAndActionName) {
        this.inputValue = inputValue;
        this.checkAndActionName = checkAndActionName;
    }

    @Override
    public String toString() {
        return "CheckInputValue{" +
                "inputValue='" + inputValue + '\'' +
                ",checkAndActionName=" + checkAndActionName +
//                ",action=" + check.getActionValueType().getAction() +
                '}';
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public CheckAndActionName getCheckAndAction() {
        return checkAndActionName;
    }

    public void setCheckAndAction(CheckAndActionName checkAndActionName) {
        this.checkAndActionName = checkAndActionName;
    }
}
