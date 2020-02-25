package com.ujiuye.crmpro.common.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        SimpleDateFormat simpleDateFormat =null;

        if(s.contains("/")){
            simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        }else{
            simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            // 转换失败返回null
            return null;
        }




    }
}
