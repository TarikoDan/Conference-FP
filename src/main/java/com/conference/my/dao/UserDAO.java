package com.conference.my.dao;

import com.conference.my.entity.User;

import java.util.List;

public interface UserDAO {
  boolean createNewUser(User user);
  List<User> findAllUsers();
  User findUserById(int userId);
  User findUserByEmail(String userEmail);
  boolean updateUserById(int userId);
  boolean deleteUserById(int userId);

}
