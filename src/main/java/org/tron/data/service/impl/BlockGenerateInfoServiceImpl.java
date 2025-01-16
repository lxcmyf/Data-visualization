package org.tron.data.service.impl;

import org.tron.data.dao.BlockGenerateInfoMapper;
import org.tron.data.entity.BlockGenerateInfo;
import org.tron.data.entity.QueryParam;
import org.tron.data.service.BlockGenerateInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockGenerateInfoServiceImpl implements BlockGenerateInfoService {
  @Autowired
  private BlockGenerateInfoMapper blockGenerateInfoMapper;
  @Override
  public List<BlockGenerateInfo> selectAll(){
    return blockGenerateInfoMapper.selectAll();
  }

  @Override
  public List<BlockGenerateInfo> selectByParam(QueryParam queryParam) {
    return blockGenerateInfoMapper.selectByParam(queryParam);
  }

  @Override
  public void insertBatch(String tableSuffix, List<BlockGenerateInfo> blockGenerateInfoList) {
    blockGenerateInfoMapper.insertBatch(tableSuffix, blockGenerateInfoList);
  }
}
