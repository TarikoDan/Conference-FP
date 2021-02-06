package com.conference.my.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<E> {
  EntityTransformer<E> entityTransformer;

  public void setTransformer(EntityTransformer<E> transformer) {
    this.entityTransformer = transformer;
  }

  public <V> E findByField(V field, String query, Connection connection) throws SQLException {
    E res = null;
    try (PreparedStatement prst = connection.prepareStatement(query)) {

      if (field instanceof Integer)
        prst.setInt(1, (Integer) field);
      if (field instanceof String)
        prst.setString(1, (String) field);

      try (ResultSet rs = prst.executeQuery()) {
        if (rs.next()) {
          res = entityTransformer.extractEntity(rs);
        }
      }
      return res;
    }
  }

  public int insertNew(E entity, String query, Connection connection) throws SQLException {
    int rows;
    try (PreparedStatement prst = connection.prepareStatement(query,
        Statement.RETURN_GENERATED_KEYS)) {
      rows = entityTransformer.insertEntity(prst, entity);
    }
    return rows;
  }

  public List<E> findAll(String query, Connection connection) throws SQLException {
    List<E> res = new ArrayList<>();
    try (Statement st = connection.createStatement();
         ResultSet rs = st.executeQuery(query)) {
      while (rs.next()) {
        final E entity = entityTransformer.extractEntity(rs);
        res.add(entity);
      }
    }
    return res;
  }

}
