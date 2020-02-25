package com.ujiuye.crmpro.account.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.account.pojo.Baoxiao;
import com.ujiuye.crmpro.account.service.BaoxiaoService;
import com.ujiuye.crmpro.common.utils.UUIDUtils;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.project.pojo.Analysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/baoxiao")
public class BaoxiaoController {
    @Autowired
    private BaoxiaoService baoxiaoService;


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize,
                       @RequestParam(value = "status",required = false,defaultValue = "4")int status,
                       HttpSession session){

        PageHelper.startPage(pageNum,pageSize);
        Employee login =(Employee) session.getAttribute("LOGIN");


        //查询我的报销
        if (status==4){
            List<Baoxiao> list = baoxiaoService.list(type, keyword,status,login.getEid());
        }

        List<Baoxiao> list = baoxiaoService.list(type, keyword,status,0);


        PageInfo<Baoxiao> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        //返回的页面
        if (status==1){//待审批
            return "list-approve";
        }else if (status==2){//待付款
            return "list-pay";
        }else if (status==3){//已付款
            return "list-payed";
        }else {//我的报销
            return "list-myaccount";
        }


    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(Baoxiao baoxiao,HttpSession session){
        try{
            String uuid = UUIDUtils.getUUID();
            Employee login =(Employee) session.getAttribute("LOGIN");
            baoxiao.setBxid(uuid);
            baoxiao.setEmpFk(login.getEid());
            if ( baoxiaoService.save(baoxiao)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/show")
    public String show(Model model,String bxid){

        Baoxiao baoxiao = baoxiaoService.getById(bxid);

        model.addAttribute("baoxiao",baoxiao);

        return "show-myaccount";


    }


    //跳转申请报销页面
    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,String bxid){

        Baoxiao baoxiao = baoxiaoService.getById(bxid);

        model.addAttribute("baoxiao",baoxiao);

        return "update-myaccount";


    }
    @RequestMapping("/toApprove")
    public String toApprove(Model model,String bxid){

        Baoxiao baoxiao = baoxiaoService.getById(bxid);

        model.addAttribute("baoxiao",baoxiao);

        return "update-approve";


    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Baoxiao baoxiao,HttpSession session){
        try{
            Employee login =(Employee) session.getAttribute("LOGIN");
            if (login.getpFk()>=4){//项目经理和项目总监才有权限审批
                if (baoxiaoService.update(baoxiao)>0){
                    return "true";
                }
            }else {
                return "no";
            }

            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/pay")
    @ResponseBody
    public String pay(String id){
        try{
            Baoxiao baoxiao = baoxiaoService.getById(id);
            baoxiao.setBxstatus(3);
            if (baoxiaoService.update(baoxiao)>0){
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
    public String remove(String id){
        try{
            if (baoxiaoService.remove(id)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    //查询我的报销
    @RequestMapping("/listByEmpFk")
    public String listByEmpFk(HttpSession session,Model model,
                              @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize,
                              @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum){
        PageHelper.startPage(pageNum,pageSize);
        Employee login =(Employee) session.getAttribute("LOGIN");
        List<Baoxiao> baoxiaos = baoxiaoService.listByEid(login.getEid());
        PageInfo<Baoxiao> page=new PageInfo<>(baoxiaos);

        model.addAttribute("baoxiaos",baoxiaos);
        model.addAttribute("page",page);
        return "list-mybaoxiao";
    }


}
