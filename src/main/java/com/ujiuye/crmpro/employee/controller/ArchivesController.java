package com.ujiuye.crmpro.employee.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.common.utils.UUIDUtils;
import com.ujiuye.crmpro.employee.pojo.Archives;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.ArchivesService;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/archives")
public class ArchivesController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ArchivesService archivesService;


    @RequestMapping(value = "saveArchives",produces = ("text/html;charset=utf-8"))
    @ResponseBody
    public String saveArchives(@RequestParam("files") MultipartFile[] files) {//录入档案

        List<File> list = MyFileUtils.fileUpload(files, null);

        List<String> names=new ArrayList<>();

        if (list==null || list.size()==0){
            return "nofile";
        }

        File file = list.get(0);

        try {
            List<Archives> archivesList = readExcel(file, names);
            if (archivesList!=null && archivesList.size()>0){
                if (archivesService.saveTable(archivesList)>0){
                    if (names.size()>0){
                        return names.toString();//部分不符合表中名单
                    }

                    return "true";//全部录入成功
                }

                return "false";

            }else{
                return "allnot";//全部不成功
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }


    }

    public List<Archives> readExcel(File file,List<String> list) throws IOException {
        List<Archives> archivesList=new ArrayList<>();

        FileInputStream fileInputStream=new FileInputStream(file);
        //创建工作簿
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
        //获取第一个表
        XSSFSheet sheet = workbook.getSheetAt(0);

        //表里的有数据的行数
        int totalRows = sheet.getPhysicalNumberOfRows();

        for (int i=1;i<totalRows;i++){
            Row row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            //判断员工表里有没有该名字
            List<Employee> employees = employeeService.getByName(name);
            if (employees==null || employees.size()==0){
                list.add(name); //将不符合的名字存入该集合中
                continue;//结束本次循环
            }
            String telephone = row.getCell(1).getStringCellValue();
            String school = row.getCell(2).getStringCellValue();
            String major = row.getCell(3).getStringCellValue();
            String contact = row.getCell(4).getStringCellValue();
            Date graduate = row.getCell(5).getDateCellValue();
            String politics = row.getCell(6).getStringCellValue();
            String nation = row.getCell(7).getStringCellValue();
            String education = row.getCell(8).getStringCellValue();
            String email = row.getCell(9).getStringCellValue();
            String remark = row.getCell(10).getStringCellValue();
            String no = UUIDUtils.getUUID();
            Employee employee = employees.get(0);
            Integer empFk = employee.getEid();
            Date hiredate = employee.getHiredate();

            Archives archives=new Archives(no,telephone,school, major, contact, graduate, politics, nation, education,  email,empFk, remark, hiredate);

            archivesList.add(archives);

        }

        return archivesList;


    }


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Archives> list = archivesService.list(type, keyword);

        PageInfo<Archives> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-archives";

    }





}
