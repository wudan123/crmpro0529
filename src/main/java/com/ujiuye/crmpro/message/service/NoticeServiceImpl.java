package com.ujiuye.crmpro.message.service;

import com.ujiuye.crmpro.message.mapper.NoticeMapper;
import com.ujiuye.crmpro.message.pojo.Notice;
import com.ujiuye.crmpro.message.pojo.NoticeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int save(Notice notice) {
        notice.setDate(new Date());
        return noticeMapper.insert(notice);
    }

    @Override
    public List<Notice> list(String keyword) {
        NoticeExample example=new NoticeExample();
        example.setOrderByClause("date desc");//按日期降序
        if (keyword!=null &&!"".equals(keyword)){
            NoticeExample.Criteria criteria = example.createCriteria();
            criteria.andTitleLike("%"+keyword+"%");
        }
        return noticeMapper.selectByExample(example);
    }

    @Override
    public Notice getById(int id) {

        return noticeMapper.selectByPrimaryKey(id);
    }
}
