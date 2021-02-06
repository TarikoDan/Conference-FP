package com.conference.my.entity;

public class Report {
  private int id;
  private String topic;
  private Speaker speaker;

  public Report() { }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public Speaker getSpeaker() {
    return speaker;
  }

  public void setSpeaker(Speaker speaker) {
    this.speaker = speaker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Report report = (Report) o;

    return topic.equals(report.topic);
  }

  @Override
  public int hashCode() {
    return topic.hashCode();
  }

  @Override
  public String toString() {
    return "Report{" +
        "id=" + id +
        ", topic='" + topic + '\'' +
        ", speaker=" + speaker +
        '}';
  }

}
