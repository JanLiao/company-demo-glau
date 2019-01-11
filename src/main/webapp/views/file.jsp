<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta content="text/html;charset=UTF-8"/>
	<title>主页</title>
	<meta name="keywords" content="fsLayuiPlugin,layui,layuiPlugin,layui插件,layui快速开发插件" />
    <meta name="description" content="fsLayuiPlugin,layui,layuiPlugin,layui插件,layui快速开发插件" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta http-equiv ="Pragma" content = "no-cache"/>
	<meta http-equiv="Cache-Control" content="no cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no"/>
  <title>TestSubmit</title>
 </head>
 <body>
  <form name="upform" action="<%=request.getContextPath()%>/uploadImgDownloadPDF.do" method="POST" enctype="multipart/form-data">
　　文件2<input type="file" name="file"/><br/>
　　<input type="submit" value="Submit" /><br/>
  </form>
 </body>
</html>
