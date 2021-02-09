package com.conference.my.entity;

public class Report extends Entity{
  private String topic;
  private User speaker;

  public Report() { }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public User getSpeaker() {
    return speaker;
  }

  public void setSpeaker(User speaker) {
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
