package com.conference.my;

import com.conference.my.entity.Report;
import com.conference.my.entity.Speaker;
import com.conference.my.entity.User;

public class Demo {
  public static void main(String[] args) {
    Report report = Report.createReport("Blaaaa");
    System.out.println(report);
    Speaker speaker =  new Speaker("Jhon");
    final User user = new User("User");
    System.out.println(user);
    report.setSpeaker(speaker);

    System.out.println(report);

  }

}
