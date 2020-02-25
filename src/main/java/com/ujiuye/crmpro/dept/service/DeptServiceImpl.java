package com.ujiuye.crmpro.dept.service;

import com.ujiuye.crmpro.dept.mapper.DeptMapper;
import com.ujiuye.crmpro.dept.pojo.Dept;
import com.ujiuye.crmpro.dept.pojo.DeptExample;
import com.ujiuye.crmpro.dept.pojo.DeptExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list(int type, String keyword) {
        DeptExample example=new DeptExample();
        Criteria criteria = example.createCriteria();
        if (type==1){
            criteria.andDnameLike("%"+keyword+"%");
        }else if (type==2){
            criteria.andDlocationLike("%"+keyword+"%");
        }


        return deptMapper.selectByExample(example);
    }

    @Override
    public int save(Dept dept) {

        return deptMapper.insert(dept);
    }

    @Override
    public int update(Dept dept) {



        return deptMapper.updateByPrimaryKeySelective(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int remove(List<Integer> ids) {
        DeptExample example=new DeptExample();
        Criteria criteria = example.createCriteria();
        criteria.andDeptnoIn(ids);
        return deptMapper.deleteByExample(example);
    }
}
