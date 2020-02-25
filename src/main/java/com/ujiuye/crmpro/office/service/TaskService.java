package com.ujiuye.crmpro.office.service;

import com.ujiuye.crmpro.office.pojo.Task;

import java.util.List;

public interface TaskService {

    int save(Task task);

    List<Task> list(int type,String keyword,int emp_fk2);//emp_fk2不为0代表我的任务列表

    int update(Task task);

    Task getById(int id);


}
