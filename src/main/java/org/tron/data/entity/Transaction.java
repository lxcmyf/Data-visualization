package org.tron.data.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
  private String txId;
  private Integer blockNum;
  private Date updateTime;
  private String txType;
  private String methodId;
  private String methodName;
  private String fromAddress;
  private String toAddress;
  private Integer token;
  private String tokenSymbol;
  private Long amount;
  private Integer result;
  private Integer status;
}
