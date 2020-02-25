package com.ujiuye.crmpro.forum.controller;

import com.alibaba.fastjson.JSON;
import com.ujiuye.crmpro.forum.pojo.Forumsort;
import com.ujiuye.crmpro.forum.service.ForumsortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/forumsort")
public class ForumsortController {

    @Autowired
    private ForumsortService forumsortService;

    @RequestMapping("/list")
    public String list(Model model){

        List<Forumsort> list = forumsortService.list();
        model.addAttribute("list",list);
        return "list-forum-main";

    }


    @RequestMapping("/listJSON")
    @ResponseBody
    public String listJSON(){

        List<Forumsort> list = forumsortService.list();

        return JSON.toJSONString(list);
    }


}
