package com.conference.my.dao;

import com.conference.my.dao.util.Converter;
import com.conference.my.dao.util.EntityTransformer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAO<E> {
  EntityTransformer<E> entityTransformer;

  public void setTransformer(EntityTransformer<E> transformer) {
    this.entityTransformer = transformer;
  }

  protected int insertNew(E entity, String query, Connection connection) throws SQLException {
    int rows;
    try (PreparedStatement prst = connection.prepareStatement(query,
        Statement.RETURN_GENERATED_KEYS)) {
      rows = entityTransformer.insertEntity(prst, entity);
    }
    return rows;
  }

  protected <V> E findByField(V field, String query, Connection connection) throws SQLException {
    E res = null;
    try (PreparedStatement prst = connection.prepareStatement(query)) {
      setInstance(field, prst);
      try (ResultSet rs = prst.executeQuery()) {
        if (rs.next()) {
          res = entityTransformer.extractEntity(rs);
        }
      }
      return res;
    }
  }

  protected List<E> findAll(String query, Connection connection) throws SQLException {
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

  protected <V> List<E> findAllWithCondition(V field, String query, Connection connection) throws SQLException {
    List<E> res = new ArrayList<>();
    try (PreparedStatement prst = connection.prepareStatement(query)) {
      setInstance(field, prst);
      try (ResultSet rs = prst.executeQuery()) {
        while (rs.next()) {
          final E entity = entityTransformer.extractEntity(rs);
          res.add(entity);
        }
      }
      return res;
    }
  }

  protected void tieRecordsInJoinTable(int record1_id, int record2_id, String query, Connection connection) throws SQLException {
    try (PreparedStatement prst = connection.prepareStatement(query)) {
      prst.setInt(1, record1_id);
      prst.setInt(2, record2_id);
      prst.executeUpdate();
    }
  }

  private <V> void setInstance(V field, PreparedStatement prst) throws SQLException {
    if (field instanceof Integer)
      prst.setInt(1, (Integer) field);
    if (field instanceof String)
      prst.setString(1, (String) field);
    if (field instanceof LocalDate) {
      prst.setDate(1, Date.valueOf((LocalDate) field));
    }

  }

  public<T,V> T convertNullable(V value, Converter<T, V> converter) {
    return Optional.ofNullable(value)
        .map(converter::convert)
        .orElse(null);
  }

}
