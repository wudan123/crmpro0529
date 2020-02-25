package com.ujiuye.crmpro.message.service;

import com.ujiuye.crmpro.message.pojo.Notice;

import java.util.List;

public interface NoticeService {

    int save(Notice notice);

    List<Notice> list(String keyword);

    Notice getById(int id);
}
