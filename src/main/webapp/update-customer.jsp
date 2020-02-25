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
                        <h5>客户信息管理<small>>修改客户信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form  id="saveFrom" class="form-horizontal">
                            <input name="id" value="${customer.id}" type="hidden">
                       	
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">公司名称</label>
                                <div class="col-sm-3">
                                   <input name="comname" type="text" value="${customer.comname}" class="form-control input-sm required">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">公司联系人</label>
                                <div class="col-sm-3">
                                    <input name="companyperson" value="${customer.companyperson}" type="text" class="form-control input-sm required " >
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话</label>
                                <div class="col-sm-3">
                                   <input name="comphone" value="${customer.comphone}" type="text" class="form-control input-sm required">
                                </div>
                                <label class="col-sm-2  col-sm-offset-1 control-label ">座机</label>
                                <div class="col-sm-3">
                                    <input name="camera" value="${customer.camera}" type="text" class="form-control input-sm required">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">公司地址</label>
                                <div class="col-sm-9">
                                   <input name="comaddress" value="${customer.comaddress}" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">公司简介</label>
                                <div class="col-sm-9">
                                    <textarea name="present"  class="form-control">${customer.present}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark"  class="form-control">${customer.remark}</textarea>
                                </div>
                            </div>
                        </div>
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button  type="button" class="btn btn-primary btnSave"><i class="fa fa-save"></i>保存内容</button>
                                </div>
                                <div class="col-sm-3">
                                	<a href="/customer/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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
	
   <script>
	$(document).ready(function() {

	    $(".btnSave").click(function () {

	        if(!checkInput()){
	            return;
            }

            var data=$("#saveFrom").serialize() //序列化表单
            $.post("/customer/update",data,function (result) {

                if (result=="true"){

                    swal({
                       title:"修改成功",
                       text:"修改成功!",
                        type:"success"
                    },function () {
                        location.href="/customer/list"; //回调函数，点击确定时触发
                    })
               }else {
                    swal("修改失败","修改失败","error");

                }
            },"text");
        });

	    // 校验必填项非空
	    function checkInput() {
	        var isNull=true;
	        $(".required:input").each(function () {

	           var value=$(this).val();
	           if(value==""){
	               isNull=false;
               }

            });

	        return isNull;

        }




		




		


	});
   </script>
  
</body>


</html>
