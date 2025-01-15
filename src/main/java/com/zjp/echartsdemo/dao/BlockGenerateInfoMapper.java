package com.zjp.echartsdemo.dao;

import com.zjp.echartsdemo.entity.BlockGenerateInfo;
import com.zjp.echartsdemo.entity.QueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlockGenerateInfoMapper {
    List<BlockGenerateInfo> selectAll();

    List<BlockGenerateInfo> selectByParam(@Param("queryParam") QueryParam queryParam);
}
