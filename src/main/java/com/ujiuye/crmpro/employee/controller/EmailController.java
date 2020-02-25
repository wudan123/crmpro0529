package com.ujiuye.crmpro.employee.controller;

import com.ujiuye.crmpro.common.utils.EmailScheduler;
import com.ujiuye.crmpro.common.utils.EmailUtils;
import com.ujiuye.crmpro.employee.pojo.Archives;
import com.ujiuye.crmpro.employee.pojo.Email;
import com.ujiuye.crmpro.employee.service.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private ArchivesService archivesService;


    @RequestMapping("/send")
    @ResponseBody
    public String send(Email email){

        Archives archives = archivesService.getByEmpFk(email.getEid());
        if (archives==null){
            return "noemail";
        }
        if (archives.getEmail()==null ||"".equals(archives.getEmail())){
            return "noemail";
        }
        //不为空就把邮箱赋给收件人
        email.setReceiver(archives.getEmail());

        try {
            //如果勾选了定时发送
            if (email.getIsTime().equals("on")){
                EmailScheduler.send(email);
                return "true";
            }

            EmailUtils.send(email);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";

        }

    }
}
