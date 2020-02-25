<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>项目管理系统 - 基础表格</title>
    <meta name="keywords" content="项目管理系统">
    <meta name="description" content="项目管理系统">
    <link rel="shortcut icon" href="favicon.ico"> 
    	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
    <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form action="/baoxiao/list" method="post">
                        <div class="row">
                        		<div class="col-sm-3 col-sm-offset-4 text-right">
                        			<h3><small>搜索条件:</small></h3>
                        		</div>
                            <div class="col-sm-2">
                                <select name="type" class="selectpicker form-control">
                                    <option value="0">---选择类型---</option>
                                    <option value="1">报销单号</option>
                                </select>
                            </div>
                           
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input name="keyword" type="text" placeholder="请输入关键词" class="input-sm form-control">
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
                                        <th>单号</th>
                                        <th>类型</th>
                                        <th>报销人</th>
                                        <th>总金额</th>
                                        <th>使用时间</th>
                                        <th>备注信息</th>
                                        <th>审批意见</th>
                                        <th>审批状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${baoxiaos}" var="baoxiao">
                                    <tr>
                                         <td>${baoxiao.bxid}</td>
                                        <td>${baoxiao.paymode}</td>
                                        <td>${baoxiao.employee.ename}</td>
                                        <td>${baoxiao.totalmoney}</td>
                                        <td><fmt:formatDate value="${baoxiao.bxtime}" pattern="yyyy-MM-dd"/></td>
                                        <td>
                                            <c:if test="${baoxiao.bxremark.length()>9}">
                                                ${baoxiao.bxremark.substring(0,9)}...
                                            </c:if>
                                            <c:if test="${baoxiao.bxremark.length()<=9}">
                                                ${baoxiao.bxremark}
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${baoxiao.bxstatus==0}"><font color="red">驳回</font></c:if>
                                            <c:if test="${baoxiao.bxstatus==1}">待审批</c:if>
                                            <c:if test="${baoxiao.bxstatus==2}">待付款</c:if>
                                            <c:if test="${baoxiao.bxstatus==3}">已付款</c:if>
                                        </td>
                                        <td>未审批</td>
                                        <td>
                                        		<a href="/baoxiao/show?bxid=${baoxiao.bxid}"><i class="glyphicon glyphicon-eye-open  text-navy"></i>
                                                </a>
                                            <c:if test="${baoxiao.bxstatus==0 or baoxiao.bxstatus==1}">
	                                        	<a href="/baoxiao/toUpdate?bxid=${baoxiao.bxid}"><i class="glyphicon glyphicon-edit  text-navy"></i>
                                                </a>
	                                        	<button value="${baoxiao.bxid}" type="button" class="btn btn-sm btn-primary btndel">撤销<i class="fa fa-times-circle-o"></i>
                                                </button>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${fn:length(baoxiaos)==0}">
                                    <tr>
                                        <td colspan="9" class="text-center">没有搜到你想要的- -</td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>


                       <div class="row">

                           <div class="col-sm-12 text-right">
                               <div class="btn-group">

                                   <a href="/baoxiao/listByEmpFk?pageNum=${page.pageNum-1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum-1<1}">disabled</c:if> "><i class="glyphicon glyphicon-chevron-left"></i>
                                   </a>
                                   <c:forEach begin="1" end="${page.pages}" varStatus="vs">

                                       <a href="/baoxiao/listByEmpFk?pageNum=${vs.index}&type=${type}&keyword=${keyword}" class="btn btn-sm btn-white    <c:if test="${page.pageNum==vs.index}">active</c:if> ">${vs.index}</a>
                                   </c:forEach>

                                   <a href="/baoxiao/listByEmpFk?pageNum=${page.pageNum+1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum+1>page.pages}">disabled</c:if>"><i class="glyphicon glyphicon-chevron-right"></i>
                                   </a>
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
		        var id=$(".btndel").val();
                $.post("/baoxiao/remove","id="+id,function (result) {
                    if (result=="true"){
                        swal({
                            title:"删除成功！",
                            text:"您已经成功删除此条报销记录。",
                            type:"success"
                        },function () {
                            location.href="/baoxiao/list";
                        })
                    }else {
                        swal("删除失败！", "请重试", "error");
                    }
                },"text");

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
