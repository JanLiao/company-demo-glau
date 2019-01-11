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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jan.css" />
	<style>
/* 为了区分效果 */
div[carousel-item]>*{text-align: center; line-height: 280px; color: #fff;}
div[carousel-item]>*:nth-child(4n){background-color: #009688;}
div[carousel-item]>*:nth-child(4n+1){background-color: #5FB878;}
div[carousel-item]>*:nth-child(4n+2){background-color: #01AAED;}
div[carousel-item]>*:nth-child(4n+3){background-color: #FF5722;}
#test2 div[carousel-item]>*{line-height: 120px;}
/* .layui-progress{height:15px;}
.layui-progress-bar{height: 15px;} */
/* .layui-progress-text{font-size:13px; line-height:5px;} */
.layui-bg-pig{background-color:0xF98E9!important;}
</style>
	
	<script type="text/javascript" defer="defer">
$(function(){
	 init();
});
function init(){
}
</script>
	
</head>
<body>
   <div class="jan-content"></div>
   <div align="center" class="jan-head">
       无袖带血压持续监测系统
   </div>
   
   <div class="jan-id">ID:&nbsp;&nbsp;<label id="uid">195332</label></div>
   <div class="jan-hr">
      <hr style="width:100%;height:3px;border:none;border-top:3px groove skyblue;"/>
   </div>
   
   <div class="jan-left-num">
      <div style="height:2%;">&nbsp;</div>
      <div style="height:32%;">ID:&nbsp;&nbsp;<label id="jan-shrink">120  mmHg</label></div>
      <div style="height:32%;">ID:&nbsp;&nbsp;<label id="jan-relax">70  mmHg</label></div>
      <div style="height:32%;">ID:&nbsp;&nbsp;<label id="jan-heart">65  bpm</label></div>
      <div style="height:2%;">&nbsp;</div>
   </div>
   
   <div id="ware" class="jan-ware">
      
   </div>
   
   <div class="jan-left-notify">
      <div style="height:2%;">&nbsp;</div>
      <div style="height:32%;">Tips</div>
      <div style="height:32%;">&nbsp;&nbsp;&nbsp;</div>
      <div style="height:32%;">
      <p>
                         您有一定的高血压风险，建议饮食少油少盐，戒烟限酒，多做有氧运动，保证充足的睡眠。
      </p>
      </div>
      <div style="height:2%;">&nbsp;</div>
   </div>
   
   <div id="report" class="jan-report">
      
   </div>
</body>
</html>
