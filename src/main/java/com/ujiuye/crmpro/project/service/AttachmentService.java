package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Attachment;

import java.util.List;

public interface AttachmentService {

    int countByPid(int pid);//删除项目时统计所删项目是否带有附件

    List<Attachment> list(int type,String tag,String name);

    int save(Attachment attachment);//上传附件

    Attachment getById(int id);


}
