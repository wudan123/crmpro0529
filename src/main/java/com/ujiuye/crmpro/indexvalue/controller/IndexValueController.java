package com.ujiuye.crmpro.indexvalue.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.indexvalue.pojo.DataCollect;
import com.ujiuye.crmpro.indexvalue.pojo.IndexValue;
import com.ujiuye.crmpro.indexvalue.service.IndexValueService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/indexvalue")
public class IndexValueController {
    @Autowired
    private IndexValueService indexValueService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<IndexValue> list = indexValueService.list(type, keyword);

        PageInfo<IndexValue> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-indexvalue";

    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam("myfiles")MultipartFile[] myfiles, IndexValue indexValue, HttpSession session){
        try{
            Employee login =(Employee)session.getAttribute("LOGIN");
            indexValue.setEmpFk5(login.getEid());
            List<File> files = MyFileUtils.fileUpload(myfiles, null);
            if (files!=null && files.size()>0){
                indexValue.setInFile(files.get(0).getName());
            }
            if (indexValueService.save(indexValue)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,int id){
        IndexValue indexValue = indexValueService.getById(id);
        model.addAttribute("indexvalue",indexValue);

        return "update-indexvalue";
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam("myfiles")MultipartFile[] myfiles, IndexValue indexValue, HttpSession session){
        try{
            Employee login =(Employee)session.getAttribute("LOGIN");
            indexValue.setEmpFk5(login.getEid());
            List<File> files = MyFileUtils.fileUpload(myfiles, null);
            if (files!=null && files.size()>0){
                indexValue.setInFile(files.get(0).getName());
            }
            if (indexValueService.update(indexValue)>0){
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

        IndexValue indexValue = indexValueService.getById(id);

        model.addAttribute("indexValue",indexValue);

        return "show-indexvalue";

    }

    @RequestMapping("/down")
    public ResponseEntity<byte[]> down(@RequestParam("filename")String filename){

        return MyFileUtils.fileDown(filename, null);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Integer[] ids){
        List<Integer> list= Arrays.asList(ids);
        try {
            if (indexValueService.remove(list)>0){
                return "true";
            }
            return "false";

        }catch(Exception e){
            e.printStackTrace();
            return "false";

        }

    }
    @RequestMapping("downExcel")
    public ResponseEntity<byte[]> downExcel(){
        List<IndexValue> list = indexValueService.list(0, "");

        //创建工作簿
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建表
        XSSFSheet sheet = workbook.createSheet("指标信息");
        //创建行
        XSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("编号");
        headRow.createCell(1).setCellValue("目标公司");
        headRow.createCell(2).setCellValue("目标营业额");
        headRow.createCell(3).setCellValue("指标指定人");
        headRow.createCell(4).setCellValue("指标开始时间");
        headRow.createCell(5).setCellValue("指标截止时间");

        for (int i=0;i<list.size();i++){
            IndexValue indexValue = list.get(i);
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(indexValue.getInId());
            row.createCell(1).setCellValue(indexValue.getDataCollect().getDacname());
            row.createCell(2).setCellValue(indexValue.getInTurnover());
            row.createCell(3).setCellValue(indexValue.getEmployee().getEname());
            row.createCell(4).setCellValue(indexValue.getInStarttime());
            row.createCell(5).setCellValue(indexValue.getInEndtime());

        }

        String path="C:\\Users\\WuDan\\Desktop\\";
        File file=new File(path+"指标信息表.xlsx");
        try {
            workbook.write(new FileOutputStream(file));


        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = file.getName();
        ResponseEntity<byte[]> responseEntity = MyFileUtils.fileDown(name, path);

        return responseEntity;

    }

    @RequestMapping("/attachmentDown")
    public ResponseEntity<byte[]> attachmentDown(int id){
        IndexValue indexValue = indexValueService.getById(id);
        String file = indexValue.getInFile();
        return attachment(file);

    }

    public ResponseEntity<byte[]> attachment(String filename){

        return MyFileUtils.fileDown(filename,null);
    }






}
