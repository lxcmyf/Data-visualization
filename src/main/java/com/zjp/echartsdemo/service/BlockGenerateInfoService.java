package com.zjp.echartsdemo.service;

import com.zjp.echartsdemo.entity.BlockGenerateInfo;
import com.zjp.echartsdemo.entity.QueryParam;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BlockGenerateInfoService {
    List<BlockGenerateInfo> selectAll();
    List<BlockGenerateInfo> selectByParam(QueryParam queryParam);
}
