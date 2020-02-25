package com.ujiuye.crmpro.indexvalue.mapper;

import com.ujiuye.crmpro.indexvalue.pojo.IndexValue;
import com.ujiuye.crmpro.indexvalue.pojo.IndexValueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexvalueMapper {
    int countByExample(IndexValueExample example);

    int deleteByExample(IndexValueExample example);

    int deleteByPrimaryKey(Integer inId);

    int insert(IndexValue record);

    int insertSelective(IndexValue record);

    List<IndexValue> selectByExample(IndexValueExample example);

    IndexValue selectByPrimaryKey(Integer inId);

    int updateByExampleSelective(@Param("record") IndexValue record, @Param("example") IndexValueExample example);

    int updateByExample(@Param("record") IndexValue record, @Param("example") IndexValueExample example);

    int updateByPrimaryKeySelective(IndexValue record);

    int updateByPrimaryKey(IndexValue record);
}