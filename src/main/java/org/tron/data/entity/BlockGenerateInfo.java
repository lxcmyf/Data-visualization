package org.tron.data.entity;

import java.util.Date;

public class BlockGenerateInfo {
  private long height;
  private long cost;
  private long txNum;

  public String getUpdateTimeStr() {
    return updateTimeStr;
  }

  public void setUpdateTimeStr(String updateTimeStr) {
    this.updateTimeStr = updateTimeStr;
  }

  private String updateTimeStr;

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  private Date updateTime;

  public long getHeight() {
    return height;
  }

  public void setHeight(long height) {
    this.height = height;
  }

  public long getCost() {
    return cost;
  }

  public void setCost(long cost) {
    this.cost = cost;
  }

  public long getTxNum() {
    return txNum;
  }

  public void setTxNum(long txNum) {
    this.txNum = txNum;
  }

}
