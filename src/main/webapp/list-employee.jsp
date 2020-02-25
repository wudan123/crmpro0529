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
                       <form action="/employee/list" method="post">
                        <div class="row">
                        		<div class="col-sm-3 col-sm-offset-2 text-right">
                        			<h3><small>搜索条件:</small></h3>
                        		</div>
                            <div class="col-sm-2">
                                <select name="type" class="selectpicker form-control">

                                    <option value="0">---选择类型---</option>
                                    <option <c:if test="${type==1}">selected</c:if> value="1">姓名</option>
                                </select>
                            </div>
                           
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" name="keyword" value="${keyword}" placeholder="请输入关键词" class="input-sm form-control">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-search"></i>  搜索</button>
                                    </span>
                                </div>
                                
                            </div>
                            <div class="col-sm-2 text-right">
                            		<a href="/save-employee.jsp" class="btn btn-sm btn-primary" type="button"><i class="fa fa-plus-circle"></i> 添加员工</a>
                            	</div>
                        </div>
                       </form>

                        <div class="hr-line-dashed2"></div>
                        <div class="table-responsive">
                            <table class="table table-striped list-table">
                                <thead>
                                    <tr>
                                        <th>选择</th>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>职位</th>
                                        <th>性别</th>
                                        <th>联系电话</th>
                                        <th>入职时间</th>
                                        <th>状态</th>
                                        <th class="text-center">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="employee">
                                    <tr>
                                        <td><input  type="checkbox" ${employee.eid}></td>
										<td>${employee.eid}</td>
										<td>${employee.ename}</td>
										<td>${employee.position.name}</td>
										<td>${employee.esex}</td>
										<td>${employee.eage}</td>
										<td>${employee.telephone}</td>
										<td><fmt:formatDate value="${employee.hiredate}" pattern="yyyy-MM-dd"/> </td>
                                        <td class="text-right">
	                                        	<a href="show-employee.jsp" class="btn btn-primary btn-xs"><i class="fa fa-eye"></i>查看</a>
	                                        	<button class="btn btn-primary btn-xs"><i class="fa fa-edit"></i>编辑</button>
	                                        	<button class="btn btn-danger btn-xs btndel"><i class="fa fa-close"></i>删除</button>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <c:if test="${empty list}">
                                    <tr>
                                        <td colspan="9" class="text-center">没有找到你想要的- -</td>
                                    </tr>

                                </c:if>
                                    
                                </tbody>
                            </table>
                        </div>
                       
                        <div class="row">
	                        	<div class="col-sm-5">
	                        		<button class="btn btn-sm btn-primary" type="button"><i class="fa fa-check-square-o"></i> 全选</button>
	                        		<button class="btn btn-sm btn-primary" type="button"><i class="fa fa-square-o"></i> 反选</button>
	                        		<button class="btn btn-sm btn-primary" type="button"><i class="fa fa-times-circle-o"></i> 删除</button>
	                        		<button id="demo1" class="btn btn-sm btn-primary" type="button"><i class="fa fa-table"></i> 导出Excel</button>
	                        	</div>
							<div class="col-sm-7 text-right">
                                <div class="btn-group">

                                    <a href="/employee/list?pageNum=${page.pageNum-1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum-1<1}">disabled</c:if> "><i class="glyphicon glyphicon-chevron-left"></i>
                                    </a>
                                    <c:forEach begin="1" end="${page.pages}" varStatus="vs">

                                        <a href="/employee/list?pageNum=${vs.index}&type=${type}&keyword=${keyword}" class="btn btn-sm btn-white    <c:if test="${page.pageNum==vs.index}">active</c:if> ">${vs.index}</a>
                                    </c:forEach>

                                    <a href="/employee/list?pageNum=${page.pageNum+1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum+1>page.pages}">disabled</c:if>"><i class="glyphicon glyphicon-chevron-right"></i>
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
