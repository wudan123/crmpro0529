package com.ujiuye.crmpro.system.controller;

import com.alibaba.fastjson.JSON;
import com.ujiuye.crmpro.project.pojo.Function;
import com.ujiuye.crmpro.system.pojo.Sources;
import com.ujiuye.crmpro.system.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "listZtree" ,produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listZtree(){
        List<Sources> listZtree = sourcesService.listZtree();

        return JSON.toJSONString(listZtree);
    }


    @RequestMapping("/list")
    @ResponseBody
    public String list(){
        List<Sources> list = sourcesService.list();
        return JSON.toJSONString(list);
    }



    @RequestMapping("/save")
    @ResponseBody
    public String save(Sources sources){
        try{
            if (sourcesService.save(sources)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("/show")
    @ResponseBody
    public String show(int id){

        Sources sources = sourcesService.getById(id);

        return JSON.toJSONString(sources);
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(Sources sources){

        try {
            if (sourcesService.update(sources)>0){
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
    public String remove(int id){
        try {
            if (sourcesService.remove(id)>0){
                return "true";
            }
            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }

    }



}
