package com.ujiuye.crmpro.indexvalue.service;

import com.ujiuye.crmpro.indexvalue.pojo.DataCollect;

import java.util.List;

public interface DataCollectService {
    List<DataCollect> list();

    List<DataCollect> listByDcname(int type,String keyword);

    int save(DataCollect dataCollect);

    int update(DataCollect dataCollect);

    DataCollect getById(int id);

    int remove(List<Integer> ids);

    List<DataCollect> getByName(String name);
}
