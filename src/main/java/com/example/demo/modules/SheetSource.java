package com.example.demo.modules;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table
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
  private String dataType;

  @Column(nullable = true)
  private String name;


  @Column(nullable = true)
  @Enumerated
  private SheetType sheetSourceType;

  public SheetSource(String htmlID, String dataType,
      SheetType sheetSourceType
  ) {
    this.htmlID = htmlID;
    this.dataType = dataType;
    this.sheetSourceType = sheetSourceType;
  }

  public SheetSource(String htmlTag, String htmlID, String dataType,
      SheetType sheetSourceType) {
    this(htmlID, dataType, sheetSourceType);
    this.htmlTag = htmlTag;
  }

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
