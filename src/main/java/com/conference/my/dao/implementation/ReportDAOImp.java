package com.conference.my.dao.implementation;

import com.conference.my.dao.EntityTransformer;
import com.conference.my.dao.GenericDAO;
import com.conference.my.dao.ReportDAO;
import com.conference.my.entity.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ReportDAOImp extends GenericDAO<Report> implements ReportDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(ReportDAOImp.class);


  public ReportDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(reportTransformer);
  }

  @Override
  public boolean insertNewReport(Report report) {
    final String INSERT_REPORT =
        "INSERT INTO report (topic) VALUES (?)";
    boolean res = false;
    if (findReportByTopic(report.getTopic()) != null) return false;
    try {
      res = insertNew(report, INSERT_REPORT, connection) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Report with topic: {} wasn't inserted", report.getTopic(), ex);
    }
    return res;
  }

  @Override
  public List<Report> findAllReports() {
    final String FIND_ALL_REPORTS =
        "SELECT * FROM report";
    try {
      return findAll(FIND_ALL_REPORTS, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Reports failed", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public Report findReportById(int reportId) {
    final String FIND_REPORT_BY_ID =
        "SELECT * FROM report WHERE id = ?";
    Report res = null;
    try {
      res = findByField(reportId, FIND_REPORT_BY_ID, connection);
    } catch (SQLException ex) {
      LOGGER.error("Report with id: {} wasn't found", reportId, ex);
    }
    return res;
  }

  @Override
  public Report findReportByTopic(String topic) {
    final String FIND_USER_BY_EMAIL =
        "SELECT * FROM report WHERE topic = ?";
    Report res = null;
    try {
      res = findByField(topic, FIND_USER_BY_EMAIL, connection);
    } catch (SQLException ex) {
      LOGGER.error("Report with topic: {} wasn't found", topic, ex);
    }
    return res;
  }

  @Override
  public Report findReportBySpeaker(String reportSpeaker) {
    return null;
  }

  @Override
  public boolean updateReportById(int reportId) {
    return false;
  }

  @Override
  public boolean deleteReportById(int reportId) {
    return false;
  }

  EntityTransformer<Report> reportTransformer = new EntityTransformer<>() {
    @Override
    public Report extractEntity(ResultSet rs) throws SQLException {
      Report report = new Report();
      report.setId(rs.getInt("id"));
      report.setTopic(rs.getString("topic"));
      return report;
    }

    @Override
    public int insertEntity(PreparedStatement prst, Report report) throws SQLException {
      int res;
      prst.setString(1, report.getTopic());
      res = prst.executeUpdate();
      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          report.setId(id);
        }
      }
      return res;
    }
  };



}
