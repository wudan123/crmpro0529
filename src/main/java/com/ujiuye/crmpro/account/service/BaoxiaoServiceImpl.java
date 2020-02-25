package com.ujiuye.crmpro.account.service;

import com.ujiuye.crmpro.account.mapper.BaoxiaoMapper;
import com.ujiuye.crmpro.account.pojo.Baoxiao;
import com.ujiuye.crmpro.account.pojo.BaoxiaoExample;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaoxiaoServiceImpl implements BaoxiaoService {
    @Autowired
    private BaoxiaoMapper baoxiaoMapper;
    @Autowired
    private EmployeeService employeeService;


    @Override
    public List<Baoxiao> list(int type, String keyword, int status, int eid) {
        BaoxiaoExample example=new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();

        if (type==1){//报销单号
            criteria.andBxidLike("%"+keyword+"%");
        }
        if (type==2){//报销人
            List<Employee> employees = employeeService.getByName(keyword);
            List<Integer> list=new ArrayList<>();
            list.add(0);
            if (employees!=null&&employees.size()>0){

                for (Employee employee : employees) {
                    list.add(employee.getEid());
                }

            }
            criteria.andEmpFkIn(list);
        }
        if (status<4){//按报销状态
            criteria.andBxstatusEqualTo(status);
        }
        if (eid!=0){
            criteria.andEmpFkEqualTo(eid);
        }


        return baoxiaoMapper.selectByExample(example);
    }

    @Override
    public int save(Baoxiao baoxiao) {

        return baoxiaoMapper.insert(baoxiao);
    }

    @Override
    public int update(Baoxiao baoxiao) {

        return baoxiaoMapper.updateByPrimaryKeySelective(baoxiao);//只修改单个状态
    }

    @Override
    public Baoxiao getById(String id) {

        return baoxiaoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int remove(String id) {
        return baoxiaoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Baoxiao> listByEid(int eid) {
        BaoxiaoExample example=new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);

        return baoxiaoMapper.selectByExample(example);
    }
}
