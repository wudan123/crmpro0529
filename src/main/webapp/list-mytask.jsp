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
    	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
    <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form action="/task/myList" method="post">
                           <div class="row">
                               <div class="col-sm-3 col-sm-offset-4 text-right">
                                   <h3><small>搜索条件:</small></h3>
                               </div>
                               <div class="col-sm-2">
                                   <select name="type" class="selectpicker form-control">
                                       <option value="0">选择类型</option>
                                       <option <c:if test="${type==1}">selected</c:if> value="1">任务标题</option>
                                   </select>
                               </div>

                               <div class="col-sm-3">
                                   <div class="input-group">
                                       <input name="keyword" value="${keyword}" type="text" placeholder="请输入关键词" class="input-sm form-control">
                                       <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-search"></i> 搜索</button>
                                    </span>
                                   </div>

                               </div>
                           </div>
                       </form>
                        <div class="hr-line-dashed2"></div>
                        <div class="table-responsive">
                            <table class="table table-striped list-table">
                                <thead>
                                    <tr>
                                        
                                        <th>序号</th>
                                        <th>任务标题</th>
                                        <th>创建人</th>
                                        <th>状态</th>
                                        <th>优先级</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="task">
                                    <tr>
                                        
                                        <td>${task.id}</td>
                                        <td>${task.tasktitle}</td>
                                        <td>${task.creator.ename}</td>
                                        <td>
                                            <c:if test="${task.status==0}"><i class="fa fa-hourglass-start"></i> 分配</c:if>
                                            <c:if test="${task.status==1}"><i class="fa fa-hourglass-half"></i> 进行中...</c:if>
                                            <c:if test="${task.status==2}"><i class="fa fa-hourglass-end"></i>完成</c:if>

                                        </td>
                                        <td>
                                            <c:if test="${task.level=='高'}"><i class="fa fa-star"></i>高</c:if>
                                            <c:if test="${task.level=='低'}"><i class="fa fa-star-o"></i>低</c:if>
                                            <c:if test="${task.level=='中'}"><i class="fa fa-star-half-empty"></i>中</c:if>
                                            <c:if test="${task.level=='暂缓'}"><i class="fa fa-star-half-empty"></i>暂缓</c:if>
                                        </td>
                                        <td><fmt:formatDate value="${task.starttime}" pattern="yyyy-MM-dd"/></td>
                                        <td><fmt:formatDate value="${task.endtime}" pattern="yyyy-MM-dd"/></td>
                                        <td>
                                        	<a href="/task/show?id=${task.id}" class="btn btn-primary btn-xs" href="show-task.jsp"><i class="fa fa-eye"></i>查看</a>
                                            <c:if test="${task.status==0}">
                                                <button data-taskid="${task.id}"  class="btn btn-success btn-xs btnStart"><i class="fa fa-hourglass-start "></i>开始</button>
                                            </c:if>
                                            <c:if test="${task.status==1}">
                                                <button data-taskid="${task.id}"  class="btn btn-danger btn-xs btnEnd"><i class="fa fa-hourglass-end "></i> 完成</button>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty list}">
                                    <tr>
                                        <td colspan="8" class="text-center">没有找到你想要的内容- -</td>
                                    </tr>
                                </c:if>
                                    
                                </tbody>
                            </table>
                        </div>
                       
                        <div class="row">
	                        
							<div class="col-sm-12 text-right">
                                <div class="btn-group">

                                    <a href="/task/myList?pageNum=${page.pageNum-1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum-1<1}">disabled</c:if> "><i class="glyphicon glyphicon-chevron-left"></i>
                                    </a>
                                    <c:forEach begin="1" end="${page.pages}" varStatus="vs">

                                        <a href="/task/myList?pageNum=${vs.index}&type=${type}&keyword=${keyword}" class="btn btn-sm btn-white    <c:if test="${page.pageNum==vs.index}">active</c:if> ">${vs.index}</a>
                                    </c:forEach>

                                    <a href="/task/myList?pageNum=${page.pageNum+1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum+1>page.pages}">disabled</c:if>"><i class="glyphicon glyphicon-chevron-right"></i>
                                    </a>
                                </div>
							</div>
                        </div>
						
                    </div>
                </div>
     </div>       
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
     <script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>

   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white').selectpicker('setStyle', 'btn-sm');

		//改变开始状态
		$("body").on("click",".btnStart",function () {
		    var id=$(this).data("taskid");
		    var $this=$(this);//转换js对象
            $target=$(this).parents("tr").children().eq("3");//获取这一行的状态
            swal({
                title: "你确定开始任务?",
                text: "任务开始，状态将变为进行中...",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "rgba(169,85,243,0.37)",
                confirmButtonText: "yes",
                closeOnConfirm: false
            }, function () {//此函数是点击删除执行的函数
                var message="开始任务,记得按时完成哦!";
                var content="<i class='fa fa-hourglass-half'></i> 进行中...";
                updateStatus(id,1,$this,message,$target,content);

            });

        });

		//改变完成状态
        $("body").on("click",".btnEnd",function () {
            var id=$(this).data("taskid");
            var message="恭喜你,任务完成!";
            var $this=$(this);
            $target=$(this).parents("tr").children().eq("3");//获取这一行的状态
            swal({
                title: "你确定结束任务?",
                text: "任务结束就无法更改!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "rgba(169,85,243,0.37)",
                confirmButtonText: "yes",
                closeOnConfirm: false
            }, function () {//此函数是点击删除执行的函数
                var content="<i class='fa fa-hourglass-end'></i>完成";

                updateStatus(id,2,$this,message,$target,content);

            });

        });

        //改变状态函数
        function updateStatus(id,status,$this,message,$target,content){

            $.post("/task/update",{"id":id,"status":status},function (result) {
               if (result=="true"){
                   //修改成功，改变状态图标
                   $target.html(content);

                   if (status==1){
                       $this.removeClass("btn-success btnStart").addClass("btn-danger btnEnd").html("<i class='fa fa-hourglass-end'></i> 完成").unbind("click");
                   }else if (status==2){
                       $this.remove();//移除
                   }
                   swal("状态修改成功",message,"success");

               }else {
                   swal("状态修改失败",message,"error");
               }

            },"text");

        }





		
		//点击删除
		$('.btndel').click(function () {
		    swal({
		        title: "您确定要删除这条信息吗",
		        text: "删除后将无法恢复，请谨慎操作！",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "删除",
		        closeOnConfirm: false
		    }, function () {//此函数是点击删除执行的函数
		    		//这里写ajax代码
		    		// 以下是成功的提示框，请在ajax回调函数中执行
			    swal("删除成功！", "您已经永久删除了这条信息。", "success");
		    });
		});
		
		
		$("#demo1").click(function() {
			//基本消息框－留着备用
			swal({
				title: "欢迎使用SweetAlert",
				text: "Sweet Alert 是一个替代传统的 JavaScript Alert 的漂亮提示效果。"
			})
		});
	});
    </script>
    
</body>


</html>
