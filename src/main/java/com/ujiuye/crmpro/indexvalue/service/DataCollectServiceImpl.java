package com.ujiuye.crmpro.indexvalue.service;

import com.ujiuye.crmpro.indexvalue.mapper.DatacollectMapper;
import com.ujiuye.crmpro.indexvalue.pojo.DataCollect;
import com.ujiuye.crmpro.indexvalue.pojo.DataCollectExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataCollectServiceImpl implements DataCollectService {
    @Autowired
    private DatacollectMapper datacollectMapper;

    @Override
    public List<DataCollect> list() {

        return datacollectMapper.selectByExample(null);
    }

    @Override
    public List<DataCollect> listByDcname(int type, String keyword) {
        DataCollectExample example=new DataCollectExample();
        DataCollectExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("buildtime desc");
        if (type==1){
            criteria.andDacnameLike("%"+keyword+"%");
        }
        if (type==2){
            criteria.andDabusinessLike("%"+keyword+"%");
        }
        return datacollectMapper.selectByExample(example);
    }

    @Override
    public int save(DataCollect dataCollect) {
        return datacollectMapper.insert(dataCollect);
    }

    @Override
    public int update(DataCollect dataCollect) {
        return datacollectMapper.updateByPrimaryKeySelective(dataCollect);
    }

    @Override
    public DataCollect getById(int id) {
        return datacollectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int remove(List<Integer> ids) {
        DataCollectExample example=new DataCollectExample();
        DataCollectExample.Criteria criteria = example.createCriteria();

        criteria.andDaidIn(ids);

        return datacollectMapper.deleteByExample(example);
    }

    @Override
    public List<DataCollect> getByName(String name) {
        DataCollectExample example=new DataCollectExample();
        DataCollectExample.Criteria criteria = example.createCriteria();

        criteria.andDacnameLike("%"+name+"%");


        return datacollectMapper.selectByExample(example);
    }
}
