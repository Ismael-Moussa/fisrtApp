package com.example.clientapp.model;

public class User {
  private Long id;
  private String fullName;
  private String email;
  private Long roleId;
  private String roleName;

  public User() {
  }

  public User(Long id, String fullName, String email, Long roleId, String roleName) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.roleId = roleId;
    this.roleName = roleName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
