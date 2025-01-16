package org.tron.data.dao;

import org.tron.data.entity.BlockGenerateInfo;
import org.tron.data.entity.QueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlockGenerateInfoMapper {
    List<BlockGenerateInfo> selectAll();

    List<BlockGenerateInfo> selectByParam(@Param("queryParam") QueryParam queryParam);

    void insertBatch(@Param("tableSuffix") String tableSuffix, @Param("records") List<BlockGenerateInfo> records);
}
