package com.example.demo.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class that serves as an entity in the database. It contains information about the admin.
 * Attributes are id - the primary key in the table, username, password and the email address of the
 * admin.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  /** Username of the administrator. */
  @Column(name = "username")
  private String username;

  /** Email address of the administrator. */
  @Column(name = "email")
  private String email;

  /** Password of the administrator. */
  @Column(name = "password")
  private String password;

  /** Predefined variable that gives each administrator a role, so they can be authrized. */
  @Column(name = "role")
  private String adminRole = "ADMIN";

  /**
   * Constructor that creates an Admin object with username, email, password.
   *
   * @param username the username of the admin
   * @param email the email of the admin
   * @param password the password of the admin
   */
  public Admin(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
