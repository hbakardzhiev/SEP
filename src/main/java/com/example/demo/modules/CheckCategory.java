package com.example.demo.modules;

public class CheckCategory {
    private String category; //we can change it to enum -> failed, passed, attention..
    private Check check;

    public CheckCategory(String category, Check check) {
        this.category = category;
        this.check = check;
    }

    @Override
    public String toString() {
        return "CheckCategory{" +
                "category='" + category + '\'' +
                ", check=" + check +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
