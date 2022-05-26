package com.example.demo.modules;

public class CheckInputValue {
    private String inputValue; //we can change it to enum -> failed, passed, attention..
    private Check check;

    public CheckInputValue(String inputValue, Check check) {
        this.inputValue = inputValue;
        this.check = check;
    }

    @Override
    public String toString() {
        return "CheckInputValue{" +
                "inputValue='" + inputValue + '\'' +
                ",check=" + check +
                ",action=" + check.getActionValueType().getAction() +
                '}';
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
