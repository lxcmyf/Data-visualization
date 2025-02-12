package org.tron.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tron.data.dao.TransactionMapper;
import org.tron.data.entity.QueryParam;
import org.tron.data.entity.Transaction;
import org.tron.data.vo.AmountVO;
import org.tron.data.vo.FromAddressVO;
import org.tron.data.vo.TxTypeVO;

@Slf4j
@Service
public class TransactionService {
  private static final String TRONGRID_API_URL_GETNOWBLOCK = "https://api.trongrid.io/walletsolidity/getnowblock?visible=true";
  private static final String TRONGRID_API_URL_GETBLOCKBYNUM = "https://api.trongrid.io/walletsolidity/getblockbynum?visible=true";
  private static final int MAX_RETRIES = 3;
  private static long currentNum = 0;

  @Autowired
  private TransactionMapper transactionMapper;

  public void fetchAndInsertTransactions() {

    // 获取当前区块的数据
    List<Transaction> currentTransactions = fetchCurrentBlockTransactions();

    // 处理当前区块
    if (currentTransactions != null && !currentTransactions.isEmpty()) {
      transactionMapper.insertTransactions(currentTransactions);
      log.info("Inserted {} transactions from block {}", currentTransactions.size(), currentNum);
    }

  }

  private List<Transaction> fetchCurrentBlockTransactions() {
    String url = currentNum == 0 ? TRONGRID_API_URL_GETNOWBLOCK : TRONGRID_API_URL_GETBLOCKBYNUM + "&num=" + currentNum;
    for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
      try {
        String response = callApi(url);
        return parseTransactions(response);
      } catch (Exception e) {
        log.error("Failed to fetch current block (attempt {})", attempt + 1, e);
      }
    }
    log.error("Failed to fetch current block after {} attempts", MAX_RETRIES);
    return null;
  }


  private String callApi(String url) throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();
    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) {
      throw new IOException("Unexpected code " + response);
    }
    assert response.body() != null;
    return response.body().string();
  }

  public static boolean isValidTimestamp(long timestamp) {
    long minTimestamp = 0L;                   // 1970-01-01 00:00:00 UTC
    long maxTimestamp = 253402300799000L;      // 9999-12-31 23:59:59 UTC

    return timestamp > minTimestamp && timestamp < maxTimestamp;
  }

  private static List<Transaction> parseTransactions(String jsonResponse) {
    List<Transaction> transactions = new ArrayList<>();
    JSONObject blockData = JSON.parseObject(jsonResponse);
    JSONObject rawData = blockData.getJSONObject("block_header").getJSONObject("raw_data");
    long timestamp = rawData.getLongValue("timestamp");
    Date updateTime = new Date(timestamp);
    int blockNumber = rawData.getIntValue("number");
    JSONArray transactionsArray = blockData.getJSONArray("transactions");
    if (transactionsArray != null) {
      for (int i = 0; i < transactionsArray.size(); i++) {
        JSONObject transactionNode = transactionsArray.getJSONObject(i);
        Transaction transaction = new Transaction();
        transaction.setBlockNum(blockNumber);
        transaction.setUpdateTime(updateTime);
        transaction.setTxId(transactionNode.getString("txID"));

        JSONObject txRawData = transactionNode.getJSONObject("raw_data");
        JSONObject contract = txRawData.getJSONArray("contract").getJSONObject(0);
        long txTime = txRawData.getLongValue("timestamp");
        if (isValidTimestamp(txTime)) {
          transaction.setTxTime(new Date(txTime));
        } else {
          transaction.setTxTime(updateTime);
        }
        String txType = contract.getString("type");
        transaction.setTxType(txType);
        JSONObject value = contract.getJSONObject("parameter").getJSONObject("value");

        String from = value.getString("owner_address");
        transaction.setFromAddress(from);
        if (from == null && "SmartContract".equals(txType)) {
          transaction.setFromAddress(value.getString("origin_address"));
        }

        String to = value.getString("to_address");
        transaction.setToAddress(to);
        if (to == null &&
            ("FreezeBalanceContract".equals(txType) ||
            "UnfreezeBalanceContract".equals(txType) ||
            "DelegateResourceContract".equals(txType) ||
            "UnDelegateResourceContract".equals(txType))) {
          transaction.setToAddress(value.getString("receiver_address"));
        }

        if ("TransferContract".equals(txType) || "TransferAssetContract".equals(txType)) {
          long amount = value.getLongValue("amount");
          to = value.getString("to_address");
          transaction.setToAddress(to);
          transaction.setAmount(amount);
        }
        transactions.add(transaction);
      }
    }
    currentNum = blockNumber + 1L;
    return transactions;
  }

  public List<FromAddressVO> getFromAddressTop(QueryParam queryParam) {
    return transactionMapper.getFromAddressTop(queryParam);
  }

  public List<TxTypeVO> getTopTxTypeByFromAddress(QueryParam queryParam) {
    return transactionMapper.getTopTxTypeByFromAddress(queryParam);
  }

  public List<AmountVO> getAmountTrendByAddress(QueryParam queryParam) {
    return transactionMapper.getAmountTrendByAddress(queryParam);
  }
}
