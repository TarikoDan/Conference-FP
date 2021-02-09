package com.conference.my.dao;

import com.conference.my.entity.Location;

import java.util.List;

public interface LocationDAO {
  boolean createNew(Location location);
  List<Location> findAll();
  Location findByID(int locationId);
  Location findByHashCode(int hashCode);
}
