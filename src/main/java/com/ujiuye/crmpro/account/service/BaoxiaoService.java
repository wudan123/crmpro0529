package com.ujiuye.crmpro.account.service;

import com.ujiuye.crmpro.account.pojo.Baoxiao;

import java.util.List;

public interface BaoxiaoService {

    List<Baoxiao> list(int type,String keyword,int status,int eid);//eid代表查询登陆人的报销  状态0驳回 1待审批 2待付款 3已付款

    int save(Baoxiao baoxiao);

    int update(Baoxiao baoxiao);

    Baoxiao getById(String id);

    int remove(String id);

    List<Baoxiao> listByEid(int eid);
}
