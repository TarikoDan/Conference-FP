package com.conference.my.entity;

public class Report {
  private int id;
  private final String topic;
  private Speaker speaker;

  private Report(String topic) {
    this.topic = topic;
  }

  public void setSpeaker(Speaker speaker) {
    this.speaker = speaker;
  }

  public static Report createReport(String topic){
    return new Report(topic);
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
