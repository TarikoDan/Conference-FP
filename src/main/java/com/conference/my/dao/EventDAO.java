package com.conference.my.dao;

import com.conference.my.entity.Event;
import com.conference.my.entity.Location;
import com.conference.my.entity.Report;

import java.time.LocalDate;
import java.util.List;

public interface EventDAO {
  boolean createNewEvent(Event event);
  Event findEventById(int eventId);
  Event findEventByTitle(String eventTitle);
  Event findEventByDate(LocalDate date);
  List<Event> findAllEvents();
  List<Event> getPastEvents();
  List<Event> getFutureEvents();
  List<Event> getSortedEventsByDate();
  List<Event> getSortedEventsByTitle();
  List<Event> getSortedEventsByLocation();
  List<Event> getSortedEventsByReportsNumber();
  List<Event> getSortedEventsByVisitorsNumber();
  boolean addReportToEvent(int eventId, Report newReport);
  boolean updateEventDate(int eventId, LocalDate newDate);
  boolean updateEventLocation(int eventId, Location newLocation);
  boolean deleteEventById(int eventId);

}
