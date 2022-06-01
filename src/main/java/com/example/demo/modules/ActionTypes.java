package com.example.demo.modules;

/** Specifies all action types/names, but currently not used. */
public enum ActionTypes {
  NotEmpty("NotEmpty"),
  Empty("Empty"),
  Contains("Contains"),
  NotContains("NotContains"),
  StrictlyGreater("StrictlyGreater"),
  StrictlySmaller("StrictlySmaller"),
  GreaterEqual("GreaterEqual"),
  SmallerEqual("SmallerEqual"),
  LengthStrictlyGreater("LengthStrictlyGreater"),
  LengthStrictlySmaller("LengthStrictlySmaller"),
  LengthGreaterEqual("LengthGreaterEqual"),
  LengthSmallerEqual("LengthSmallerEqual"),
  DifferentAttrValue("DifferentAttrValue"),
  SameAttrValue("SameAttrValue"),
  IsEqual("IsEqual"),
  IsNotEqual("IsNotEqual"),
  HumanCheck("HumanCheck");

  private String action;

  ActionTypes(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }
}
