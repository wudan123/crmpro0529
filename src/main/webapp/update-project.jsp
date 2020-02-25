 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>项目管理系统 - 基础表格</title>
    <meta name="keywords" content="项目管理系统">
    <meta name="description" content="项目管理系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
      <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>项目管理<small>>修改项目信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveFrom" class="form-horizontal">
                            <input type="hidden" name="pid" value="${project.pid}">
                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">项目名称</label>
                                <div class="col-sm-3">
                                    <input name="name" value="${project.name}" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">客户公司名称</label>
                                <div class="col-sm-3">
                                    <select name="companyname"  id="companyname" class="selectpicker form-control">

									  <option>---请选择客户方公司---</option>

									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">客户方负责人</label>
                                <div class="col-sm-3">
                                    <input name="companyperson"   readonly id="textPerson" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">项目经理</label>
                                <div class="col-sm-3">
                                    <select name="empFk"  id="employee" class="selectpicker form-control">

									  <option>---请选择项目经理---</option>

									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">开发人数</label>
                                <div class="col-sm-3">
                                    <input name="empcount" value="${project.empcount}" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">开始时间</label>
                                <div class="col-sm-3">
                                		<!--时间控件的id都不能改-->
                                    <input name="starttime" value="<fmt:formatDate value="${project.starttime}" pattern="yyyy-MM-dd"></fmt:formatDate>" id="start" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">立项时间</label>
                                <div class="col-sm-3">
                                    <input name="buildtime" value="<fmt:formatDate value="${project.buildtime}" pattern="yyyy-MM-dd"></fmt:formatDate>"  id="buid" class="laydate-icon form-control layer-date">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">预估成本</label>
                                <div class="col-sm-3">
                                    <input name="cost" value="${project.cost}" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">级别</label>
                                <div class="col-sm-3">
                                    <select name="level" class="selectpicker form-control">

										<option  <c:if test="${project.level==1}">selected</c:if> value="1">高</option>
										<option  <c:if test="${project.level==2}">selected</c:if> value="2">中</option>
										<option  <c:if test="${project.level==3}">selected</c:if> value="3">低</option>
									</select>
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">计划完成时间</label>
                                <div class="col-sm-3">
                                    <input name="endtime" value="<fmt:formatDate value="${project.endtime}" pattern="yyyy-MM-dd"></fmt:formatDate>" id="finish" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark" class="form-control">${project.remark}</textarea>
                                </div>
                                
                            </div>
                        </div>
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button type="button" class="btn btn-primary btnSave"><i class="fa fa-save"></i> 保存内容</button>
                                </div>
                                <div class="col-sm-3">
                                	<a href="/project/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
                                	</div>
                            </div>
                       </div>
                       </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

 
    
    
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');
		//初始化日期控件
		laydate({elem: "#finish"});
		laydate({elem: "#buid"});
		laydate({elem: "#start"});


		var cp="${project.companyperson}";
		var companyId="${project.companyname}";
        // 加载客户信息
        loadCustomer();

		function loadCustomer() {
		    $.post("/customer/getJSON","",function (result) {
                $(result).each(function () {
                    var option="";
                    if(this.id==companyId){

                       option ="<option selected data-person='"+this.companyperson+"' value='"+this.id+"'>"+this.comname+"</option>"

                        $("#textPerson").val(this.companyperson);
                    }else {
                        option ="<option  data-person='"+this.companyperson+"' value='"+this.id+"'>"+this.comname+"</option>"

                    }
                    $("#companyname").append(option).selectpicker('refresh');
                });
                //自动选择value为comname的option
                $("#companyname").selectpicker('val',companyId);


            },"json");
        }

        //关联客户负责人
        $("#companyname").change(function () {
            var thisval=$(this).val();
           // alert(thisval);
           $(this).children().each(function () {//获取select下的子项option并遍历
               if ($(this).prop("selected")){
                  var person=$(this).data("person");//获取option存的数据this.companyperson

                  $("#textPerson").val(person);
                //  $("#textPerson").selectpicker('val',comname);
               }
           });
        });


		var employeeId="${project.empFk}";
        // 加载项目经理
            loadEmployee();
        function loadEmployee() {

            $.post("/employee/getByPfk","p_fk=4",function (result) {

                $(result).each(function () {
                    var option="";

                    if (this.eid==employeeId){

                         option="<option selected value='"+this.eid+"'>"+this.ename+"</option>"
                    }else {
                        //  var option="<option value='"+this.eid+"'>"+this.ename+"</option>"
                         option="<option value='"+this.eid+"'>"+this.ename+"</option>"
                    }
                    $("#employee").append(option).selectpicker('refresh');

                });

            },"json");

        }

        $(".btnSave").click(function () {
            var data=$("#saveFrom").serialize();

            $.post("/project/update",data,function (result) {

                if (result=="true"){
                    swal({
                        title:"修改成功",
                        text:"修改成功,返回列表页",
                        type:"success"


                    },function () {
                        location.href="/project/list";
                    })
                }else {
                    swal("修改失败","修改失败!","error");
                }

            },"text");

        });


//      选择客户公司名称时自动选对应的客户方负责人
        $("#company").change(function(){
            var thisVal=$(this).val();
            //val指value属性,选中value属性＝thisVal的选项
            $('#text').selectpicker('val',thisVal);
        });

		



		
	});
   </script>
   <!-- 修复日期控件长度-->
   <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet">
</body>


</html>
