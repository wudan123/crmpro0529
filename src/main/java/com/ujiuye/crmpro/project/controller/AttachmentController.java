package com.ujiuye.crmpro.project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.FileTypeUtils;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.project.pojo.Attachment;
import com.ujiuye.crmpro.project.pojo.Module;
import com.ujiuye.crmpro.project.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "17")int pageSize,
                       @RequestParam(value = "tag",required = false,defaultValue = "")String tag){

        PageHelper.startPage(pageNum,pageSize);

        List<Attachment> list = attachmentService.list(type,tag,keyword);

        PageInfo<Attachment> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("tag",tag);

        return "list-attachment";

    }

    @RequestMapping("/fileUp")
    @ResponseBody
    public String fileUp(Attachment attachment,@RequestParam("files")MultipartFile[] files){

        try{
            List<File> list = MyFileUtils.fileUpload(files, null);
            if (list!=null && list.size()>0){
                String name = list.get(0).getName();
                attachment.setPath(name);
                //添加文件类型
                int type = FileTypeUtils.getType(name);
                attachment.setType(type);
                //添加文件日期
                attachment.setAddtime(new Date());
            }else {
                return "nofile";
            }
            if (attachmentService.save(attachment)>0){
                return "true";
            }

            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/show")
    public String show(Model model,int id){

        Attachment attachment = attachmentService.getById(id);
        model.addAttribute("attachment",attachment);

        return "show-attachment";
    }

    @RequestMapping("/down")
    @ResponseBody
    public ResponseEntity<byte[]> down(@RequestParam("files")String files){

        ResponseEntity<byte[]> file = MyFileUtils.fileDown(files, null);

        return file;
    }
}
