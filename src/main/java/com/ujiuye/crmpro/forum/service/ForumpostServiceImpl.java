package com.ujiuye.crmpro.forum.service;

import com.ujiuye.crmpro.forum.mapper.ForumpostMapper;
import com.ujiuye.crmpro.forum.pojo.Forumpost;
import com.ujiuye.crmpro.forum.pojo.ForumpostExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ForumpostServiceImpl implements ForumpostService {
    @Autowired
    private ForumpostMapper forumpostMapper;

    @Override
    public List<Forumpost> list(int id) {
        ForumpostExample example=new ForumpostExample();
        ForumpostExample.Criteria criteria = example.createCriteria();
        criteria.andForumsortFkEqualTo(id);


        return forumpostMapper.selectByExample(example);
    }

    @Override
    public Forumpost getById(int id) {
        return forumpostMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Forumpost forumpost) {
        return forumpostMapper.updateByPrimaryKeySelective(forumpost);
    }

    @Override
    public int save(Forumpost forumpost) {
        forumpost.setCreatetime(new Date());
        forumpost.setClick(0);
        forumpost.setCommentCount(0);
        return forumpostMapper.insert(forumpost);
    }

    @Override
    public List<Forumpost> getByEmpFk(int emp_fk) {
        ForumpostExample example=new ForumpostExample();
        ForumpostExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFk3EqualTo(emp_fk);
        return forumpostMapper.selectByExample(example);
    }

    @Override
    public int remove(List<Integer> ids) {
        ForumpostExample example=new ForumpostExample();
        ForumpostExample.Criteria criteria = example.createCriteria();
        criteria.andForumidIn(ids);
        return forumpostMapper.deleteByExample(example);
    }
}
