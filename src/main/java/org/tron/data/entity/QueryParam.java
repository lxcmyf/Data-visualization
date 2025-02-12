package org.tron.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryParam {
  private String ipSuffix;
  private String startTime;
  private String endTime;
  private String fromAddress;
  private String txType;
}
