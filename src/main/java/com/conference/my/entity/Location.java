package com.conference.my.entity;

public class Location {
  private int id;
  private int zipCode;
  private String country;
  private String region;
  private String city;
  private String street;
  private String building;
  private String suite;

  private Location() {
  }

  public static Builder newBuilder() {
    return new Location().new Builder();
  }


  class Builder {
    private Builder() {
    }

    public Builder id(int id) {
      Location.this.id = id;
      return this;
    }

    public Builder zipCode(int zipCode) {
      Location.this.zipCode = zipCode;
      return this;
    }

    public Builder country(String country) {
      Location.this.country = country;
      return this;
    }

    public Location build() {
      return Location.this;
    }

  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Location{")
        .append(0 == id ? "" : "id: " + id + ", ")
        .append(0 == zipCode ? "" : "zipCode: " + zipCode + ", ")
        .append(null == country ? "" : "country: " + country + ", ")
        .append(null == region ? "" : "region: " + region + ", ")
        .append(null == city ? "" : "city: " + city + ", ")
        .append(null == street ? "" : "street: " + street + ", ")
        .append(null == building ? "" : "building: " + building + ", ")
        .append(null == suite ? "" : "suite: " + suite + ", ")
        .delete(sb.lastIndexOf(",") > 0 ? sb.lastIndexOf(",") : sb.length(), sb.length());
    sb.append('}');
    return sb.toString();
  }

}
