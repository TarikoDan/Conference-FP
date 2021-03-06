package com.conference.my.dao;

import com.conference.my.entity.Report;
import com.conference.my.entity.User;

import java.util.List;

public interface ReportDAO {
  boolean createNewReport(Report report);
  List<Report> findAllReports();
  List<Report> findReportsWithoutSpeakers();
  Report findReportById(int reportId);
  Report findReportByTopic(String reportTopic);
  Report findReportBySpeaker(User reportSpeaker);
  void proposeSpeakerForReport(int speakerId, int reportId);
  void assignSpeakerToReport(int speakerId, int reportId);
  boolean updateReportById(int reportId, Report newReport);
  boolean deleteReportById(int reportId);

}
