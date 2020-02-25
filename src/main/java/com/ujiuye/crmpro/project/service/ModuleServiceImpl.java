package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.mapper.ModuleMapper;
import com.ujiuye.crmpro.project.pojo.Module;
import com.ujiuye.crmpro.project.pojo.ModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public int countById(int id) {
        ModuleExample example=new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAnalysisFkEqualTo(id);

        return moduleMapper.countByExample(example);
    }

    @Override
    public List<Module> list(int type, String keyword) {
        ModuleExample example=new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        if (type==1){
            criteria.andModulenameLike("%"+keyword+"%");
        }
        if (type==2){
            criteria.andProjectnameLike("%"+keyword+"%");
        }


        return moduleMapper.selectByExample(example);
    }

    @Override
    public int save(Module module) {

        return moduleMapper.insert(module);
    }

    @Override
    public int update(Module module) {

        return moduleMapper.updateByPrimaryKeySelective(module);
    }

    @Override
    public Module getById(int id) {

        return moduleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int remove(List<Integer> ids) {
        ModuleExample example=new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return moduleMapper.deleteByExample(example);
    }

    @Override
    public List<Module> getByName(String name) {
        ModuleExample example=new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();

        criteria.andModulenameLike("%"+name+"%");

        return moduleMapper.selectByExample(example);
    }

    @Override
    public List<Module> getAnalysisFk(int analysis_fk) {
        ModuleExample example=new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAnalysisFkEqualTo(analysis_fk);

        return moduleMapper.selectByExample(example);
    }
}
