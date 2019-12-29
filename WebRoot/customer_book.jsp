<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 导入easyui的资源文件 -->
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link id="themeLink" rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<script type="text/javascript">
		console.log(window.location.href);//先瞅一下这个是啥，可不就是刚刚打开页面的时候传的地址么
		var str = window.location.href;
		let list =  str.split('?');//用刚刚传递地址里面的“？”把传过来的东西劈开
		console.log(list);//这就成一个数组了
		var rowid = decodeURI(list[1]);//传过来的中文结果都是%跟十六进制数字，那就得用decodeURI转一下
		console.log(rowid);
	</script>
	<script type="text/javascript">
		$(function(){
  			$("#list").datagrid({
  				//url:后台数据查询的地址
  				url:"customer/BookfindById.action?customerid="+rowid,
  				//columns：填充的列数据
  					//field:后台对象的属性
  					//tille:列标题
  					
  				columns:[[
  					{
  						field:"id",
  						title:"书籍编号",
  						width:100,
  						checkbox:true
  					},
  					{
  						field:"name",
  						title:"书籍名称",
  						width:200
  					},
  					{
  						field:"customerid",
  						title:"客户ID",
  						width:200
  					}
  				]],
  				//显示分页
  				pagination:true,
  				//工具条
  				toolbar:"#tb"
  				
  			});
  			})
	</script>
		
	
  </head>
  
  <body>
		<table id="list"></table>
		<!-- 工具条 -->
  	<div id="tb">
		<a id="addBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		<a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		<a id="deleteBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	
  </body>
</html>
