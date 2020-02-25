<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                        <h5>需求管理<small>>修改需求信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveFrom" class="form-horizontal">
                            <input type="hidden" name="id" value="${function.id}">
                       	
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">选择项目</label>
                                <div class="col-sm-3">
                                    <select name="proFk" id="projectName" class="selectpicker form-control">
										<option>---选择项目---</option>

									</select>
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">选择需求</label>
                                <div class="col-sm-3">
                                    <input readonly id="analysisName" type="text" class="form-control input-sm">
                                    <input type="hidden" value="${function.analysisFk}" name="analysisFk" id="analysisId" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">选择模块</label>
                                <div class="col-sm-3">
                                		<select name="moduleFk" id="getModule" class="selectpicker form-control">
										<option value="0">---选择模块---</option>

									</select>
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">优先级</label>
                                <div class="col-sm-3">
                                    <select name="level" class="selectpicker form-control">
										<option <c:if test="${function.level=='高'}">selected</c:if> value="高">高</option>
										<option <c:if test="${function.level=='中'}">selected</c:if> value="中">中</option>
										<option <c:if test="${function.level=='低'}">selected</c:if> value="低">低</option>
									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">功能名称</label>
                                <div class="col-sm-3">
                                		<input name="functionname" value="${function.functionname}" type="text" class="form-control input-sm">
                                </div>
                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">简单描述</label>
                                <div class="col-sm-9">
                                    <textarea name="simpledis" class="form-control">${function.simpledis}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">详细描述</label>
                                <div class="col-sm-9">
                                    <textarea name="detaileddis" class="form-control">${function.detaileddis}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark" class="form-control">${function.remark}</textarea>
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
                                	<a href="/function/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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
		

		var profk="${function.proFk}";
		// 加载项目
        loadProject();
        function loadProject() {
            $.getJSON("/project/analysisJSON","",function (result) {
               $(result).each(function () {
                   if (profk==this.pid){

                       var analysisId=this.pid;
                       getAnalysisById(analysisId);
                   }
                       var option="<option  data-projectpid='"+this.pid+"' value='"+this.pid+"'>"+this.name+"</option>";
                       $("#projectName").append(option).selectpicker('refresh');

               });
                $("#projectName").selectpicker('val',profk);
            });
            
        }

        //选择项目时把pid赋给需求文本框
        $("#projectName").change(function () {

            var analysisid;
            $(this).children().each(function () {
               if ($(this).prop("selected")){
                   analysisid=$(this).data("projectpid");
                   $("#analysisId").val(analysisid);
               }
            });

            getAnalysisById(analysisid);
        });

        //根据需求id查询相应需求
        function getAnalysisById(id) {

            $.post("/analysis/getById","id="+id,function (result) {
               if (result==null){
                   $("#analysisName").val("");
                   swal({
                       title: "该项目还未添加需求",
                       text: "确定前往添加需求页面",
                       type: "warning",
                       showCancelButton: true,
                       confirmButtonColor: "rgba(120,59,56,0.32)",
                       confirmButtonText: "yes",
                       closeOnConfirm: false
                   }, function () {//此函数是点击删除执行的函数
                       location.href="/save-analysis.jsp";
                   },"json");
               }else {
                   //不为空则把需求名称赋给需求文本框,一个用于显示需求名称，一个隐藏框用于传id

                   $("#analysisName").val(result.title);
                   getModule(id);
               }

            });
            
        }

        var moduleid="${function.moduleFk}";
        //根据需求id查模块
        function getModule(id) {
            $.post("/module/listByAnalysisFk","analysis_fk="+id,function (result) {
                if (result.length==0){
                    $("#getModule").val("");
                    swal({
                        title: "该需求还未添加模块",
                        text: "确定前往添加模块页面",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "rgba(120,59,56,0.32)",
                        confirmButtonText: "yes",
                        closeOnConfirm: false
                    }, function () {//此函数是点击删除执行的函数
                        location.href="/save-module.jsp";
                    });
                }else {
                    $("#getModule").html("<option value='0'>---请选择模块---</option>");
                    $(result).each(function () {
                        if (this.id==moduleid){

                            var option="<option selected  value='"+this.id+"'>"+this.modulename+"</option>";

                        }else {
                            var option="<option  value='"+this.id+"'>"+this.modulename+"</option>";
                        }
                        $("#getModule").append(option).selectpicker('refresh');
                    });

                }

            },"json");

        }

        $(".btnSave").click(function () {

            var data=$("#saveFrom").serialize();
            $.post("/function/update",data,function (result) {

                if (result=="true"){
                    swal({
                        title:"修改成功",
                        text:"修改成功,返回列表页",
                        type:"success"

                    },function () {
                        location.href="/function/list";
                    })
                }else {
                    swal("修改失败","修改失败!","error");
                }

            },"text");

        });

		
		
		

		
	});
   </script>
   
</body>


</html>
