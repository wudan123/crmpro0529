package com.ujiuye.crmpro.common.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyFileUtils {

    public static List<File> fileUpload(MultipartFile[] multipartFile, String path){

        List<File> list=new ArrayList<>();

        if(path==null || path.equals("")){
            path="D:\\IDEA\\work\\upload\\";
        }

        if(multipartFile.length>0){
            for (MultipartFile files : multipartFile) {
                if (files.getSize()>0){

                    //获取文件名称
                    String filename = files.getOriginalFilename();

                    //防止图片覆盖
                    UUID uuid = UUID.randomUUID();
                 //   System.out.println(uuid);

                    //获取文件后缀名
                    String substring = filename.substring(filename.lastIndexOf("."));
                  //  System.out.println(substring);


                    String newFilename=uuid+substring;


                    String truePath=path+newFilename;

                    File file=new File(truePath);

                    try {
                        // 把files的内容复制到file里面
                        files.transferTo(file);
                        // 把file添加到list集合中 用于返回
                        list.add(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }else{
                    System.out.println("没有选择文件!");
                }

            }

        }else {

            System.out.println("没有选择文件!");
        }




        return list;



    }

    public static ResponseEntity<byte[]> fileDown(String filename, String path){

        if(path==null || path.equals("")){
            path="D:\\IDEA\\work\\upload\\";
        }

        try {
            //处理弹框中文乱码
            String newName=new String(filename.getBytes("UTF-8"),"ISO-8859-1");


            String truePath=path+filename;
            File file=new File(truePath);

            HttpHeaders headers=new HttpHeaders();

            //设置下载文件的名字
            headers.setContentDispositionFormData("attachment",newName);

            //设置MIME类型
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            ResponseEntity<byte[]> entry =
                    new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,
                            HttpStatus.OK);

            return entry;



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }
}
