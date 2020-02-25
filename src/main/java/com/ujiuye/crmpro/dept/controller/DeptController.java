package com.ujiuye.crmpro.dept.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.dept.pojo.Dept;
import com.ujiuye.crmpro.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum){

        PageHelper.startPage(pageNum,3);
        List<Dept> list = deptService.list(type, keyword);

        PageInfo<Dept> page=new PageInfo<>(list);

        model.addAttribute("list",list);
        model.addAttribute("page",page);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-dept";



    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Dept dept){


     //   System.out.println(dept.getDname());

        try {
            if (deptService.save(dept)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "false";

        }

    }

    @RequestMapping("/toUpdate")
    @ResponseBody
    public String toUpdate(Model model,Integer deptno){

        Dept dept = deptService.getById(deptno);
       // model.addAttribute("dept",dept);

        return JSON.toJSONString(dept);

    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Dept dept){
      //  System.out.println(dept.getDname());

        try {
            if (deptService.update(dept)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            return "false";

        }

    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Integer[] ids){
        List<Integer> list= Arrays.asList(ids);

        try {
            if (deptService.remove(list)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            return "false";

        }

    }
}
