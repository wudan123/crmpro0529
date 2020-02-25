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
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link rel="stylesheet" href="css/customer.css" />
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
      <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>对标管理<small>>添加指标基本信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal">
                        	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">选择对标公司:</label>
                                <div class="col-sm-3">
                                    <select id="projectName" name="level" class="selectpicker form-control">
										<option value="0">---选择公司---</option>

									</select>
                                </div>
                            </div>
                        </div>
                     	<div class="row indexvalue-title">
                     		<div class="col-sm-12 list-group-item-info indexvalue-text">
                     			<h4>	对标企业信息如下 <i class="fa fa-hand-o-down"></i></h4>
                     		</div>
                     	</div>
                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">公司名称:</label>
                                <div id="dacname" class="col-sm-3 show-text">
                                    xxxxxxx
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">营业额:</label>
                                <div id="daturnover" class="col-sm-3 show-text">
                                    xxxxx
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年份:</label>
                                <div id="datime" class="col-sm-3 show-text">
                                    xxxxxx
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">员工数量:</label>
                                <div id="empcount" class="col-sm-3 show-text">
                                    xxxxxx
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">行业地位:</label>
                                <div id="dasort" class="col-sm-3 show-text">
                                    xxxxx
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">创建时间:</label>
                                <div id="buildtime" class="col-sm-3 show-text">
                                    xxxxx
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">主要业务:</label>
                                <div id="dabusiness" class="col-sm-9 show-text">
                                    xxxxxx
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">优势:</label>
                                <div id="dasuperiority" class="col-sm-9 show-text">
                                    xxxxxx
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">劣势:</label>
                                <div id="dainforiority" class="col-sm-9 show-text">
                                     xxxxxxx
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">简单描述:</label>
                                <div id="remark" class="col-sm-9 show-text">
                                    xxxxxx
                                </div>
                            </div>
                        </div>
                        </form>
                     	
                     	<div class="row indexvalue-title">
                     		<div class="col-sm-12 list-group-item-info indexvalue-text">
                     			<h4>添加对标信息  <i class="fa fa-hand-o-down"></i></h4>
                     		</div>
                     	</div>

                     	<form id="saveForm"  enctype="multipart/form-data" class="form-horizontal">
                            <input type="hidden" name="inId" value="${indexvalue.inId}">


                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">目标公司</label>
                                <div class="col-sm-3">
                                    <input id="companyname" value="${indexvalue.dataCollect.dacname}" type="text" class="form-control input-sm">
                                    <input name="comnameFk" type="hidden" id="comnameFK">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">目标营业额</label>
                                <div class="col-sm-3">
                                    <input name="inTurnover" value="${indexvalue.inTurnover}" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">业务方向</label>
                                <div class="col-sm-3">
                                    <input name="inBusiness" value="${indexvalue.inBusiness}" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">开始时间</label>
                                <div class="col-sm-3">
                                     <input name="inStarttime" value='<fmt:formatDate value="${indexvalue.inStarttime}" pattern="yyyy-MM-dd"/>'   id="start" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">上传详细计划书</label>
                                <div class="col-sm-3">
                                   <input name="myfiles"  type="file" class="form-control file" >
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">截止时间</label>
                                <div class="col-sm-3">
                                    <input name="inEndtime" value='<fmt:formatDate value="${indexvalue.inEndtime}" pattern="yyyy-MM-dd"/>'   id="finish" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="inRemark" class="form-control">${indexvalue.inRemark}</textarea>
                                </div>
                            </div>
                        </div>
                        </form>
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button type="button" class="btn btn-primary btnSave"><i class="fa fa-save"></i> 保存内容</button>
                                </div>
                                <div class="col-sm-3">
                                	<a href="/indexvalue/list" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
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
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
	<script src="${pageContext.request.contextPath}/js/inputfile.js"></script>
   <script>
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');
		//初始化日期控件
		laydate({elem: "#finish"});
	//	laydate({elem: "#buid"});
		laydate({elem: "#start"});


        // 加载公司
        var name="${indexvalue.dataCollect.dacname}";
        loadProject();
        function loadProject() {
            $.getJSON("/datacollect/listJSON","",function (result) {
                $(result).each(function () {
                    if (name==this.dacname){
                        var daid=this.daid;
                        $("#projectName").val(daid);
                        autoSelect(daid);
                    }

                    var option="<option selected data-projectpid='"+this.daid+"' value='"+this.dacname+"'>"+this.dacname+"</option>";

                    $("#projectName").append(option).selectpicker('refresh');
                });

                $("#projectName").selectpicker('val',name);


            });



        }

        $("#projectName").change(function () {
            var daid;
            $("#projectName").children().each(function () {
                if ($(this).prop("selected")){
                    daid=$(this).data("projectpid");
                }
            })

           $("#comnameFK").val(daid);//赋给目标公司,用于保存btnSave
           $.post("/datacollect/getByIdJSON","id="+daid,function (result) {
               $("#dacname").text(result.dacname);
               $("#daturnover").text(result.daturnover);
               $("#datime").text(new Date(result.datime).format("yyyy-MM-dd"));
               $("#empcount").text(result.empcount);
               $("#dasort").text(result.dasort);
               $("#buildtime").text(new Date(result.buildtime).format("yyyy-MM-dd"));
               $("#dabusiness").text(result.dabusiness);
               $("#dasuperiority").text(result.dasuperiority);
               $("#dainforiority").text(result.dainforiority);
               $("#remark").text(result.remark);

           },"json");

        });


        function autoSelect(daid){
            $.post("/datacollect/getByIdJSON","id="+daid,function (result) {
                $("#dacname").text(result.dacname);
                $("#daturnover").text(result.daturnover);
                $("#datime").text(new Date(result.datime).format("yyyy-MM-dd"));
                $("#empcount").text(result.empcount);
                $("#dasort").text(result.dasort);
                $("#buildtime").text(new Date(result.buildtime).format("yyyy-MM-dd"));
                $("#dabusiness").text(result.dabusiness);
                $("#dasuperiority").text(result.dasuperiority);
                $("#dainforiority").text(result.dainforiority);
                $("#remark").text(result.remark);

            },"json");

        }



        // js格式日期  new Date(日期毫秒值).format("yyyy-MM-dd")
        Date.prototype.format =function(format){
            var o = {
                "M+" : this.getMonth()+1, //month
                "d+" : this.getDate(), //day
                "h+" : this.getHours(), //hour
                "m+" : this.getMinutes(), //minute
                "s+" : this.getSeconds(), //second
                "q+" : Math.floor((this.getMonth()+3)/3), //quarter
                "S" : this.getMilliseconds() //millisecond
            }
            if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
                (this.getFullYear()+"").substr(4- RegExp.$1.length));
            for(var k in o)if(new RegExp("("+ k +")").test(format))
                format = format.replace(RegExp.$1,
                    RegExp.$1.length==1? o[k] :
                        ("00"+ o[k]).substr((""+ o[k]).length));
            return format;
        }

        $(".file").on("change","input[type='file']",function () {
           var filename=$(this).val();
           alert(filename);

        });


        //修改
        $(".btnSave").click(function () {



            $.ajax({
                url: "/indexvalue/update",
                type: 'POST',
                dataType:"text",
                cache: false,
                data: new FormData($("#saveForm")[0]), //$() 工厂函数    将DOM对象转化JQuery对象,返回的是一个数组   $("#id")
                processData: false,
                contentType: false,  //把浏览器提交表单的方式变为：multipart/form-data
                success: function (result) {

                    if(result=="true"){


                        swal({
                            title:"修改成功",
                            text:"",
                            type:"success"

                        },function () {
                            location.href="/indexvalue/list";
                        })
                    }else {
                        swal("修改失败","失败,请检查数据有效性!","error");
                    }
                }
            });
        });












        //--------------------下面是常用代码模版---------------------------------------
		
		//下拉列表使用ajax加载说明例子
		function loadSelect(){
			var option='<option value="0">-------请选择------</option><option value="4">用友软件</option><option selected value="5">浪潮软件</option>';
			$("#company").jsp(option);
			//ajax填充数据后需调用下面的方法来刷新下拉列表
			$("#company").selectpicker('refresh');
		}
		loadSelect();
		
		//点击按钮，消息提示框
		$("#demo1").click(function() {
			//基本消息框－留着备用
			swal({
				title: "恭喜，添加成功",
				text: "数据添加成功，为您返回列表页！"
			})
		});
		
		// 选择客户公司名称时自动选对应的客户方负责人
		$("#company").change(function(){
			var thisVal=$(this).val();
			//val指value属性,选中value属性＝thisVal的选项
			$('#customer').selectpicker('val',thisVal);
		});
		
	});
   </script>
   <!-- 修复日期控件长度-->
   <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet">
</body>


</html>
