package com.example.demo.modules;

/**
 * This class encapsulates the data types that needs to be sent to the FE
 * with regard of the execution of the checks. It saves the inputValue - value that is checked,
 * and the object checkAndActionName which is the check and the action of the check
 * that are used for checking.
 */
public class CheckInputValue {
    private String inputValue; //the value that a specific attribute has in Windchill
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
