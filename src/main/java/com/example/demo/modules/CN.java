package com.example.demo.modules;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CN {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String description;
  private String[] changeType;
  private boolean customerApproval;
  private boolean supplierApproval;
  private String philipsID;
  private Boolean changeAdminAuditReq;
  private String teamName;
  private String location;
  private String[] createdBy;
  private LocalDateTime createdOn;
  private String[] modifiedBy;
  private LocalDateTime modifiedOn;
  private Boolean regulatoryRestrictionReq;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getChangeAdminAuditReq() {
    return changeAdminAuditReq;
  }

  public Boolean getRegulatoryRestrictionReq() {
    return regulatoryRestrictionReq;
  }

  public void setRegulatoryRestrictionReq(Boolean regulatoryRestrictionReq) {
    this.regulatoryRestrictionReq = regulatoryRestrictionReq;
  }

  public void setChangeAdminAuditReq(Boolean changeAdminAuditReq) {
    this.changeAdminAuditReq = changeAdminAuditReq;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String[] getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String[] createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public String[] getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String[] modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public LocalDateTime getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(LocalDateTime modifiedOn) {
    this.modifiedOn = modifiedOn;
  }

  public CN() {}

  public String getPhilipsID() {
    return philipsID;
  }

  public void setPhilipsID(String philipsID) {
    this.philipsID = philipsID;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String[] getChangeType() {
    return changeType;
  }

  public void setChangeType(String[] changeType) {
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
