package com.conference.my;

import com.conference.my.dao.DAOFactory;
import com.conference.my.dao.ReportDAO;
import com.conference.my.dao.UserDAO;
import com.conference.my.entity.Report;
import com.conference.my.entity.Speaker;
import com.conference.my.entity.User;

import java.util.List;

public class Demo {
  public static void main(String[] args) {
    String query = "select * from user join role r on r.id = user.role_id";

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

    final User user = new User();
//    user.setName("New1");
//    user.setEmail("New1@mail.com");
//    user.setPassword("New1pas");
//    final User user2 = new User();
//    user2.setName("New2");
//    user2.setEmail("New2@mail.com");
//    user2.setPassword("New2pas");

    final UserDAO userDAO = DAOFactory.getUserDAO();

    final boolean isCreated = userDAO.createNewUser(user);
    System.out.println(user);
    System.out.println(isCreated);
//    System.out.println(userDAO.createNewUser(user));
//    userDAO.createNewUser(user2);
    List<User> allUsers = userDAO.findAllUsers();
    allUsers.forEach(System.out::println);


    final ReportDAO reportDAO = DAOFactory.getReportDAO();
    List<Report> allReports = reportDAO.findAllReports();
    allReports.forEach(System.out::println);
    final Report report = new Report();
    report.setTopic("newRep1");
    report.setSpeaker(new Speaker("newSpeaker"));
    System.out.println(reportDAO.insertNewReport(report));
    allReports = reportDAO.findAllReports();
    allReports.forEach(System.out::println);

  }


}


