<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>客户管理</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
	<!-- 导入easyui的资源文件 -->
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link id="themeLink" rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
</head>
<body>



<%--文件上传 --%>
    <div style="align-items: center;align-content: center;">
        <h1>文件上传</h1>
        <form id="form" method="post" enctype="multipart/form-data">
            <p>请选择要上传的文件:</p>
            <input id="file" name="file" type="file"  accept=".txt" multiple="multiple"/>
            <br>
            <input id="upload" name="upload" type="button" value="上传">
        </form>
    </div>
    
<%--文件夹上传 
<%--
<form action="saveFileupload/upload.action" enctype="multipart/form-data" method="post">    
	<input type="hidden" name="type" value="1"/>
    <input id="dir" type="file" name="dir" webkitdirectory mozdirectory/>
    <input id="uploadDir" type="submit" value="提交文件夹">
</form>
--%>
<a id="downfile"  href=""></a>

<script type="text/javascript">
	    var filename = null;
        
           $(window).ready(function () {
        $("#upload").click(function () {
           var formData = new FormData($('#form')[0]);//获取表单中的文件
           //ajax请求
           $.ajax({
               url:"saveFileupload/uploadFile.action",//后台的接口地址
               type:"post",//post请求方式
               data:formData,//参数
               cache: false,
               processData: false,
               contentType: false,
               success:function (data) {
                   
             for(var s in data){
            		console.log(data[s]);
            		filename = data[s];
            		$("#downfile").text(data[s]);
            		$("#downfile").attr("href","saveFileupload/downfile.action?filename="+encodeURI(filename));
            		
     			}
               },error:function () {
                   alert("操作失败~");
               }
           })
        });
    });

	
</script>

</body>
</html>