package com.conference.my.dao;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBmanager {
  private static final Logger LOGGER_DB = LogManager.getLogger(DBmanager.class);
  private static DBmanager dbManager;

  private DBmanager() {
  }

  public synchronized static DBmanager getInstanse() {
    if (dbManager == null)
      dbManager = new DBmanager();
    return dbManager;
  }

  public static Connection getConnection() {
    String connectionUrl = getConnectionURL();
    Connection connection;
    MysqlDataSource poolDataSource = new MysqlConnectionPoolDataSource();
    poolDataSource.setURL(connectionUrl);
    try {
      connection = poolDataSource.getConnection();
      LOGGER_DB.info("Database connected!");
    } catch (SQLException e) {
      LOGGER_DB.error("Connection failed");
      throw new RuntimeException(e.getMessage());
    }
    return connection;
  }

  private static String getConnectionURL() {
    Properties appProps = new Properties();
    try {
      appProps.load(new FileReader("app.properties"));
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    return appProps.getProperty("connection.url");
  }



}
