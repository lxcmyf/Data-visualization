package org.tron.data.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.tron.data.service.impl.TransactionService;

@Slf4j
@Service
public class FetchBlockTransactionsSchedule {

  @Autowired
  private TransactionService transactionService;

  @Scheduled(fixedRate = 3000)
  public void insertBlockTransactions() {
    transactionService.fetchAndInsertTransactions();
  }
}
