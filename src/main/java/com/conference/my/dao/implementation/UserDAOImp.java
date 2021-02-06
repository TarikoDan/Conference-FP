package com.conference.my.dao.implementation;

import com.conference.my.dao.EntityTransformer;
import com.conference.my.dao.GenericDAO;
import com.conference.my.dao.UserDAO;
import com.conference.my.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDAOImp extends GenericDAO<User> implements UserDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(UserDAOImp.class);


  public UserDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(userTransformer);
  }

  @Override
  public boolean createNewUser(User user) {
    final String INSERT_USER =
        "INSERT INTO user (name, email, password, role_id) VALUES (?, ?, ?, 3)";
    if (findUserByEmail(user.getEmail()) != null) return false;
    boolean res;
    try {
      res = insertNew(user, INSERT_USER, connection) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Error with inserting an {}", user, ex);
      return false;
    }
    return res;
  }

  @Override
  public List<User> findAllUsers() {
    final String FIND_ALL_USERS =
        "SELECT * FROM user WHERE role_id = 3";
    try {
      return findAll(FIND_ALL_USERS, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Users Error", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public User findUserById(int userId) {
    final String FIND_USER_BY_ID =
        "SELECT * FROM user WHERE role_id = 3 AND id = ?";
    try {
      return findByField(userId, FIND_USER_BY_ID, connection);
    } catch (SQLException ex) {
      LOGGER.error("User with id: {} wasn't found", userId, ex);
      throw new NoSuchElementException("User wasn't found");
    }
  }

  @Override
  public User findUserByEmail(String userEmail) {
    if (userEmail == null)
      throw new IllegalArgumentException("Email not specified");
    final String FIND_USER_BY_EMAIL =
        "SELECT * FROM user WHERE role_id = 3 AND email = ?";
    try {
      return findByField(userEmail, FIND_USER_BY_EMAIL, connection);
    } catch (SQLException ex) {
      LOGGER.error("User with email: {} wasn't found", userEmail, ex);
      throw new NoSuchElementException("User wasn't found");
    }
  }

  @Override
  public boolean updateUserById(int userId) {
    return false;
  }

  @Override
  public boolean deleteUserById(int userId) {
    return false;
  }

  EntityTransformer<User> userTransformer = new EntityTransformer<>() {
    @Override
    public User extractEntity(ResultSet rs) throws SQLException {
      final User user = new User();
      user.setId(rs.getInt("id"));
      user.setName(rs.getString("name"));
      user.setEmail(rs.getString("email"));
      user.setPassword(rs.getString("password"));
      return user;
    }

    @Override
    public int insertEntity(PreparedStatement prst, User user) throws SQLException {
      int res;
      int k = 1;
      prst.setString(k++, user.getName());
      prst.setString(k++, user.getEmail());
      prst.setString(k, user.getPassword());
      res = prst.executeUpdate();
      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          user.setId(id);
        }
      }
      return res;
    }
  };

}
