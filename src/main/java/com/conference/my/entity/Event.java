package com.conference.my.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

public class Event {
  private int id;
  private final String title;
  private LocalDate date;
  private Location location;
  private Set<Report> reports;
  private Set<User> visitors;

  private Event(String title) {
    this.title = title;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public static Event createEvent(String title, LocalDate date, Location location) {
    Event event = new Event(title);
    event.setDate(date);
    event.setLocation(location);
    event.id = 7;
    return event;
  }

  @Override
  public String toString() {
    return "Event{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", date=" + date +
        ", " + location +
        ", reports=" + reports +
        ", visitors=" + visitors +
        '}';
  }

  public static void main(String[] args) {
    final LocalDate date = LocalDate.now();
    Location location = Location.newBuilder().id(1).zipCode(222).country("aaa").build();

    final Event event = Event.createEvent("aaa", date, location);
    final LocalDate localDate = LocalDate.parse("1977-12-04", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    System.out.println("LocalDate.parse() -> " + localDate);
    LocalDateTime dateTime = LocalDateTime.of(2021, 1, 25, 11, 0);
//    LocalDateTime dateTime2 = LocalDateTime.parse("2021, 1, 25, 11, 0", DateTimeFormatter.ofPattern("yyyy, MM, dd, hh, mm"));
    System.out.println("LocalDate.now() -> " + date);
    System.out.println("LocalDateTime.of() -> " + dateTime);
//    System.out.println(dateTime2);

    LocalDateTime date3 = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String text = date3.format(formatter);
    text = "2021-01-25 00:00";
    LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
    System.out.println("LocalDateTime.now() -> " + date3);
    System.out.println("LocalDateTime.parse(text, formatter) -> " + parsedDate);

    java.sql.Date sqlDate = java.sql.Date.valueOf(date);
    System.out.println(sqlDate);
    java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(new Date().getTime());
    System.out.println(sqlTimestamp);
    java.sql.Timestamp sqlTimestampEpoch = java.sql.Timestamp.from(parsedDate.toInstant(ZoneOffset.UTC));
    System.out.println(sqlTimestampEpoch);

    System.out.println(event);
    event.setDate(localDate);
    System.out.println(event);
  }

}
