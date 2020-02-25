package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.customer.mapper.CustomerMapper;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.customer.pojo.CustomerExample;
import com.ujiuye.crmpro.customer.service.CustomerService;
import com.ujiuye.crmpro.employee.mapper.EmployeeMapper;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import com.ujiuye.crmpro.project.mapper.ProjectMapper;
import com.ujiuye.crmpro.project.pojo.Project;
import com.ujiuye.crmpro.project.pojo.ProjectExample;
import com.ujiuye.crmpro.project.pojo.ProjectExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Project> list(int type, String keyword) {
        ProjectExample example=new ProjectExample();
        Criteria criteria = example.createCriteria();
        if (type==1){

            criteria.andNameLike("%"+keyword+"%");

        }
        if (type==2){
            List<Customer> customers = customerService.getByName(keyword);
            List<Integer> list=new ArrayList<>();
            list.add(0);

            if (customers!=null && customers.size()>0){


                for (Customer customer : customers) {

                    list.add(customer.getId());

                }
            }
            criteria.andCompanynameIn(list);


        }
        if (type==3){

            criteria.andCompanypersonLike("%"+keyword+"%");

        }
        if(type==4){//按项目经理
            List<Employee> employees = employeeService.getByName(keyword);
            List<Integer> list=new ArrayList<>();
            list.add(0);
            if(employees!=null && employees.size()>0){
                for (Employee employee:employees) {
                    list.add(employee.getEid());
                }
            }
            criteria.andEmpFkIn(list);
        }
        /*
        if (type==4){
            List<Employee> employees = employeeService.getByName(keyword);

            List<Integer> list=new ArrayList<>();
            list.add(0); //确保list集合不为空

            if (employees!=null && employees.size()>0){
                for (Employee employee : employees) {
                    list.add(employee.getEid());
                }
                criteria.andEmpFkIn(list); //位置！！！！
            }

        }*/

        return projectMapper.selectByExample(example);
    }

    @Override
    public int save(Project project) {
        return projectMapper.insert(project);
    }


    @Override
    public int remove(List<Integer> ids) {
        ProjectExample example=new ProjectExample();
        Criteria criteria = example.createCriteria();
        criteria.andPidIn(ids);
        return projectMapper.deleteByExample(example);
    }

    @Override
    public Project getById(int pid) {

        return projectMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(Project project) {

        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<Project> getByName(String name) {
        ProjectExample example=new ProjectExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        return projectMapper.selectByExample(example);
    }
}
