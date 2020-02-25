package com.ujiuye.crmpro.employee.service;

import com.ujiuye.crmpro.employee.mapper.ArchivesMapper;
import com.ujiuye.crmpro.employee.pojo.Archives;
import com.ujiuye.crmpro.employee.pojo.ArchivesExample;
import com.ujiuye.crmpro.employee.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    private ArchivesMapper archivesMapper;
    @Autowired
    private EmployeeService employeeService;


    @Override
    public Archives getByEmpFk(int emp_fk) {
        ArchivesExample example=new ArchivesExample();
        ArchivesExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(emp_fk);
        List<Archives> archives = archivesMapper.selectByExample(example);
        if (archives!=null && archives.size()>0){//根据姓名查找邮箱
            return archives.get(0);
        }

        return null;
    }

    @Override
    public int saveTable(List<Archives> archives) {

        return archivesMapper.saveTable(archives);
    }

    @Override
    public List<Archives> list(int type, String keyword) {
        ArchivesExample example=new ArchivesExample();
        ArchivesExample.Criteria criteria = example.createCriteria();
        List<Integer> list=new ArrayList<>();
        list.add(0);
        if (type==1){//姓名
            List<Employee> employees = employeeService.getByName(keyword);
            if (employees!=null && employees.size()>0){
                for (Employee employee : employees) {
                    list.add(employee.getEid());
                }
            }
            criteria.andEmpFkIn(list);

        }
        if (type==2){//学校
            criteria.andSchoolLike("%"+keyword+"%");
        }

        return archivesMapper.selectByExample(example);
    }
}
