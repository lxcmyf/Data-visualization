package org.tron.data.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmountVO {
  private Long amount;
  private Date txTime;
}
