package com.ujiuye.crmpro.office.service;

import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import com.ujiuye.crmpro.office.mapper.TaskMapper;
import com.ujiuye.crmpro.office.pojo.Task;
import com.ujiuye.crmpro.office.pojo.TaskExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public int save(Task task) {
        task.setStatus(0);//当前任务状态 0代表未开始
        return taskMapper.insert(task);
    }

    @Override
    public List<Task> list(int type, String keyword, int emp_fk2) {
        TaskExample example=new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        List<Integer> list=new ArrayList<>();
        list.add(0);

        if (emp_fk2>0){// 大于0代表查询我的任务，等于0代表查询所有任务信息
            criteria.andEmpFk2EqualTo(emp_fk2);
        }
        if (type==1){
            criteria.andTasktitleLike("%"+keyword+"%");
        }
        if (type==2){
            List<Employee> employees = employeeService.getByName("%" + keyword + "%");

            if (employees!=null && employees.size()>0){
                for (Employee employee : employees) {
                    list.add(employee.getEid());
                }
            }
            criteria.andEmpFk2In(list);
        }
        if (type==3){
            List<Employee> employees = employeeService.getByName("%" + keyword + "%");
            if (employees!=null && employees.size()>0){
                for (Employee employee : employees) {
                    list.add(employee.getEid());
                }
            }
            criteria.andEmpFkIn(list);
        }


        return taskMapper.selectByExample(example);
    }

    @Override
    public int update(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public Task getById(int id) {
        return taskMapper.selectByPrimaryKey(id);
    }
}
