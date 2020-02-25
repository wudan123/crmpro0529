package com.ujiuye.crmpro.indexvalue.service;

import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import com.ujiuye.crmpro.indexvalue.mapper.IndexvalueMapper;
import com.ujiuye.crmpro.indexvalue.pojo.DataCollect;
import com.ujiuye.crmpro.indexvalue.pojo.IndexValue;
import com.ujiuye.crmpro.indexvalue.pojo.IndexValueExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IndexValueServiceImpl implements IndexValueService {
    @Autowired
    private IndexvalueMapper indexvalueMapper;
    @Autowired
    private DataCollectService dataCollectService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<IndexValue> list(int type, String keyword) {
        IndexValueExample example=new IndexValueExample();
        IndexValueExample.Criteria criteria = example.createCriteria();
        List<Integer> list=new ArrayList<>();
        list.add(0);

        if (type==1){//目标公司
            List<DataCollect> dataCollects = dataCollectService.getByName(keyword);
            if (dataCollects!=null){
                for (DataCollect dataCollect : dataCollects) {
                    list.add(dataCollect.getDaid());
                }
            }
            criteria.andComnameFkIn(list);
        }
        if (type==2){//指标指定人

            List<Employee> employees = employeeService.getByName(keyword);
            if (employees!=null){
                for (Employee employee : employees) {
                    list.add(employee.getEid());
                }
            }
            criteria.andEmpFk5In(list);

        }


        return indexvalueMapper.selectByExample(example);
    }

    @Override
    public int save(IndexValue indexValue) {
        return indexvalueMapper.insert(indexValue);
    }

    @Override
    public IndexValue getById(int id) {
        return indexvalueMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(IndexValue indexValue) {
        indexValue.setInUpdatetime(new Date());
        return indexvalueMapper.updateByPrimaryKeySelective(indexValue);
    }

    @Override
    public int remove(List<Integer> ids) {
        IndexValueExample example=new IndexValueExample();
        IndexValueExample.Criteria criteria = example.createCriteria();
        criteria.andInIdIn(ids);
        return indexvalueMapper.deleteByExample(example);
    }
}
