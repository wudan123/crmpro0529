package com.ujiuye.crmpro.forum.service;

import com.ujiuye.crmpro.forum.mapper.EvaluateMapper;
import com.ujiuye.crmpro.forum.pojo.Evaluate;
import com.ujiuye.crmpro.forum.pojo.EvaluateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService{
    @Autowired
    private EvaluateMapper evaluateMapper;
    @Override
    public List<Evaluate> list(int forum_fk) {
        EvaluateExample example=new EvaluateExample();
        EvaluateExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("evatime desc");
        criteria.andForumFkEqualTo(forum_fk);
        return evaluateMapper.selectByExample(example);
    }

    @Override
    public int save(Evaluate evaluate) {
        evaluate.setEvatime(new Date());
        return evaluateMapper.insert(evaluate);
    }

    @Override
    public int remove(int id) {

        return evaluateMapper.deleteByPrimaryKey(id);
    }
}
