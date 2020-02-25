package com.ujiuye.crmpro.customer.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.customer.service.CustomerService;
import com.ujiuye.crmpro.employee.pojo.Employee;
import org.apache.ibatis.annotations.Param;
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
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "type",required = false,defaultValue = "0")int type,
                       @RequestParam(value = "keyword",required = false,defaultValue = "")String keyword,
                       @RequestParam(value = "pageNum",required=false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "pageSize",required = false,defaultValue = "3")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<Customer> list = customerService.list(type, keyword);

        PageInfo<Customer> page=new PageInfo<>(list);
        model.addAttribute("page",page);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "list-customer";

    }

    @RequestMapping("/getJSON")
    @ResponseBody
    public String getJSON(){
        List<Customer> list = customerService.list(0, "");

        return JSON.toJSONString(list);
    }

    @RequestMapping("/show")
    public String show(Model model,int id,int pageNum,int type,String keyword){

        Customer customer = customerService.show(id);
        model.addAttribute("customer",customer);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "show-customer";


    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Customer customer){

        try {
            if(customerService.save(customer)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            return "false";
        }

    }

    @RequestMapping("/toUpdate")
    public String toUpate(Model model,int id){

        Customer customer = customerService.show(id);
        model.addAttribute("customer",customer);

        return "update-customer";


    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Customer customer){

        try {
            if(customerService.update(customer)>0){
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
        // 将数组转换为集合 Arrays
        List<Integer> list= Arrays.asList(ids);
        try{
            if(customerService.remove(list)>0){
                return "true";
            }else {
                return "false";
            }

        }catch (Exception e){
            return "false";
        }

    }


    @RequestMapping("/downExcel")
    public ResponseEntity<byte[]> downExcel(){
        List<Customer> list = customerService.list(0, "");
        File file = toExcel(list);
        String name = file.getName();
        String path="C:\\Users\\WuDan\\Desktop\\";

        return MyFileUtils.fileDown(name, path);

    }

    //将数据写到excel
    private File toExcel(List<Customer> list){

        //创建工作簿
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建表
        XSSFSheet sheet = workbook.createSheet("客户信息");
        XSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("序号");
        headRow.createCell(1).setCellValue("公司名称");
        headRow.createCell(2).setCellValue("公司联系人");
        headRow.createCell(3).setCellValue("联系电话");
        headRow.createCell(4).setCellValue("座机");
        headRow.createCell(5).setCellValue("地址");
        headRow.createCell(6).setCellValue("简介");
        headRow.createCell(7).setCellValue("备注");

        for(int i=0;i<list.size();i++){
            Customer customer = list.get(i);
            XSSFRow row = sheet.createRow(1 + i);
            row.createCell(0).setCellValue(customer.getId());
            row.createCell(1).setCellValue(customer.getComname());
            row.createCell(2).setCellValue(customer.getCompanyperson());
            row.createCell(3).setCellValue(customer.getComphone());
            row.createCell(4).setCellValue(customer.getCamera());
            row.createCell(5).setCellValue(customer.getComaddress());
            row.createCell(6).setCellValue(customer.getPresent());
            row.createCell(7).setCellValue(customer.getRemark());
        }
        //写入的路径
        String path="C:\\Users\\WuDan\\Desktop\\";
        //创建文件名
       File file=new File(path+"客户信息.xlsx");

        try {
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;

    }









}
