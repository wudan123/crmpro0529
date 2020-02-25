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
                        <h5>对标管理<small>>修改对标基本信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveFrom" class="form-horizontal">
                            <input type="hidden" name="daid" value="${dataCollect.daid}">
                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">公司名称</label>
                                <div class="col-sm-3">
                                    <input name="dacname" value="${dataCollect.dacname}" type="text" class="form-control input-sm required" >
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">营业额</label>
                                <div class="col-sm-3">
                                    <input name="daturnover" value="${dataCollect.daturnover}" type="text" class="form-control input-sm required" >
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年份</label>
                                <div class="col-sm-3">
                                    <input name="datime" value='<fmt:formatDate value="${dataCollect.datime}" pattern="yyyy-MM-dd"/>'  type="text" class="form-control input-sm required" >
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">员工数量</label>
                                <div class="col-sm-3">
                                    <input name="empcount" value="${dataCollect.empcount}" type="text" class="form-control input-sm required" >
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">行业地位</label>
                                <div class="col-sm-3">
                                    <input name="dasort" value="${dataCollect.dasort}" type="text" class="form-control input-sm required" >
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">创建时间</label>
                                <div class="col-sm-3">
                                    <input name="buildtime" value='<fmt:formatDate value="${dataCollect.buildtime}" pattern="yyyy-MM-dd"/>'    id="finish" class="laydate-icon form-control layer-date required" >
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">主要业务</label>
                                <div class="col-sm-9">
                                    <textarea name="dabusiness" class="form-control">${dataCollect.dabusiness}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">优势</label>
                                <div class="col-sm-9">
                                    <textarea name="dasuperiority" class="form-control">${dataCollect.dasuperiority}</textarea>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">劣势</label>
                                <div class="col-sm-9">
                                    <textarea name="dainforiority" class="form-control">${dataCollect.dainforiority}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">简单描述</label>
                                <div class="col-sm-9">
                                    <textarea name="remark" class="form-control">${dataCollect.remark}</textarea>
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
                                    <a href="/datacollect/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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
		//laydate({elem: "#buid"});
		//laydate({elem: "#start"});


        $(".btnSave").click(function () {

            var data=$("#saveFrom").serialize() //序列化表单
            $.post("/datacollect/update",data,function (result) {

                if (result=="true"){
                    //清空表单
                    $("#saveFrom")[0].reset();
                    swal({
                        title:"修改成功",
                        text:"",
                        type:"success"

                    },function () {
                        location.href="/datacollect/list";
                    })

                }else {
                    swal("修改失败","修改失败","error");

                }
            },"text");
        });

		
		
		

		
	});
   </script>
   <!-- 修复日期控件长度-->
   <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet">
</body>


</html>
