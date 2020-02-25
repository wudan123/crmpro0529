<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <h5>需求管理<small>>添加需求信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="saveFrom" class="form-horizontal">
                            <input type="hidden" name="id" value="${module.id}">
                       	
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">选择项目</label>
                                <div class="col-sm-3">
                                    <select id="projectSelect" name="projectname" class="selectpicker form-control">
										<option>---选择项目---</option>

									</select>
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">选择需求</label>
                                <div class="col-sm-3">
                                    <input id="textanalysis" readonly type="text" class="form-control input-sm">
                                    <input type="hidden" name="analysisFk" id="analysisId">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">模块名称</label>
                                <div class="col-sm-3">
                                		<input name="modulename" value="${module.modulename}" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">优先级</label>
                                <div class="col-sm-3">
                                    <select name="level" class="selectpicker form-control">
                                        <option value="0">---请选择模块---</option>
										<option   <c:if test="${module.level=='高'}">selected</c:if>  value="高">高</option>
										<option   <c:if test="${module.level=='中'}">selected</c:if> value="中">中</option>
										<option   <c:if test="${module.level=='低'}">selected</c:if> value="低">低</option>
									</select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">简单描述</label>
                                <div class="col-sm-9">
                                    <textarea name="simpledis" class="form-control">${module.simpledis}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">详细描述</label>
                                <div class="col-sm-9">
                                    <textarea name="detaileddis" class="form-control">${module.detaileddis}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark" class="form-control">${module.remark}</textarea>
                                </div>
                            </div>
                        </div>
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button type="button" id="btn" class="btn btn-primary btnSave"><i class="fa fa-save"></i> 保存内容</button>
                                </div>
                                <div class="col-sm-3">
                                	<a href="/module/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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


		var oldprojectName="${module.projectname}";

        // 加载项目信息
        loadProject();
        function loadProject(){
            $.getJSON("/project/analysisJSON","",function (result) {
                $(result).each(function () {
                    if (this.name==oldprojectName){
                        var analysisid=this.pid;
                        checkAnalysis(analysisid);
                    }
                    var option="<option data-projectid='"+this.pid+"' value='"+this.name+"'>"+this.name+"</option>";
                    $("#projectSelect").append(option).selectpicker('refresh');
                });

                //自动选择value为oldprojectName的option
                $("#projectSelect").selectpicker('val',oldprojectName);

            });
        }
        //选择项目时给id赋值
        $("#projectSelect").change(function () {
            var analysisid;
            //获取id并把id赋给文本框
            $(this).children().each(function () {
                if($(this).prop("selected")){
                    analysisid=$(this).data("projectid");
                    $("#analysisId").val(analysisid);

                }
            });
            //根id查到对应的需求
            checkAnalysis(analysisid);
        });

        // 验证选择项目时有没有对应的需求
        function checkAnalysis(id) {
            $.post("/analysis/getById","id="+id,function (result) {
                $("#textanalysis").val("");//清空文本框
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
                   });
               }else {
                   $("#textanalysis").val(result.title);
               }
            },"json");

        }

       $(".btnSave").click(function () {

            var data=$("#saveFrom").serialize();
            $.post("/module/update",data,function (result) {

                if (result=="true"){
                    swal({
                        title:"修改成功",
                        text:"修改成功,返回列表页",
                        type:"success"


                    },function () {
                        location.href="/module/list";
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
