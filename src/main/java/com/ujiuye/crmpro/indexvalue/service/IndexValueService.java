package com.ujiuye.crmpro.indexvalue.service;

import com.ujiuye.crmpro.indexvalue.pojo.IndexValue;

import java.util.List;

public interface IndexValueService {

    List<IndexValue> list(int type, String keyword);

    int save(IndexValue indexValue);

    IndexValue getById(int id);

    int update(IndexValue indexValue);

    int remove(List<Integer> ids);


}
