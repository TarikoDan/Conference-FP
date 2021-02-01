package com.conference.my.dao;

import com.conference.my.entity.User;

import java.util.List;

public interface UserDAO {
  public boolean createNewUser();
  public List<User> findAllUsers();
  public User findUserById(int userId);
  public User findUserByEmail(String userEmail);
  public boolean updateUserById(int userId);
  public boolean deleteUserById(int userId);

}
