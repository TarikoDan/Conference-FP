package com.conference.my;

import com.conference.my.dao.*;
import com.conference.my.entity.*;

import java.time.LocalDate;
import java.util.List;

public class Demo {
  public static void main(String[] args) {
//    String query = "select * from user join role r on r.id = user.role_id";

//    try (Connection connection = DBCPManager.getInstance().getConnection();
//         Statement statement = connection.createStatement();
//         ResultSet resultSet = statement.executeQuery(query)) {
//      while (resultSet.next()) {
//        System.out.println("Id:" + resultSet.getInt("id"));
//        System.out.println("Name:" + resultSet.getString("name"));
//        System.out.println("Email:" + resultSet.getString("email"));
//        System.out.println("Pass:" + resultSet.getString("password"));
//        System.out.println("Role_id:" + resultSet.getInt("role_id"));
//        System.out.println("Role:" + resultSet.getString("title"));
//        System.out.println("--------------------------");
//      }
//    } catch (SQLException throwables) {
//      throwables.printStackTrace();
//    }

//    User user = new User();
//    user.setName("New1");
//    user.setEmail("New1@mail.com");
//    user.setPassword("New1pas");
//    user.setRole(Role.valueOf("MODERATOR"));

    final User userTest = new User();
    userTest.setName("Speaker1");
    userTest.setEmail("Speaker1@mail.com");
    userTest.setPassword("Speaker1");
    userTest.setRole(Role.SPEAKER);
//
    final UserDAO userDAO = DAOFactory.getUserDAO();

//    userDAO.createNewUser(userTest);
    List<User> allUsers = userDAO.findAllUsers();
    allUsers.forEach(System.out::println);

    System.out.println("--------------");
    final ReportDAO reportDAO = DAOFactory.getReportDAO();
    List<Report> allReports = reportDAO.findAllReports();
    allReports.forEach(System.out::println);
//    final Report report = new Report();
//    report.setTopic("newRep1");
//    report.setSpeaker(new Speaker("newSpeaker"));
//    System.out.println(reportDAO.createNewReport(report));
//    allReports = reportDAO.findAllReports();
//    allReports.forEach(System.out::println);
//    final User speaker2 = userDAO.findUserById(2);
    final User speaker2 = new User();
    speaker2.setId(22);
    final Report reportBySpeaker = reportDAO.findReportBySpeaker(speaker2);
    System.out.println(reportBySpeaker);
    final Report reportTest = new Report();
    reportTest.setTopic("newReport5");
//    final User speaker4 = new User();
//    speaker4.setId(4);
//    reportTest.setSpeaker(speaker4);
//    reportDAO.createNewReport(reportTest);
//    reportDAO.assignSpeakerToReport(4, 1);
    reportDAO.findAllReports().forEach(System.out::println);
    System.out.println("--------------");

    final Location.Builder builder = Location.newBuilder();
    final Location location = builder
        .id(1)
        .zipCode(82400)
        .country("Ukraine")
        .region("Lviv")
        .city("Stryi")
        .street("Shevchenko")
        .building("117")
        .suite("302")
        .build();

    final LocationDAO locationDAO = DAOFactory.getLocationDAO();
//    locationDAO.createNew(location);
    locationDAO.findAll().forEach(System.out::println);
    System.out.println("--------------");

    final EventDAO eventDAO = DAOFactory.getEventDAO();
    List<Event> allEvents = eventDAO.findAllEvents();
    allEvents.forEach(System.out::println);
//    System.out.println(eventDAO.createNewEvent(Event.createEvent("testEvent1", LocalDate.now(), location)));
//    allEvents = eventDAO.findAllEvents();
//    allEvents.forEach(System.out::println);
    System.out.println("--------------");
    System.out.println(eventDAO.findAllEventsByDate(LocalDate.of(2021, 1, 31)));
    final LocalDate now = LocalDate.now();
    System.out.println(eventDAO.findAllEventsByDate(now));
    System.out.println("--------------");
//    eventDAO.addReportToEvent(1, 4);
//    eventDAO.addReportToEvent(4, 4);
    final User user4 = userDAO.findUserById(4);
    System.out.println(eventDAO.findAllEventsBySpeaker(user4));


  }


}


