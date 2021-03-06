package com.conference.my.dao.implementation;

import com.conference.my.dao.DAOFactory;
import com.conference.my.dao.util.EntityTransformer;
import com.conference.my.dao.EventDAO;
import com.conference.my.dao.GenericDAO;
import com.conference.my.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class EventDAOImp extends GenericDAO<Event> implements EventDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(EventDAOImp.class);

  public EventDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(eventTransformer);
  }

  @Override
  public boolean createNewEvent(Event event) {
    final String INSERT_EVENT =
        "INSERT INTO event (title, date, location_id) VALUES (?, ?, ?)";
    boolean res = false;
    try {
      res = insertNew(event, INSERT_EVENT, connection) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Event '{}' wasn't inserted", event.getTitle(), ex);
    }
    return res;
  }

  @Override
  public Event findEventById(int eventId) {
    final String FIND_EVENT_BY_ID =
        "SELECT * FROM event WHERE id = ?";
    try {
      return findByField(eventId, FIND_EVENT_BY_ID, connection);
    } catch (SQLException ex) {
      LOGGER.error("Event with id: {} wasn't found", eventId, ex);
      throw new NoSuchElementException("Event wasn't found");
    }
  }

  @Override
  public Event findEventByTitle(String eventTitle) {
    final String FIND_EVENT_BY_TITLE =
        "SELECT * FROM event WHERE title = ?";
    try {
      return findByField(eventTitle, FIND_EVENT_BY_TITLE, connection);
    } catch (SQLException ex) {
      LOGGER.error("Event with '{}' wasn't found", eventTitle, ex);
      throw new NoSuchElementException("Event wasn't found");
    }
  }

  @Override
  public List<Event> findAllEvents() {
    final String FIND_ALL_EVENTS =
        "SELECT * FROM event";
    try {
      return findAll(FIND_ALL_EVENTS, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Events Error", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public List<Event> findAllEventsByDate(LocalDate date) {
    final String FIND_ALL_EVENTS_BY_DATE =
        "SELECT * FROM event WHERE date = ?";
    try {
      return findAllWithCondition(date, FIND_ALL_EVENTS_BY_DATE, connection);
    } catch (SQLException ex) {
      LOGGER.error("Event on Date '{}' wasn't found", date, ex);
      throw new NoSuchElementException("Event wasn't found");
    }
  }

  @Override
  public List<Event> findAllEventsBySpeaker(User speaker) {
    final String FIND_ALL_EVENTS_BY_SPEAKER =
        "SELECT * FROM event WHERE id IN" +
        "(SELECT event_id FROM report_event re JOIN report r ON r.speaker = ?)";
    try {
      return findAllWithCondition(speaker.getId(), FIND_ALL_EVENTS_BY_SPEAKER, connection);
    } catch (SQLException ex) {
      LOGGER.error("Event involving Speaker with Id: {} wasn't found", speaker.getId(), ex);
      throw new NoSuchElementException("Event wasn't found");
    }
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
  public void addReportToEvent(int reportId, int eventId) {
    final String ADD_REPORT_TO_EVENT =
        "INSERT INTO report_event (report_id, event_id) VALUES (?, ?)";
    try {
      tieRecordsInJoinTable(reportId, eventId, ADD_REPORT_TO_EVENT, connection);
    } catch (SQLException ex) {
      LOGGER.error("Adding Report(id: {}) to the event(id: {}) failed", reportId, eventId, ex);
    }
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
      final int id = rs.getInt("id");
      final String title = rs.getString("title");
      final LocalDate date =
          convertNullable(rs.getDate("date"), Date::toLocalDate);
      final int locationId = rs.getInt("location_id");
//      TODO check the necessary of transfer of the object Location
      final Location location = DAOFactory.getLocationDAO().findByID(locationId);

      return Event.createEvent(id, title, date, location);
    }

    @Override
    public int insertEntity(PreparedStatement prst, Event event) throws SQLException {
      int res;
      prst.setString(1, event.getTitle());
      final Date date = convertNullable(event.getDate(), Date::valueOf);
      prst.setDate(2, date);
      final int locationId = convertNullable(event.getLocation(), Location::getId);
      prst.setInt(3, locationId);
      res = prst.executeUpdate();
      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          event.setId(id);
        }
      }
      return res;
    }
  };

}
