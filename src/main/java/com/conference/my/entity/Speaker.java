package com.conference.my.entity;

public class Speaker extends User{
  private final Role role ;

  public Speaker(String name) {
    super(name);
    this.role = Role.SPEAKER;
  }

  @Override
  public String toString() {
    super.role = this.role;
    return super.toString();
  }
}
