package com.conference.my.dao;

import com.conference.my.entity.User;

import java.util.List;

public class UserDAOImp implements UserDAO{
  @Override
  public boolean createNewUser() {
    return false;
  }

  @Override
  public List<User> findAllUsers() {
    return null;
  }

  @Override
  public User findUserById(int userId) {
    return null;
  }

  @Override
  public User findUserByEmail(String userEmail) {
    return null;
  }

  @Override
  public boolean updateUserById(int userId) {
    return false;
  }

  @Override
  public boolean deleteUserById(int userId) {
    return false;
  }
}
