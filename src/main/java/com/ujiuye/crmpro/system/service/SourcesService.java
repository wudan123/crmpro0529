package com.ujiuye.crmpro.system.service;

import com.ujiuye.crmpro.system.pojo.Sources;

import java.util.List;

public interface SourcesService {

    List<Sources> listZtree();

    List<Sources> list();

    int save(Sources sources);

    Sources getById(int id);

    int update(Sources sources);

    int remove(int id);
}
