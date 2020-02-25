package com.ujiuye.crmpro.project.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Module;
import com.ujiuye.crmpro.project.service.FunctionService;
import com.ujiuye.crmpro.project.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private FunctionService functionService;


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Module> list = moduleService.list(type, keyword);

        PageInfo<Module> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-module";

    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Module module){
        try {
            if (moduleService.save(module)>0){
                return "true";
            }
            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";

        }


    }

    @RequestMapping("/getById")
    public String getById(Model model,int id){

        Module module = moduleService.getById(id);
        model.addAttribute("module",module);
        return "update-module";
    }

    @RequestMapping("/show")
    public String show(Model model,int id){

        Module module = moduleService.getById(id);
        model.addAttribute("module",module);
        return "show-module";
    }




    @RequestMapping("/update")
    @ResponseBody
    public String update(Module module){
        try{
            if (moduleService.update(module)>0){
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
    public String remove(Integer [] ids){

        List<Integer> list=Arrays.asList(ids);

        List<Integer> remove=new ArrayList<>();
        List<Integer> notRemove=new ArrayList<>();
        String msg="";
        for (int i=0;i<list.size();i++){

            int count = functionService.countByMid(list.get(i));
            if (count>0){
                notRemove.add(list.get(i));
            }else {
                remove.add(list.get(i));
            }

        }
        if (remove.size()==0){
            msg=notRemove.toString();
            return msg;
        }

        try {
            if (moduleService.remove(remove)>0){
                if (notRemove.size()>0){
                    msg=notRemove.toString();
                    return msg;
                }
                return "true";
            }
            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";

        }


    }
    @RequestMapping("/listByAnalysisFk")
    @ResponseBody
    public String listByAnalysisFk(int analysis_fk){

        List<Module> modules = moduleService.getAnalysisFk(analysis_fk);

        return JSON.toJSONString(modules);

    }






}
