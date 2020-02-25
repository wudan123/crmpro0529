package com.ujiuye.crmpro.project.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Project;
import com.ujiuye.crmpro.project.service.AnalysisService;
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
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private ModuleService moduleService;


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Analysis> list = analysisService.list(type, keyword);

        PageInfo<Analysis> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-analysis";

    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(Analysis analysis){
        try{
            if ( analysisService.save(analysis)>0){
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

        Analysis analysis = analysisService.show(id);

        model.addAttribute("analysis",analysis);

        return "show-analysis";


    }


    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,int id){

        Analysis analysis = analysisService.show(id);

        model.addAttribute("analysis",analysis);

        return "update-analysis";


    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Analysis analysis){
        try{
            if ( analysisService.update(analysis)>0){
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
        /**
         * controller不能封装List、map、set等集合
         * 将数组转换成集合，很重要！！！！
         */
        List<Integer> list=Arrays.asList(ids);
        List<Integer> remove=new ArrayList<>();
        List<Integer> notRemove=new ArrayList<>();
        String msg="";

        for (int i=0;i<list.size();i++){

            int module = moduleService.countById(list.get(i));
            if (module>0){
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
            if (analysisService.remove(remove)>0){
                if (notRemove.size()>0){
                    msg=notRemove.toString();
                    return msg;
                }
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }


    }

    /**
     * 返回项目需求JSON数据
     * @param id
     * @return
     */
    @RequestMapping("/getById")
    @ResponseBody
    public String getById(int id){

        Analysis analysis = analysisService.getById(id);

        return JSON.toJSONString(analysis);
    }



}
