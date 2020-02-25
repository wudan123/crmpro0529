package com.ujiuye.crmpro.message.service;

import com.ujiuye.crmpro.message.mapper.MsgMapper;
import com.ujiuye.crmpro.message.pojo.Msg;
import com.ujiuye.crmpro.message.pojo.MsgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgMapper msgMapper;

    @Override
    public List<Msg> list(String keyword, int type, int status, int receive) {
        MsgExample example=new MsgExample();
        MsgExample.Criteria criteria = example.createCriteria();
        if (keyword!=null && !"".equals(keyword)){
            criteria.andTitleLike("%"+keyword+"%");
        }
        if (type!=0){//标签
            criteria.andTypeEqualTo(type);
        }
        if (status>=2){//0 1代表已读和未读
            criteria.andStatusEqualTo(status);
        }else{
            List<Integer> list=new ArrayList<>();
            list.add(0);
            list.add(1);
            criteria.andStatusIn(list);
        }
        criteria.andReceiveEqualTo(receive);//查当前登陆者的信


        return msgMapper.selectByExample(example);
    }

    @Override
    public int save(Msg msg) {
        msg.setTime(new Date());
        return msgMapper.insert(msg);
    }

    @Override
    public Msg getById(int id) {

        return msgMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(List<Msg> msgs) {
        for (Msg msg : msgs) {

            msg.setStatus(3);
            msgMapper.updateByPrimaryKeySelective(msg);
        }

        return 1;
    }
    public int update(Msg msg) {

        return msgMapper.updateByPrimaryKey(msg);
    }

    @Override
    public int countByStatus(int status,int receive) {
        MsgExample example=new MsgExample();
        MsgExample.Criteria criteria = example.createCriteria();
        if (status>=2){
            criteria.andStatusEqualTo(status);
        }else {
            List<Integer> list=new ArrayList<>();
            list.add(0);
            list.add(1);
            criteria.andStatusIn(list);
        }
        criteria.andReceiveEqualTo(receive);
        return msgMapper.countByExample(example);
    }

    @Override
    public List<Msg> getByIds(List<Integer> ids) {
        MsgExample example=new MsgExample();
        MsgExample.Criteria criteria = example.createCriteria();

        criteria.andMsgidIn(ids);

        return msgMapper.selectByExample(example);
    }
}
