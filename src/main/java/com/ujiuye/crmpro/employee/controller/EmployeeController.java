package com.ujiuye.crmpro.employee.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getByPfk")
    @ResponseBody
    public String getByPfk(int p_fk){

        List<Employee> list = employeeService.getByPfk(p_fk);

        return JSON.toJSONString(list);

    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(Employee employee, String code, HttpSession session){
        String syscode =(String)session.getAttribute("CODE");
        if (!syscode.equalsIgnoreCase(code)&&code!=null&&!"".equals(code)){
            return "error";
        }
        Employee employee1 = employeeService.login(employee);
        if (employee1!=null){
            session.setAttribute("LOGIN",employee1);
            return "true";
        }

        return "false";
    }

    @RequestMapping("/listJSON")
    @ResponseBody
    public String listJSON(){

        List<Employee> employees = employeeService.listJSON();
        return JSON.toJSONString(employees);
    }


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Employee> list = employeeService.list(type, keyword);

        PageInfo<Employee> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-employee";

    }




}
