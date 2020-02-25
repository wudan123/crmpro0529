 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    <div class="row">
	    		<div class="col-sm-5">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加部门</h5>
                    </div>
                    <div class="ibox-content">
                        <form  id="saveFrom" class="form-horizontal">
                            <input type="hidden"  id="deptno" value="save">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门编号：</label>


                                <div class="col-sm-8">
                                    <input type="text" id="dno" name="deptno" class="form-control dno">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门名称：</label>

                                <div class="col-sm-8">
                                    <input type="text" id="dname" name="dname" class="form-control dname">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">区域：</label>

                                <div class="col-sm-8">
                                    <input type="text" id="dl" name="dlocation" class="form-control dl">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button id="btnSave" class="btn btn-sm btn-white btnSave" type="button"><i class="fa fa-save"></i> 保存</button>
                                    <button id="btnReset" class="btn btn-sm btn-white btnReset" type="button" ><i class="fa fa-undo"></i> 重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
	    		<div class="col-sm-7">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>部门列表 <small>点击修改信息将显示在左边表单</small></h5>
                    </div>

                    <div class="ibox-content">
                        <form id="saveForm" action="/dept/list" method="post">
                            <input type="hidden" id="btn" value="save">
                    		<div class="row">
                        		<div class="col-sm-2 col-sm-offset-2 text-right">
                        			<h3><small>搜索条件:</small></h3>
                        		</div>
                            <div class="col-sm-3">
                                <select name="type" class="selectpicker form-control">

                                    <option value="0">选择类型</option>
                                    <option value="1"  <c:if test="${type==1}">selected</c:if>>部门名称</option>
                                    <option value="2"  <c:if test="${type==2}">selected</c:if>>区域</option>
                                </select>
                            </div>
                           
                            <div class="col-sm-5">
                                <div class="input-group">
                                    <input name="keyword" type="text" value="${keyword}" placeholder="请输入关键词" class="input-sm form-control">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-search"></i> 搜索</button>
                                    </span>
                                </div>
                                
                            </div>

                        </div>
                        </form>

                        <div class="hr-line-dashed2"></div>
                        <div class="row">
                            <div class="table-responsive">
                            <table class="table table-striped list-table">
                                <thead>
                                    <tr>		
                                        <th>选择</th>
                                        <th>部门编号</th>
                                        <th>部门名称</th>
                                        <th>区域</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="dept">
                                    <tr>
                                        <td><input  type="checkbox" value="${dept.deptno}" ></td>
										<td>${dept.deptno}</td>
										<td>${dept.dname}</td>
										<td>${dept.dlocation}</td>
                                        <td>
	                                        	<a href="javascript:void(0)" class="update"  id="${dept.deptno}"><i class="glyphicon glyphicon-edit  text-navy"></i></a>
	                                        	<a href="javascript:void(0)" class="btndel" id="${dept.deptno}"><i class="glyphicon glyphicon-remove  text-navy"></i></a>
                                        </td>
                                    </tr>

                                </c:forEach>
                                <c:if test="${empty list}">
                                    <tr>
                                        <td colspan="5" class="text-center">没有找到你想搜索的内容- -</td>

                                    </tr>
                                </c:if>
                                    
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
	                        	<div class="col-sm-5">
	                        		<button class="btn btn-sm btn-primary selectAll" type="button"><i class="fa fa-check-square-o"></i> 全选</button>
	                        		<button class="btn btn-sm btn-primary selectReverse" type="button"><i class="fa fa-square-o"></i> 反选</button>
	                        		<button class="btn btn-sm btn-primary delAll" type="button"><i class="fa fa-times-circle-o"></i> 删除</button>
	                    
	                        	</div>
							<div class="col-sm-7 text-right">
								<div class="btn-group">

	                                <a href="/dept/list?pageNum=${page.pageNum-1}&type=${type}&keyword=${keyword}"  class="btn btn-sm btn-white <c:if test="${page.pageNum-1<1}">disabled</c:if>"><i class="glyphicon glyphicon-chevron-left"></i>
	                                </a>
                                    <c:forEach begin="1" end="${page.pages}" varStatus="vs">

	                                <a href="/dept/list?pageNum=${vs.index}&type=${type}&keyword=${keyword}" class="btn btn-sm btn-white <c:if test="${page.pageNum==vs.index}">active</c:if> ">${vs.index}</a>
                                    </c:forEach>

                                    <a href="/dept/list?pageNum=${page.pageNum+1}&type=${type}&keyword=${keyword}" type="button" class="btn btn-sm btn-white <c:if test="${page.pageNum+1>page.pages}">disabled</c:if>"><i class="glyphicon glyphicon-chevron-right"></i>
                                    </a>
	                            </div>
							</div>
                        </div>
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

	    // 全选
        $(".selectAll").click(function () {

            $("input[type='checkbox']").prop("checked","checked");
        })
        
        // 反选
        $(".selectReverse").click(function () {

            $("input:checkbox").each(function () {

                var temp=$(this).prop("checked");
                $(this).prop("checked",!temp);

            });

            
        })

        //获取id
        function checkRemove(){

            var ids="";

            $("input:checked").each(function () {
                var id=$(this).val();

                ids+=","+id;
            });
            ids=ids.substring(1);
            doRemove(ids);

        }


        //执行删除
        function doRemove(ids){

            $.post("/dept/remove","ids="+ids,function (result) {
               if (result=="true"){
                   swal({
                       title:"删除成功",
                       type:"success"

                   },function () {
                       location.href="/dept/list";
                   });
               }else {
                   swal("删除失败","","error");
               }

            },"text");
        }






        //保存按钮  添加/修改
        $("#btnSave").click(function () {

            var btn=$("#btn").val();
            var msg="保存";
            if (btn=="update"){
                msg="修改";
            }


            var data = $("#saveFrom").serialize(); //表单序列化

              $.post("/dept/"+btn,data,function (result) {
                  if (result=="true"){

                      swal({
                          title:msg+"成功",
                          type:"success"
                      },function () {
                          location.href="/dept/list";
                      })
                  }else {
                      swal(msg+"失败","","error");
                  }

              },"text");
          });




        $("#btnReset").click(function() {
            $("#saveFrom")[0].reset(); //清空
            $("#btn").val("save");
            //基本消息框－留着备用
            swal({
                title: "重置成功",
                text: "重置成功!",
                type:"success"
            })
        });



        $(".update").click(function () {

            var $this=$(this);
            var deptno=$this.prop("id");
         //   alert(deptno);

           $.post("/dept/toUpdate","deptno="+deptno,function (result) {

               $(result).each(function () {

                   var dname=this.dname;
                   var dno=this.deptno;
                   var dl=this.dlocation;

                   $("#dname").val(dname);
                   $("#dno").val(dno);
                   $("#dl").val(dl);
                   $("#btn").val("update");

               });


           },"json");

        });

        //批量删除
        $(".delAll").click(function () {
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


        //删除
        $(".btndel").click(function () {
           var $this=$(this);
            swal({
                title: "您确定要删除这条信息吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                closeOnConfirm: false
            }, function () {//此函数是点击删除执行的函数
                var id=$this.prop("id");
                doRemove(id);
            });


        });



    })




		
		

    </script>
    
</body>


</html>
