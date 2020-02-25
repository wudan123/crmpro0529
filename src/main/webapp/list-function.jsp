 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                       <form action="/function/list" method="post">
                        <div class="row">
                        		<div class="col-sm-3 col-sm-offset-2 text-right">
                        			<h3><small>搜索条件:</small></h3>
                        		</div>
                            <div class="col-sm-2">
                                <select name="type" class="selectpicker form-control">
                                    <option value="0">---选择类型---</option>
                                    <option  <c:if test="${type==1}">selected</c:if> value="1">功能名称</option>
                                    <option  <c:if test="${type==2}">selected</c:if> value="2">模块名称</option>
                                    <option  <c:if test="${type==3}">selected</c:if> value="3">需求名称</option>
                                    <option  <c:if test="${type==4}">selected</c:if> value="4">项目名称</option>
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
                            <div class="col-sm-2 text-right">
                            		<a href="/save-function.jsp" class="btn btn-sm btn-primary" type="button"><i class="fa fa-plus-circle"></i>添加功能</a>
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
                                        <th>功能名称</th>
                                        <th>模块名称</th>
                                        <th>需求名称</th>
                                        <th>项目名称</th>
                                        <th>优先级</th>
                                        <th>添加时间</th>
                                        <th>修改时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="function" varStatus="vs">
                                    <tr>
                                        <td><input  type="checkbox" value="${function.id}"></td>
								
										<td>${vs.count}</td>
										<td><a href=''>${function.functionname}</a></td>
										<td><a href=''>${function.module.modulename}</a></td>
										<td><a href=''>${function.analysis.title}</a></td>
										<td><a href=''>${function.project.name}</a></td>
										<td>
                                            <c:if test="${function.level=='高'}"><i class="fa fa-star"></i>高</c:if>
                                            <c:if test="${function.level=='低'}"><i class="fa fa-star-o"></i>低</c:if>
                                            <c:if test="${function.level=='中'}"><i class="fa fa-star-half-empty"></i>中</c:if>
                                        </td>
										<td><fmt:formatDate value="${function.addtime}" pattern="yyyy-MM-dd"/> </td>
										<td><fmt:formatDate value="${function.updatetime}" pattern="yyyy-MM-dd"/></td>
                                        <td>
	                                        	<a href="/function/show?id=${function.id}"><i class="glyphicon glyphicon-eye-open  text-navy"></i></a>
	                                        	<a href="/function/toUpdate?id=${function.id}"><i class="glyphicon glyphicon-edit  text-navy"></i></a>
	                                        	<a id="${function.id}" href="javascript:void(0)" class="btndel"><i class="glyphicon glyphicon-remove  text-navy"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                   <c:if test="${empty list}">
                                       <tr>
                                           <td colspan="10" class="text-center">没有找到你想要的内容- -</td>
                                       </tr>
                                   </c:if>
                                    
                                </tbody>
                            </table>
                        </div>
                       
                        <div class="row">
	                        	<div class="col-sm-5">
	                        		<button class="btn btn-sm btn-primary selectAll" type="button"><i class="fa fa-check-square-o"></i> 全选</button>
	                        		<button class="btn btn-sm btn-primary reverse" type="button"><i class="fa fa-square-o"></i> 反选</button>
	                        		<button class="btn btn-sm btn-primary btnDelAll" type="button"><i class="fa fa-times-circle-o"></i> 删除</button>
	                        		<button id="demo1" class="btn btn-sm btn-primary" type="button"><i class="fa fa-table"></i> 导出Excel</button>
	                        	</div>
							<div class="col-sm-7 text-right">
                                <div class="btn-group">

                                    <a href="/function/list?pageNum=${page.pageNum-1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum-1<1}">disabled</c:if> "><i class="glyphicon glyphicon-chevron-left"></i>
                                    </a>
                                    <c:forEach begin="1" end="${page.pages}" varStatus="vs">

                                        <a href="/function/list?pageNum=${vs.index}&type=${type}&keyword=${keyword}" class="btn btn-sm btn-white    <c:if test="${page.pageNum==vs.index}">active</c:if> ">${vs.index}</a>
                                    </c:forEach>

                                    <a href="/function/list?pageNum=${page.pageNum+1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum+1>page.pages}">disabled</c:if>"><i class="glyphicon glyphicon-chevron-right"></i>
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


		//全选
        $(".selectAll").click(function () {
          $("input:checkbox").prop("checked","checked");
        });

        //反选
        $(".reverse").click(function () {

           $("input[type='checkbox']").each(function () {

               var temp=$(this).prop("checked");
               $(this).prop("checked",!temp);
           });

        });


        function checkRemove(){

            var ids="";

            $("input:checked").each(function () {
                var id=$(this).val();

                ids+=","+id;
            });
            ids=ids.substring(1);
            remove(ids);

        }


        function remove(ids){
            $.post("/function/remove","ids="+ids,function (result) {
                if(result=="true"){

                    swal({
                        title:"删除成功",
                        text:"删除成功!",
                        type:"success"

                    },function () {
                        location.href="/function/list";
                    });

                }else if (result=="false"){
                    swal("删除失败","删除失败!","error");

                }else {
                    swal({
                        title:"全部或部分删除失败",
                        text:"删除失败的id有"+result+"请确认后在删除!",
                        type:"warning"
                    },function () {
                        location.href="/function/list";
                    })

                }

            },"text");

        }

        // 批量删除
        $('.btnDelAll').click(function () {

            swal({
                title: "您确定要删除这条信息吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                closeOnConfirm: false
            }, function () {//此函数是点击删除执行的函数
                checkRemove();
            });

        });

        // 单个删除
        $(".btndel").click(function () {

            // var value=$(this).val();
            var $this=$(this)  // jquery对象转换js对象


            swal({
                title:"您确定要删除这条信息吗",
                text:"删除之后将无法回复，请谨慎操作!",
                type:"warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                closeOnConfirm: false

            },function () {
                var id=$this.prop("id");
                remove(id);
            })

        });
		

	});
    </script>
    
</body>


</html>
