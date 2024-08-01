package ru.otus.model;

public enum Authority {
  ADMIN("admin"),
  USER("user");

  private final String role;

  Authority(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}