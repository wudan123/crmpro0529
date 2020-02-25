package com.ujiuye.crmpro.message.service;

import com.ujiuye.crmpro.message.pojo.Msg;

import java.util.List;

public interface MsgService {

    List<Msg> list(String keyword,int type,int status,int receive);

    int save(Msg msg);

    Msg getById(int id);

    int update(List<Msg> msgs);

    int update(Msg msg);


    int countByStatus(int status,int receive);

    List<Msg> getByIds(List<Integer> ids);


}
