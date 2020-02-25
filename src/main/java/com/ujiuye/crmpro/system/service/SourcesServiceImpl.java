package com.ujiuye.crmpro.system.service;

import com.ujiuye.crmpro.system.mapper.SourcesMapper;
import com.ujiuye.crmpro.system.pojo.Sources;
import com.ujiuye.crmpro.system.pojo.SourcesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {
    @Autowired
    private SourcesMapper sourcesMapper;

    @Override
   // @Cacheable(value = "listZtree",key = "'listZtree'")
    public List<Sources> listZtree() {
        SourcesExample example=new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(0);
        List<Sources> sourcesList = sourcesMapper.selectByExample(example);
        if (sourcesList.size()>0){

            for (Sources sources : sourcesList) {
                child(sources);
            }
        }

        return sourcesList;
    }


    private void child(Sources sources){
        Integer id = sources.getId();
        SourcesExample example=new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(id);
        List<Sources> list = sourcesMapper.selectByExample(example);
        if (list.size()>0){
            for (Sources sources1 : list) {
                child(sources1);//递归查询子树
            }
            sources.setChildren(list);
        }


    }

    @Override
   // @Cacheable(value = "list",key = "'list'")
    public List<Sources> list() {

        return sourcesMapper.selectByExample(null);
    }

    @Override
   // @CacheEvict(value = {"listZtree","list","getById"},allEntries = true)
    public int save(Sources sources) {
        return sourcesMapper.insert(sources);
    }

    @Override
   // @Cacheable(value = "getById",key = "'getById'+#id")
    public Sources getById(int id) {
        return sourcesMapper.selectByPrimaryKey(id);
    }

    @Override
    //@CacheEvict(value = {"listZtree","list","getById"},allEntries = true)
    public int update(Sources sources) {
        return sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    @Override
   // @CacheEvict(value = {"listZtree","list","getById"},allEntries = true)
    public int remove(int id) {
        return sourcesMapper.deleteByPrimaryKey(id);
    }
}
