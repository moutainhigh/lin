<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="sysbook/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="SYSBOOK_ID" id="SYSBOOK_ID" value="${pd.SYSBOOK_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订座时间:</td>
								<td><input class="span10 date-picker" name="BOOK_TIME" id="BOOK_TIME" value="${pd.BOOK_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="订座时间" title="订座时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订座数量:</td>
								<td><input type="number" name="BOOK_NUMBER" id="BOOK_NUMBER" value="${pd.BOOK_NUMBER}" maxlength="32" placeholder="这里输入订座数量" title="订座数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订座类型:</td>
								<td><input type="text" name="BOOK_TYPE" id="BOOK_TYPE" value="${pd.BOOK_TYPE}" maxlength="4" placeholder="这里输入订座类型" title="订座类型" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订座是否成功类型:</td>
								<td><input type="text" name="STATE" id="STATE" value="${pd.STATE}" maxlength="4" placeholder="这里输入订座是否成功类型" title="订座是否成功类型" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						
					</form>
	
					<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#BOOK_TIME").val()==""){
				$("#BOOK_TIME").tips({
					side:3,
		            msg:'请输入订座时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BOOK_TIME").focus();
			return false;
			}
			if($("#BOOK_NUMBER").val()==""){
				$("#BOOK_NUMBER").tips({
					side:3,
		            msg:'请输入订座数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BOOK_NUMBER").focus();
			return false;
			}
			if($("#BOOK_TYPE").val()==""){
				$("#BOOK_TYPE").tips({
					side:3,
		            msg:'请输入订座类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BOOK_TYPE").focus();
			return false;
			}
			if($("#STATE").val()==""){
				$("#STATE").tips({
					side:3,
		            msg:'请输入订座是否成功类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATE").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>