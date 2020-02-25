package com.ujiuye.crmpro.forum.controller;

import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.forum.pojo.Evaluate;
import com.ujiuye.crmpro.forum.pojo.Forumpost;
import com.ujiuye.crmpro.forum.service.EvaluateService;
import com.ujiuye.crmpro.forum.service.ForumpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private ForumpostService forumpostService;

    @RequestMapping("/save")
    @ResponseBody
    public String save(Evaluate evaluate, HttpSession session){

        Employee login =(Employee) session.getAttribute("LOGIN");
        // 发表评论人
        evaluate.setEmpFk4(login.getEid());
        try {
            if (evaluateService.save(evaluate)>0){
                //发表成功,帖子评论数加一
                Forumpost forumpost = forumpostService.getById(evaluate.getForumFk());
                forumpost.setCommentCount(forumpost.getCommentCount()+1);
                forumpostService.update(forumpost);

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
        try{
            if (evaluateService.remove(id)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }

    }



}
