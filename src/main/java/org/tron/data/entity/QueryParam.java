package org.tron.data.entity;

public class QueryParam {
  public String getIpSuffix() {
    return ipSuffix;
  }

  public void setIpSuffix(String ipSuffix) {
    this.ipSuffix = ipSuffix;
  }

  private String ipSuffix;
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  private String startTime;
  private String endTime;
}
