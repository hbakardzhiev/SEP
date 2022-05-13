package com.example.demo.modules;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SheetSource {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String htmlID;

  @Column(nullable = false)
  private String htmlTag = "attrid";

  @Column(nullable = false)
  private String type;

  @Column(nullable = true)
  private String name;

  @Column(nullable = false)
  @Enumerated
  @ElementCollection(targetClass = SheetType.class)
  private List<Enum<SheetType>> sheetSourceType;

  public SheetSource(String htmlID, String type, List<Enum<SheetType>> sheetSourceType) {
    this.htmlID = htmlID;
    this.type = type;
    this.sheetSourceType = sheetSourceType;
  }

  public SheetSource(
      String htmlTag, String htmlID, String type, List<Enum<SheetType>> sheetSourceType) {
    this(htmlID, type, sheetSourceType);
    this.htmlTag = htmlTag;
  }

  //
  //  private String description;
  //  private String[] changeType;
  //  private boolean customerApproval;
  //  private boolean supplierApproval;
  //  private String philipsID;
  //  private Boolean changeAdminAuditReq;
  //  private String teamName;
  //  private String location;
  //  private String[] createdBy;
  //  private LocalDateTime createdOn;
  //  private String[] modifiedBy;
  //  private LocalDateTime modifiedOn;
  //  private Boolean regulatoryRestrictionReq;
  //
  //  public Long getId() {
  //    return id;
  //  }
  //
  //  public void setId(Long id) {
  //    this.id = id;
  //  }
  //
  //  public Boolean getChangeAdminAuditReq() {
  //    return changeAdminAuditReq;
  //  }
  //
  //  public Boolean getRegulatoryRestrictionReq() {
  //    return regulatoryRestrictionReq;
  //  }
  //
  //  public void setRegulatoryRestrictionReq(Boolean regulatoryRestrictionReq) {
  //    this.regulatoryRestrictionReq = regulatoryRestrictionReq;
  //  }
  //
  //  public void setChangeAdminAuditReq(Boolean changeAdminAuditReq) {
  //    this.changeAdminAuditReq = changeAdminAuditReq;
  //  }
  //
  //  public String getTeamName() {
  //    return teamName;
  //  }
  //
  //  public void setTeamName(String teamName) {
  //    this.teamName = teamName;
  //  }
  //
  //  public String getLocation() {
  //    return location;
  //  }
  //
  //  public void setLocation(String location) {
  //    this.location = location;
  //  }
  //
  //  public String[] getCreatedBy() {
  //    return createdBy;
  //  }
  //
  //  public void setCreatedBy(String[] createdBy) {
  //    this.createdBy = createdBy;
  //  }
  //
  //  public LocalDateTime getCreatedOn() {
  //    return createdOn;
  //  }
  //
  //  public void setCreatedOn(LocalDateTime createdOn) {
  //    this.createdOn = createdOn;
  //  }
  //
  //  public String[] getModifiedBy() {
  //    return modifiedBy;
  //  }
  //
  //  public void setModifiedBy(String[] modifiedBy) {
  //    this.modifiedBy = modifiedBy;
  //  }
  //
  //  public LocalDateTime getModifiedOn() {
  //    return modifiedOn;
  //  }
  //
  //  public void setModifiedOn(LocalDateTime modifiedOn) {
  //    this.modifiedOn = modifiedOn;
  //  }
  //
  //  public CN() {}
  //
  //  public String getPhilipsID() {
  //    return philipsID;
  //  }
  //
  //  public void setPhilipsID(String philipsID) {
  //    this.philipsID = philipsID;
  //  }
  //
  //  public String getDescription() {
  //    return description;
  //  }
  //
  //  public void setDescription(String description) {
  //    this.description = description;
  //  }
  //
  //  public String[] getChangeType() {
  //    return changeType;
  //  }
  //
  //  public void setChangeType(String[] changeType) {
  //    this.changeType = changeType;
  //  }
  //
  //  public boolean isCustomerApproval() {
  //    return customerApproval;
  //  }
  //
  //  public void setCustomerApproval(boolean customerApproval) {
  //    this.customerApproval = customerApproval;
  //  }
  //
  //  public boolean isSupplierApproval() {
  //    return supplierApproval;
  //  }
  //
  //  public void setSupplierApproval(boolean supplierApproval) {
  //    this.supplierApproval = supplierApproval;
  //  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    SheetSource cn = (SheetSource) o;
    return id != null && Objects.equals(id, cn.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
