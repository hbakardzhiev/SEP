package com.example.demo.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

/**
 * Class that defines the rule entity in the database, which contains the name of check (PK), the
 * specific document type, the attribute upon which the check will be made, the inputted value form
 * the user the comment which will be visible when the check fails.
 */
@Entity
@Table(name = "rule")
public class Check {

  @Id
  @Column(name = "name")
  @Audited
  private String name;

  @Column(name = "document_source")
  @Audited
  private String docSource;

  @Column(name = "attribute")
  @Audited
  private String attribute;

  @Column(name = "value")
  @Audited
  private String value;

  @Column(name = "comments")
  @Audited
  private String comments;

  @Column(name = "author", nullable = true)
  @Audited
  @JsonIgnore
  private Long authorId;

  // Get a string value for action, if it does not exist from the defined actions,
  // do not allow the user to make the check.
  // This could be hardcoded on the frontend side.

  // To prevent cascading deletes, so that check deletion has no relation
  // with Action deletion.
  @ManyToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "action")
  @Audited(targetAuditMode = NOT_AUDITED)
  @JsonIgnore
  private Action actionType;

  public Check() {}

  public Check(
      String name,
      String docSource,
      String attribute,
      String value,
      String comments,
      Long authorId) {
    this.name = name;
    this.docSource = docSource;
    this.attribute = attribute;
    this.value = value;
    this.comments = comments;
    this.authorId = authorId;
  }

  public Check(String name, String docSource, String attribute, String value, String comments) {
    this.name = name;
    this.docSource = docSource;
    this.attribute = attribute;
    this.value = value;
    this.comments = comments;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws Exception {
    if (name.length() < 1) {
      throw new IllegalArgumentException("Name should not be empty");
    }
    this.name = name;
  }

  public String getDocSource() {
    return docSource;
  }

  public void setDocSource(String docSource) throws Exception {
    if (docSource.length() < 1) {
      throw new IllegalArgumentException("Docsource should not be empty");
    }
    this.docSource = docSource;
  }

  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) throws Exception {
    if (attribute.length() < 1) {
      throw new IllegalArgumentException("Attribute should not be empty");
    }
    this.attribute = attribute;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthor(Long authorId) {
    this.authorId = authorId;
  }

  public Action getActionType() {
    return actionType;
  }

  public void setActionType(Action action) {
    this.actionType = action;
  }

  @Override
  public String toString() {
    return "Check{"
        + "name='"
        + name
        + '\''
        + ", docSource='"
        + docSource
        + '\''
        + ", attribute='"
        + attribute
        + '\''
        + ", value='"
        + value
        + '\''
        + ", comments='"
        + comments
        + '\''
        + ", action="
        + actionType
        + '}';
  }
}
