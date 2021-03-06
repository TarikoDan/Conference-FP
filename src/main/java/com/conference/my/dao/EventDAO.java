package com.conference.my.dao;

import com.conference.my.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface EventDAO {
  boolean createNewEvent(Event event);
  Event findEventById(int eventId);
  Event findEventByTitle(String eventTitle);
  List<Event> findAllEvents();
  List<Event> findAllEventsByDate(LocalDate date);
  List<Event> findAllEventsBySpeaker(User speaker);
  List<Event> getPastEvents();
  List<Event> getFutureEvents();
  List<Event> getSortedEventsByDate();
  List<Event> getSortedEventsByTitle();
  List<Event> getSortedEventsByLocation();
  List<Event> getSortedEventsByReportsNumber();
  List<Event> getSortedEventsByVisitorsNumber();
  void addReportToEvent(int newReportId, int eventId);
  boolean updateEventDate(int eventId, LocalDate newDate);
  boolean updateEventLocation(int eventId, Location newLocation);
  boolean deleteEventById(int eventId);

}
