package com.conference.my.dao;

import java.sql.Connection;

public class DAOFactory {
  public static final String DRIVER = "";
  public static final String DB_URL = "";

  public static Connection createConnection() {
    return null;
  }

  public UserDAO getUserDAO() {
    return new UserDAOImp();
  }
}
