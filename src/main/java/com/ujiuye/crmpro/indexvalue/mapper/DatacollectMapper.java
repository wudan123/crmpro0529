package com.ujiuye.crmpro.indexvalue.mapper;

import com.ujiuye.crmpro.indexvalue.pojo.DataCollect;
import com.ujiuye.crmpro.indexvalue.pojo.DataCollectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DatacollectMapper {
    int countByExample(DataCollectExample example);

    int deleteByExample(DataCollectExample example);

    int deleteByPrimaryKey(Integer daid);

    int insert(DataCollect record);

    int insertSelective(DataCollect record);

    List<DataCollect> selectByExample(DataCollectExample example);

    DataCollect selectByPrimaryKey(Integer daid);

    int updateByExampleSelective(@Param("record") DataCollect record, @Param("example") DataCollectExample example);

    int updateByExample(@Param("record") DataCollect record, @Param("example") DataCollectExample example);

    int updateByPrimaryKeySelective(DataCollect record);

    int updateByPrimaryKey(DataCollect record);
}