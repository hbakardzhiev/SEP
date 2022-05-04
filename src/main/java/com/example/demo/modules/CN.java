package com.example.demo.modules;


import java.util.List;

public class CN {

  private String description;
  private List<String> changeType;
  private boolean customerApproval;
  private boolean supplierApproval;

  public CN(
      String description,
      List<String> changeType,
      boolean customerApproval,
      boolean supplierApproval) {
    this.description = description;
    this.changeType = changeType;
    this.customerApproval = customerApproval;
    this.supplierApproval = supplierApproval;
  }

  public CN() {}

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getChangeType() {
    return changeType;
  }

  public void setChangeType(List<String> changeType) {
    this.changeType = changeType;
  }

  public boolean isCustomerApproval() {
    return customerApproval;
  }

  public void setCustomerApproval(boolean customerApproval) {
    this.customerApproval = customerApproval;
  }

  public boolean isSupplierApproval() {
    return supplierApproval;
  }

  public void setSupplierApproval(boolean supplierApproval) {
    this.supplierApproval = supplierApproval;
  }
}
