package com.ujiuye.crmpro.office.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.office.pojo.Task;
import com.ujiuye.crmpro.office.service.TaskService;
import com.ujiuye.crmpro.project.pojo.Function;
import com.ujiuye.crmpro.project.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private FunctionService functionService;


    @RequestMapping("/save")
    @ResponseBody
    public String save(Task task, HttpSession session){
        Employee login = (Employee) session.getAttribute("LOGIN");
        task.setEmpFk(login.getEid());//创建任务emp_fk就是当前登陆者的id
        try {
            if (taskService.save(task)>0){
                return "true";
            }
            return "false";
        }catch(Exception e){
            e.printStackTrace();
            return "false";

        }
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Task> list = taskService.list(type, keyword,0);

        PageInfo<Task> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-task";
    }

    @RequestMapping("/myList")
    public String myList(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize,
                         HttpSession session){

        PageHelper.startPage(pageNum,pageSize);
        //查询我的任务，当前登陆人的id
        Employee login = (Employee) session.getAttribute("LOGIN");
        Integer eid = login.getEid();

        List<Task> list = taskService.list(type, keyword,eid);

        PageInfo<Task> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-mytask";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model model, int id){

        Task task = taskService.getById(id);

        //调用functionService,查到该任务的功能，放到域中提供前台回显数据
        Function function = functionService.getByFunFk(task.getFunFk());

        model.addAttribute("function",function);


        model.addAttribute("task",task);
        return "update-task";

    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(Task task){
        try {
            if (taskService.update(task)>0){
                return "true";
            }
            return "false";
        }catch(Exception e){
            e.printStackTrace();
            return "false";

        }
    }

    @RequestMapping("/show")
    public String show(Model model,int id){
        Task task = taskService.getById(id);
        model.addAttribute("task",task);
        return "show-task";
    }
}
