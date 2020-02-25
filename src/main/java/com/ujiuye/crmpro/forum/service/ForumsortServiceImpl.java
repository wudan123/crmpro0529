package com.ujiuye.crmpro.forum.service;

import com.ujiuye.crmpro.forum.mapper.ForumsortMapper;
import com.ujiuye.crmpro.forum.pojo.Forumsort;
import com.ujiuye.crmpro.forum.pojo.ForumsortExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumsortServiceImpl implements ForumsortService {
    @Autowired
    private ForumsortMapper forumsortMapper;
    @Override
    public List<Forumsort> list() {
        //按版块升序 主版0 副版1
        ForumsortExample example=new ForumsortExample();
        example.setOrderByClause("type asc");

        return forumsortMapper.selectByExample(example);
    }

    @Override
    public int updateCount(int id) {
        Forumsort forumsort = forumsortMapper.selectByPrimaryKey(id);
        forumsort.setCount(forumsort.getCount()+1);

        return forumsortMapper.updateByPrimaryKeySelective(forumsort);
    }

    @Override
    public int updateClick(int id) {
        Forumsort forumsort = forumsortMapper.selectByPrimaryKey(id);
        forumsort.setClick(forumsort.getClick()+1);

        return forumsortMapper.updateByPrimaryKeySelective(forumsort);
    }
}
