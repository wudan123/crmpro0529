package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Function;

import java.util.List;

public interface FunctionService {

    int countByPid(int pid);

    int countByMid(int mid);

    List<Function> list(int type, String keyword);

    int save(Function function);

    int update(Function function);

    Function getById(int id);

    int remove(List<Integer> ids);

    List<Function> moduleFk(int module_fk);

    Function getByFunFk(Integer fun_fk);
}
