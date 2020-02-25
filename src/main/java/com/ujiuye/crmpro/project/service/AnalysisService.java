package com.ujiuye.crmpro.project.service;


import com.ujiuye.crmpro.project.pojo.Analysis;

import java.util.List;

public interface AnalysisService {
    int countByPid(int pid);//根据pid统计是否有和project表关联的数据

    List<Analysis> list(int type,String keyword);

    int save(Analysis analysis);

    int update(Analysis analysis);

    Analysis show(int id);

    int remove(List<Integer> ids);

    Analysis getById(int id);//获取项目需求Json数据,用于返回前台显示

    List<Analysis> getByName(String name);

}
