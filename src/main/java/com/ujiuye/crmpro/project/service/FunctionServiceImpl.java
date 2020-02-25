package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.mapper.FunctionMapper;
import com.ujiuye.crmpro.project.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionMapper functionMapper;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private ProjectService projectService;

    @Override
    public int countByPid(int pid) {

        FunctionExample example=new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andProFkEqualTo(pid);

        return functionMapper.countByExample(example);
    }

    @Override
    public int countByMid(int mid) {
        FunctionExample example=new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andModuleFkEqualTo(mid);
        return functionMapper.countByExample(example);
    }

    @Override
    public List<Function> list(int type, String keyword) {
        FunctionExample example=new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        List<Integer> list=new ArrayList<>();
        list.add(0);

        if (type==1){

            criteria.andFunctionnameLike("%"+keyword+"%");
        }
        if (type==2){//模块名称
            List<Module> modules = moduleService.getByName(keyword);
            if (modules!=null){

                for (Module module : modules) {
                    list.add(module.getId());
                }
            }
            criteria.andModuleFkIn(list);

        }
        if (type==3){
            List<Analysis> analyses = analysisService.getByName(keyword);
            if (analyses!=null){

                for (Analysis analysis : analyses) {
                    list.add(analysis.getId());
                }
            }
            criteria.andAnalysisFkIn(list);
        }
        if (type==4){
            List<Project> projects = projectService.getByName(keyword);
            if (projects!=null){

                for (Project project : projects) {
                    list.add(project.getPid());
                }
            }
            criteria.andProFkIn(list);
        }

        return functionMapper.selectByExample(example);
    }

    @Override
    public int save(Function function) {
        return functionMapper.insert(function);
    }

    @Override
    public int update(Function function) {
        return functionMapper.updateByPrimaryKeySelective(function);
    }

    @Override
    public Function getById(int id) {

        return functionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int remove(List<Integer> ids) {
        FunctionExample example=new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return functionMapper.deleteByExample(example);
    }

    @Override
    public List<Function> moduleFk(int module_fk) {
        FunctionExample example=new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andModuleFkEqualTo(module_fk);

        return functionMapper.selectByExample(example);
    }

    @Override
    public Function getByFunFk(Integer fun_fk) {

        return functionMapper.selectByPrimaryKey(fun_fk);
    }
}
