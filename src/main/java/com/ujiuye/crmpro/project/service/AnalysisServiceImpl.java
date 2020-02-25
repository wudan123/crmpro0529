package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.mapper.AnalysisMapper;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.AnalysisExample;
import com.ujiuye.crmpro.project.pojo.AnalysisExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public int countByPid(int pid) {
        AnalysisExample example=new AnalysisExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(pid);
        return analysisMapper.countByExample(example);
    }

    @Override
    public List<Analysis> list(int type,String keyword) {
        AnalysisExample example=new AnalysisExample();
        Criteria criteria = example.createCriteria();
        if (type==1){
            criteria.andTitleLike("%"+keyword+"%");
        }
        if (type==2){
            criteria.andPronameLike("%"+keyword+"%");
        }
        return analysisMapper.selectByExample(example);
    }

    @Override
    public int save(Analysis analysis) {

        analysis.setAddtime(new Date());

        return analysisMapper.insert(analysis);
    }

    @Override
    public int update(Analysis analysis) {
        analysis.setUpdatetime(new Date());

        return analysisMapper.updateByPrimaryKeySelective(analysis);
    }

    @Override
    public Analysis show(int id) {

        return analysisMapper.selectByPrimaryKey(id);
    }

    @Override
    public int remove(List<Integer> ids) {
        AnalysisExample example=new AnalysisExample();
        Criteria criteria = example.createCriteria();


        criteria.andIdIn(ids);// 多个id用in表示范围

        return analysisMapper.deleteByExample(example);
    }

    @Override
    public Analysis getById(int id) {

        return analysisMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Analysis> getByName(String name) {
        AnalysisExample example=new AnalysisExample();
        Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+name+"%");
        return analysisMapper.selectByExample(example);
    }
}
