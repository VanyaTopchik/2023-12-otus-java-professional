package ru.otus.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@ToString
@Table("users")
public class User {

  @Id
  private Long id;

  @Column("login")
  private String login;

  @Column("password")
  private String password;

  @Column("role")
  private String role;

  public User(String login, String password, String role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }
}