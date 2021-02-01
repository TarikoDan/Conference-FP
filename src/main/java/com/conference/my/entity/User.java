package com.conference.my.entity;

public class User {
  private int id;
  private final String name;
  private String email;
  private String password;
  protected Role role = Role.VISITOR;

  public User(String name) {
    this.name = name;
  }

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", role=" + role +
        '}';
  }
}
