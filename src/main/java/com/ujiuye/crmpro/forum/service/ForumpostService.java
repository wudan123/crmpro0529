package com.ujiuye.crmpro.forum.service;

import com.ujiuye.crmpro.forum.pojo.Forumpost;

import java.util.List;

public interface ForumpostService {


    List<Forumpost> list(int id);

    Forumpost getById(int id);

    //更新浏览量
    int update(Forumpost forumpost);

    int save(Forumpost forumpost);

    List<Forumpost> getByEmpFk(int emp_fk);

    int remove(List<Integer> ids);
}
