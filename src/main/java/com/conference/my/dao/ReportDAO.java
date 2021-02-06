package com.conference.my.dao;

import com.conference.my.entity.Report;

import java.util.List;

public interface ReportDAO {
  boolean insertNewReport(Report report);
  List<Report> findAllReports();
  Report findReportById(int reportId);
  Report findReportByTopic(String reportTopic);
  Report findReportBySpeaker(String reportSpeaker);
  boolean updateReportById(int reportId);
  boolean deleteReportById(int reportId);

}
