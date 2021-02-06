package com.conference.my.dao.implementation;

import com.conference.my.dao.EntityTransformer;
import com.conference.my.dao.EventDAO;
import com.conference.my.dao.GenericDAO;
import com.conference.my.entity.Event;
import com.conference.my.entity.Location;
import com.conference.my.entity.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EventDAOImp extends GenericDAO<Event> implements EventDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(EventDAOImp.class);

  public EventDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(eventTransformer);
  }

  @Override
  public boolean createNewEvent(Event event) {
    return false;
  }

  @Override
  public Event findEventById(int eventId) {
    return null;
  }

  @Override
  public Event findEventByTitle(String eventTitle) {
    return null;
  }

  @Override
  public Event findEventByDate(LocalDate date) {
    return null;
  }

  @Override
  public List<Event> findAllEvents() {
    return null;
  }

  @Override
  public List<Event> getPastEvents() {
    return null;
  }

  @Override
  public List<Event> getFutureEvents() {
    return null;
  }

  @Override
  public List<Event> getSortedEventsByDate() {
    return null;
  }

  @Override
  public List<Event> getSortedEventsByTitle() {
    return null;
  }

  @Override
  public List<Event> getSortedEventsByLocation() {
    return null;
  }

  @Override
  public List<Event> getSortedEventsByReportsNumber() {
    return null;
  }

  @Override
  public List<Event> getSortedEventsByVisitorsNumber() {
    return null;
  }

  @Override
  public boolean addReportToEvent(int eventId, Report newReport) {
    return false;
  }

  @Override
  public boolean updateEventDate(int eventId, LocalDate newDate) {
    return false;
  }

  @Override
  public boolean updateEventLocation(int eventId, Location newLocation) {
    return false;
  }

  @Override
  public boolean deleteEventById(int eventId) {
    return false;
  }

  EntityTransformer<Event> eventTransformer = new EntityTransformer<>() {
    @Override
    public Event extractEntity(ResultSet rs) throws SQLException {
      return null;
    }

    @Override
    public int insertEntity(PreparedStatement prst, Event event) throws SQLException {
      return 0;
    }
  };
}
