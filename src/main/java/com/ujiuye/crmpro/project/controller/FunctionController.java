package com.ujiuye.crmpro.project.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Function;
import com.ujiuye.crmpro.project.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Function> list = functionService.list(type, keyword);

        PageInfo<Function> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-function";

    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Function function){
        try{
            if ( functionService.save(function)>0){
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

        Function function = functionService.getById(id);
        model.addAttribute("function",function);

        return "show-function";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,int id){
        Function function = functionService.getById(id);
        model.addAttribute("function",function);

        return "update-function";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Function function){

        try {
            if (functionService.update(function)>0){
                return "true";
            }
            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }

    }


    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Integer[] ids){
        List<Integer> list= Arrays.asList(ids);
        try {
            if (functionService.remove(list)>0){
                return "true";
            }
            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/moduleByFk")
    @ResponseBody
    public String moduleByFk(int module_fk){

        List<Function> list = functionService.moduleFk(module_fk);
        return JSON.toJSONString(list);

    }


}
