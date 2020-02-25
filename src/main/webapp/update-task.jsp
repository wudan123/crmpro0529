<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <h5>任务管理<small>>修改任务</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveFrom" class="form-horizontal">
                            <input type="hidden" name="id" value="${task.id}">
                       	
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">参考位置</label>
                                <div class="col-sm-3">
                                    <select id="projectName" class="selectpicker form-control">
										<option value="0">---选择项目---</option>

									</select>
                                </div>
                                <div class="col-sm-2">
                                    <input readonly id="analysisName" type="text" class="form-control input-sm">
                                    <input type="hidden" name="analysisFk" id="analysisId" type="text" class="form-control input-sm">
                                </div>
                                <div class="col-sm-2">
                                    <select  id="getModule" class="selectpicker form-control">
                                        <option value="0">---选择模块---</option>

                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <select name="funFk" id="selectFunction" class="selectpicker form-control">
                                        <option value="0">---选择功能---</option>

                                    </select>
                                </div>
                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">任务标题</label>
                                <div class="col-sm-3">
                                		<input name="tasktitle" value="${task.tasktitle}" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">执行者</label>
                                <div class="col-sm-3">
                                    <select name="empFk2" id="selectExcutor" class="selectpicker form-control">
                                        <option value="0">---选择执行者---</option>

									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">开始时间</label>
                                <div class="col-sm-3">
                                		<input name="starttime" value="<fmt:formatDate value="${task.starttime}" pattern="yyyy-MM-dd"/>" id="start" class="laydate-icon form-control layer-date">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">结束时间</label>
                                <div class="col-sm-3">
                                    <input name="endtime" value="<fmt:formatDate value="${task.endtime}" pattern="yyyy-MM-dd"/>" id="end" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">优先级</label>
                                <div class="col-sm-3">
                                		<select name="level" class="selectpicker form-control">
										<option  <c:if test="${task.level=='高'}">selected</c:if> value="高">高</option>
										<option  <c:if test="${task.level=='中'}">selected</c:if> value="中">中</option>
										<option  <c:if test="${task.level=='低'}">selected</c:if> value="低">低</option>
										<option  <c:if test="${task.level=='暂缓'}">selected</c:if> value="暂缓">暂缓</option>
									</select>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark"  class="form-control">${task.remark}</textarea>
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
		laydate({elem: "#end"});
		laydate({elem: "#start"});

        // 加载项目
        var oldProject="${function.module.projectname}";
        loadProject();
        function loadProject() {
            $.getJSON("/project/analysisJSON","",function (result) {
                $(result).each(function () {
                    if (oldProject==this.name){
                        var analysisid=this.pid;
                        getAnalysisById(analysisid);
                    }
                    var option="<option data-projectpid='"+this.pid+"' value='"+this.name+"'>"+this.name+"</option>";
                    $("#projectName").append(option).selectpicker('refresh');
                });
                //自动选择value为oldprojectName的option
                $("#projectName").selectpicker('val',oldProject);

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

        //根据需求id查模块
        var oldModule="${function.module.modulename}";
        var moduleId="${function.moduleFk}";
        function getModule(id) {
            $.post("/module/listByAnalysisFk","analysis_fk="+id,function (result) {
                if (result.length==0){
                    $("#analysisName").val("");
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
                        if (this.id==moduleId){

                           loadFunction(moduleId);
                        }
                        var option="<option data-moduleid='"+this.id+"' value='"+this.id+"'>"+this.modulename+"</option>";
                        $("#getModule").append(option).selectpicker('refresh');

                    });
                    $("#getModule").selectpicker('val',moduleId);
                }

            },"json");

        }
      //改变模块时,功能随之改变
        $("#getModule").change(function () {
        //选中时赋给
            var moduleId=$(this).val();
            loadFunction(moduleId);

        });

        //根据模块查功能
        var functionId="${function.id}";
        function loadFunction(moduleId) {
            $.post("/function/moduleByFk","module_fk="+moduleId,function (result) {
                if(result.length==0){//说明该模块还没有添加功能
                //    $("#selectFunction").html("<option value='0'>---选择功能---</option>");
                    swal({
                        title: "查询失败",
                        text: "该模块还没有添加功能！是否前往添加功能",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#1ab394",
                        confirmButtonText: "yes",
                        closeOnConfirm: false
                    }, function () {//此函数是点击删除执行的函数
                        location.href="/save-function.jsp";
                    });
                }else{//有功能
                    $("#selectFunction").html("<option value='0'>---选择功能---</option>");
                    $(result).each(function () {

                        if (this.id==moduleId){
                            var option="<option selected value='"+this.id+"'>"+this.functionname+"</option>";

                        }else {
                            var option="<option  value='"+this.id+"'>"+this.functionname+"</option>";

                        }
                        $("#selectFunction").append(option).selectpicker('refresh');
                    });
                    $("#selectFunction").selectpicker('val',functionId);
                }
            },"json");
        }

        //加载执行者
        loadExcutor();
        function loadExcutor(){
            var ids=[1,2,3];//执行者id
            var msg=["初级程序员","中级程序员","高级程序员"];
            for (var i=0;i<ids.length;i++){
                getExcutor(ids[i],msg[i]);
            }

        }

        var emp_fk="${task.empFk2}";
        function getExcutor(id,msg){
            $.post("/employee/getByPfk","p_fk="+id,function (result) {
               $(result).each(function () {

                   if (this.eid==emp_fk){

                       var option="<option selected value='"+this.eid+"'>"+msg+"——"+this.ename+"</option>";
                   }else {
                       var option="<option value='"+this.eid+"'>"+msg+"——"+this.ename+"</option>";
                   }

                  $("#selectExcutor").append(option).selectpicker('refresh');

               });
            });

        }

        $(".btnSave").click(function () {

            var data=$("#saveFrom").serialize();
            $.post("/task/update",data,function (result) {

                if (result=="true"){
                    swal({
                        title:"修改成功",
                        text:"修改成功",
                        type:"success"
                    },function () {
                        location.href="/task/list";
                    })

                }else {
                    swal("修改失败","修改失败!","error");
                }

            },"text");

        });
		

		

		

		

		
	});
   </script>
  <!-- 修复日期控件长度-->
   <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet">
</body>


</html>
