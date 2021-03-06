package com.ujiuye.crmpro.system.mapper;

import com.ujiuye.crmpro.system.pojo.Sources;
import com.ujiuye.crmpro.system.pojo.SourcesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SourcesMapper {
    int countByExample(SourcesExample example);

    int deleteByExample(SourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sources record);

    int insertSelective(Sources record);

    List<Sources> selectByExample(SourcesExample example);

    Sources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByExample(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByPrimaryKeySelective(Sources record);

    int updateByPrimaryKey(Sources record);
}