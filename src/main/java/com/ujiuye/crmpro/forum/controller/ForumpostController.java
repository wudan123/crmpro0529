package com.ujiuye.crmpro.forum.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.dept.pojo.Dept;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.forum.pojo.Evaluate;
import com.ujiuye.crmpro.forum.pojo.Forumpost;
import com.ujiuye.crmpro.forum.service.EvaluateService;
import com.ujiuye.crmpro.forum.service.ForumpostService;
import com.ujiuye.crmpro.forum.service.ForumsortService;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/forumpost")
public class ForumpostController {
    @Autowired
    private ForumpostService forumpostService;
    @Autowired
    private ForumsortService forumsortService;
    @Autowired
    private EvaluateService evaluateService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "view",required = false,defaultValue = "0")int view,
                       int forumsort_fk){
        if (view==1){
            forumsortService.updateClick(forumsort_fk);
        }

        PageHelper.startPage(pageNum,6);
        List<Forumpost> list = forumpostService.list(forumsort_fk);

        PageInfo<Forumpost> page=new PageInfo<>(list);

        model.addAttribute("list",list);
        model.addAttribute("page",page);
        return "list-forum";
    }

    @RequestMapping("/show")
    public String show(Model model,int id,
                       @RequestParam(value = "pageNum",required = false,defaultValue = "0")int pageNum,
                       @RequestParam(value = "view",required = false,defaultValue = "0")int view){
        Forumpost forumpost = forumpostService.getById(id);
        //每次查看增加点击量
        if (view==1){
            forumpost.setClick(forumpost.getClick()+1);
            forumpostService.update(forumpost);
        }


        PageHelper.startPage(pageNum,5);
        //评论
        List<Evaluate> evaluates = evaluateService.list(forumpost.getForumid());

        PageInfo<Evaluate> page=new PageInfo<>(evaluates);

        model.addAttribute("forumpost",forumpost);
        model.addAttribute("evaluates",evaluates);
        model.addAttribute("page",page);


        return "show-forum";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Forumpost forumpost, HttpSession session){
        Employee login =(Employee) session.getAttribute("LOGIN");
        forumpost.setEmpFk3(login.getEid());

        try {
            if (forumpostService.save(forumpost)>0){
                //添加成功后模块帖子数加一
                forumsortService.updateCount(forumpost.getForumsortFk());

                return "true";

            }
            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";

        }

    }


    @RequestMapping("/listByEmpFk")
    public String listByEmpFk(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                              HttpSession session){


        Employee login =(Employee) session.getAttribute("LOGIN");
        PageHelper.startPage(pageNum,6);
        List<Forumpost> list = forumpostService.getByEmpFk(login.getEid());

        PageInfo<Forumpost> page=new PageInfo<>(list);

        model.addAttribute("list",list);
        model.addAttribute("page",page);
        return "list-myforum";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Integer[] ids){
        List<Integer> list= Arrays.asList(ids);
        try {
            if (forumpostService.remove(list)>0){
                return "true";
            }
            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";

        }

    }




}
