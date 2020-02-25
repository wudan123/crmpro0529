package com.ujiuye.crmpro.employee.service;

import com.ujiuye.crmpro.employee.pojo.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getByName(String name);

    List<Employee> getByPfk(int p_fk);

    Employee login(Employee employee);

    List<Employee> listJSON();

    List<Employee> list(int type,String keyword);
}
