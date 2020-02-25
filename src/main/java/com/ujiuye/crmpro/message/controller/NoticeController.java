package com.ujiuye.crmpro.message.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.message.pojo.Notice;
import com.ujiuye.crmpro.message.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "9")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Notice> list = noticeService.list(keyword);

        PageInfo<Notice> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("keyword",keyword);

        return "list-notice";

    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam("files") MultipartFile[] files,Notice notice){

        try{
            List<File> list = MyFileUtils.fileUpload(files, null);
            if (list!=null&&list.size()>0){
                String name = list.get(0).getName();
                notice.setPath(name);
            }
            if(noticeService.save(notice)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            return "false";
        }

    }

    @RequestMapping("/show")
    public String show(Model model,int id){
        Notice notice = noticeService.getById(id);
        model.addAttribute("notice",notice);

        return "show-notice";

    }


}
