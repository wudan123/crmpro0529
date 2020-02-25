package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Module;

import java.util.List;

public interface ModuleService {

    //根据需求id统计模块内又没由关联数据
    int countById(int id);

    List<Module> list(int type, String keyword);

    int save(Module module);

    int update(Module module);

    Module getById(int id);

    int remove(List<Integer> ids);

    List<Module> getByName(String name);

    List<Module> getAnalysisFk(int analysis_fk);
}
