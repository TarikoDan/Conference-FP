package com.conference.my.dao;

import com.conference.my.entity.User;

import java.util.List;

public interface UserDAO {
  boolean createNewUser(User user);
  List<User> findAllUsers();
  User findUserById(int userId);
  User findUserByEmail(String userEmail);
  boolean updateUserById(int userId, User newUser);
  boolean deleteUserById(int userId);

  List<User> findWillingSpeakersForReport(int reportId);

  void registerVisitorForEvent(int userId, int eventId);
  void visitEventByUser(int eventId, int userId);

}
