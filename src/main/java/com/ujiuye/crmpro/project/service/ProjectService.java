package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Project;

import java.util.List;

public interface ProjectService {

    List<Project> list(int type,String keyword);

    int save(Project project);

    int remove(List<Integer> ids);

    Project getById(int pid);

    int update(Project project);

    List<Project> getByName(String name);
}
