package org.tron.data.service;

import org.tron.data.entity.BlockGenerateInfo;
import org.tron.data.entity.QueryParam;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BlockGenerateInfoService {
    List<BlockGenerateInfo> selectAll();
    List<BlockGenerateInfo> selectByParam(QueryParam queryParam);

    void insertBatch(String tableSuffix, List<BlockGenerateInfo> blockGenerateInfoList);
}
