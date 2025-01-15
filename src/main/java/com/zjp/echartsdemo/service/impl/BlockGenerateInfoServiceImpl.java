package com.zjp.echartsdemo.service.impl;

import com.zjp.echartsdemo.dao.BlockGenerateInfoMapper;
import com.zjp.echartsdemo.entity.BlockGenerateInfo;
import com.zjp.echartsdemo.entity.QueryParam;
import com.zjp.echartsdemo.service.BlockGenerateInfoService;
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
}
