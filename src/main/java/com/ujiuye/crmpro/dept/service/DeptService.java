package com.ujiuye.crmpro.dept.service;

import com.ujiuye.crmpro.dept.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list(int type,String keyword);

    int save(Dept dept);

    int update(Dept dept);

    Dept getById(Integer id);

    int remove(List<Integer> ids);
}
