package com.ujiuye.crmpro.indexvalue.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.indexvalue.pojo.DataCollect;
import com.ujiuye.crmpro.indexvalue.service.DataCollectService;
import com.ujiuye.crmpro.project.pojo.Analysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/datacollect")
public class DataCollectController {
    @Autowired
    private DataCollectService dataCollectService;

    @RequestMapping("/listJSON")
    @ResponseBody
    public String listJSON(){

        List<DataCollect> list = dataCollectService.list();

        return JSON.toJSONString(list);
    }



    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<DataCollect> list = dataCollectService.listByDcname(type, keyword);

        PageInfo<DataCollect> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-benchmarking";

    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(DataCollect dataCollect){
        try{
            if (dataCollectService.save(dataCollect)>0){
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

        DataCollect dataCollect = dataCollectService.getById(id);

        model.addAttribute("dataCollect",dataCollect);

        return "show-benchmarking";


    }


    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,int id){

        DataCollect dataCollect = dataCollectService.getById(id);

        model.addAttribute("dataCollect",dataCollect);

        return "update-benchmarking";


    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(DataCollect dataCollect){
        try{
            if ( dataCollectService.update(dataCollect)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Integer[] ids){
        List<Integer> list= Arrays.asList(ids);
        try{
            if (dataCollectService.remove(list)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/getByIdJSON")
    @ResponseBody
    public String getByIdJSON(int id){

        DataCollect dataCollect = dataCollectService.getById(id);

        return JSON.toJSONString(dataCollect);
    }


}
