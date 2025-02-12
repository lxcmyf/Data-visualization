package org.tron.data.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tron.data.entity.QueryParam;
import org.tron.data.entity.Transaction;
import org.tron.data.vo.AmountVO;
import org.tron.data.vo.FromAddressVO;
import org.tron.data.vo.TxTypeVO;

@Mapper
public interface TransactionMapper {

  void insertTransactions(@Param("list") List<Transaction> transactions);

  List<FromAddressVO> getFromAddressTop(@Param("queryParam") QueryParam queryParam);

  List<TxTypeVO> getTopTxTypeByFromAddress(@Param("queryParam") QueryParam queryParam);

  List<AmountVO> getAmountTrendByAddress(@Param("queryParam") QueryParam queryParam);
}
