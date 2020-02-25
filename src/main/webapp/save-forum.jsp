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
    	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
   
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet">
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
                        <form id="saveForm" class="form-horizontal">
                       	
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-9">
                                    <input name="forumtitle" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">板块</label>
                                    <div class="col-sm-4">
                                        <select id="sort" name="forumsortFk" class="selectpicker form-control">
                                            <option value="0">---选择板块---</option>

                                        </select>
                                    </div>
                                </div>
                            </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容</label>
                                <div class="col-sm-9">
                                    <script id="editor"  type="text/plain"></script>
                                    <input type="hidden" id="content" name="forumcontent">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标签</label>
                                <div class="col-sm-9 tips">
                                    <input name="label" value="1" type="checkbox" >文件
                                    <input name="label" value="2" type="checkbox" >资源
                                    <input name="label" value="3" type="checkbox" >分享
                                    <input name="label" value="4" type="checkbox" >技术
                                    <input name="label" value="5" type="checkbox" >问答
                                    <input name="label" value="6" type="checkbox" >讨论
                                    <input name="label" value="7" type="checkbox" >其它
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
                                	<a href="list-analysis.jsp" class="btn btn-white"><i class="fa fa-reply"></i>返回</a>
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
	
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
   <script>
	$(document).ready(function() {
		//实例化编辑器
		var ue = UE.getEditor('editor');
		
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');


		//加载模块
        loadSelect();
        function loadSelect(){
            var option="";
            $.post("/forumsort/listJSON","",function (result) {
                $(result).each(function () {
                    if (this.type==0){
                        option="<option  value='"+this.id+"'>"+"主版块--"+this.title+"</option>";
                    }else {
                        option="<option  value='"+this.id+"'>"+"副板块--"+this.title+"</option>";
                    }

                    $("#sort").append(option).selectpicker('refresh');
                });

            },"json");
        }

        $(".btnSave").click(function () {
            //富文本框内容用隐藏框传值
            var content=ue.getContent();
            $("#content").val(content);
            var data=$("#saveForm").serialize();//序列化表单
            $.post("/forumpost/save",data,function (result) {

                if (result=="true"){
                    $("#saveForm")[0].reset();
                    ue.setContent('',false);
                    swal("发布成功","","success");

                }else {
                    swal("保存失败","保存失败!","error");
                }

            },"text");

        });

		

		
	});
   </script>
  
</body>


</html>
