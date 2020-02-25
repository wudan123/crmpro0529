package com.ujiuye.crmpro.message.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.message.pojo.Msg;
import com.ujiuye.crmpro.message.pojo.Notice;
import com.ujiuye.crmpro.message.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    private MsgService msgService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "status",required = false,defaultValue = "0")int status,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize,
                       HttpSession session){

        PageHelper.startPage(pageNum,pageSize);


        Employee login =(Employee) session.getAttribute("LOGIN");


        List<Msg> list = msgService.list(keyword,type,status,login.getEid());

        PageInfo<Msg> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("keyword",keyword);
        model.addAttribute("type",type);
        model.addAttribute("status",status);

        return "list-message";

    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam("files") MultipartFile[] files, Msg msg,HttpSession session){
        Employee login =(Employee) session.getAttribute("LOGIN");//发件人
        try {
            List<File> list = MyFileUtils.fileUpload(files, null);
            if (list!=null && list.size()>0){
                String name = list.get(0).getName();
                msg.setPath(name);
            }
            msg.setSend(login.getEid());
            if (msgService.save(msg)>0){
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
        Msg msg = msgService.getById(id);
        //跳转页面时修改status 未读1 已读0
        msg.setStatus(0);
        msgService.update(msg);
        model.addAttribute("msg",msg);

        return "show-message";
    }

    @RequestMapping("/down")
    public ResponseEntity<byte[]> down(@RequestParam("filename")String filename){

        return MyFileUtils.fileDown(filename, null);

    }

    @RequestMapping("/countByStatus")
    @ResponseBody
    public Integer countByStatus(int status,HttpSession session){

        Employee login = (Employee) session.getAttribute("LOGIN");

        int count = msgService.countByStatus(status,login.getEid());


        return count;

    }

    @RequestMapping("/tabRubbish")
    @ResponseBody
    public String tabRubbish(Integer[] ids){
        try {
            List<Integer> list= Arrays.asList(ids);
            List<Msg> msgs = msgService.getByIds(list);

           msgService.update(msgs);

           return "true";


        }catch (Exception e){
            e.printStackTrace();
            return "false";

        }



    }
}
