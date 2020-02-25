package com.ujiuye.crmpro.employee.service;

import com.ujiuye.crmpro.employee.pojo.Archives;

import java.util.List;

public interface ArchivesService {

   Archives getByEmpFk(int emp_fk);

   int saveTable(List<Archives> archives);//录入档案

   List<Archives> list(int type,String keyword);
}
