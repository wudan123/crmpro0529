package com.ujiuye.crmpro.forum.service;

import com.ujiuye.crmpro.forum.pojo.Forumsort;

import java.util.List;

public interface ForumsortService {

    List<Forumsort> list();

    //更新帖子数
    int updateCount(int id);
    //更新浏览量
    int updateClick(int id);


}
