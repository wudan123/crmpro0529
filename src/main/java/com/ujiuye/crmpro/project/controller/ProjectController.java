package com.ujiuye.crmpro.project.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.project.pojo.Project;
import com.ujiuye.crmpro.project.service.AnalysisService;
import com.ujiuye.crmpro.project.service.AttachmentService;
import com.ujiuye.crmpro.project.service.FunctionService;
import com.ujiuye.crmpro.project.service.ProjectService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private FunctionService functionService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Project> list = projectService.list(type, keyword);

        PageInfo<Project> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-project";

    }

    @RequestMapping("/analysisJSON")
    @ResponseBody
    public String analysisJSON(){
        List<Project> list = projectService.list(0, "");

        return JSON.toJSONString(list);

    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(Project project){

        try {
            if (projectService.save(project)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            return "false";
        }

    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Integer[] ids){

        // 先创建两个集合 用于存储管有关联project表数据的id和没有管理project表数据的id，也就是能被删除和不能被删除
        List<Integer> remove=new ArrayList<>();
        List<Integer> notRemove=new ArrayList<>();
        // 将数组转换为集合 Arrays
        List<Integer> list= Arrays.asList(ids);

        String msg="";//用于以字符串形式返回id
        for (int i=0;i<list.size();i++) {
            int result1 = analysisService.countByPid(list.get(i));
            int result2 = attachmentService.countByPid(list.get(i));
            int result3 = functionService.countByPid(list.get(i));

            if (result1>0 || result2>0 || result3>0){
                notRemove.add(list.get(i));
            }else {
                remove.add(list.get(i));
            }
        }
        if (remove.size()==0){//证明所有被选中的id都有关联project表的数据
            msg=notRemove.toString();
            return msg;
        }

        try{
            if(projectService.remove(remove)>0){
                //判断选中的多项有没有不可以被删除的
                if (notRemove.size()>0){
                    msg=notRemove.toString();
                    return msg;
                }

                return "true";
            }

            return "false";

        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/show")
    public String show(Model model,int pid){

        Project project = projectService.getById(pid);
        model.addAttribute("project",project);

        return "show-project";

    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,int pid){
        Project project = projectService.getById(pid);

        model.addAttribute("project",project);

        return "update-project";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Project project){
        try {
            if (projectService.update(project)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            return "false";
        }

    }

    @RequestMapping("/downExcel")
    public  ResponseEntity<byte[]> downExcel(){

        List<Project> list = projectService.list(0, "");
        File file = toExcel(list);
        String path="C:\\Users\\WuDan\\Desktop\\";
        String name=file.getName();

        return MyFileUtils.fileDown(name, path);

    }


    //将数据写到excel
    private File toExcel(List<Project> list){

        //创建工作簿
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建表
        XSSFSheet sheet = workbook.createSheet("项目信息");
        XSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("项目名称");
        headRow.createCell(1).setCellValue("客户公司名称");
        headRow.createCell(2).setCellValue("客户方负责人");
        headRow.createCell(3).setCellValue("项目经理");
        headRow.createCell(4).setCellValue("开发人员数");
        headRow.createCell(5).setCellValue("立项时间");
        headRow.createCell(6).setCellValue("成本");
        headRow.createCell(7).setCellValue("等级");
        headRow.createCell(8).setCellValue("完成时间");
        headRow.createCell(9).setCellValue("备注");

        for(int i=0;i<list.size();i++){
            Project project = list.get(i);
            XSSFRow row = sheet.createRow(1 + i);
            row.createCell(0).setCellValue(project.getName());
            row.createCell(1).setCellValue(project.getCustomer().getComname());
            row.createCell(2).setCellValue(project.getCompanyperson());
            row.createCell(3).setCellValue(project.getEmployee().getEname());
            row.createCell(4).setCellValue(project.getEmpcount());
            row.createCell(5).setCellValue(project.getBuildtime());
            row.createCell(6).setCellValue(project.getCost());
            row.createCell(7).setCellValue(project.getLevel());
            row.createCell(8).setCellValue(project.getEndtime());
            row.createCell(9).setCellValue(project.getRemark());
        }
        //写入的路径
        String path="C:\\Users\\WuDan\\Desktop\\";
        //创建文件名
        File file=new File(path+"项目基本信息.xlsx");

        try {
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;

    }
}
