<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="keywords"
	content="fsLayuiPlugin,layui,layuiPlugin,layui插件,layui快速开发插件" />
<meta name="description"
	content="fsLayuiPlugin,layui,layuiPlugin,layui插件,layui快速开发插件" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no" />
<%@ include file="/layui/header.jsp"%>
<style type="text/css">
 .layui-btn-green{
 background-color:#47A447;
 }
</style>
<title>Insert title here</title>
</head>
<body>
   <div class="jan-card-risk">
      <blockquote class="layui-elem-quote layui-text">
        <h2>风险分析</h2>
      </blockquote>
   </div>
<!--    <div class="jan-card-if">
      <h3>筛选条件</h3>
   </div>
   <div class="jan-card-btn">
     <button class="layui-btn layui-btn-sm">青光眼</button>
     <button class="layui-btn layui-btn-sm">黄斑病</button>
     <button class="layui-btn layui-btn-sm">糖网病</button>
     <button class="layui-btn layui-btn-sm">病理性近视</button>
   </div> -->
		​
	<div id="card" class="jan-card-container">
		<div id="main" style="height:90%;">
		   
		</div>
		<div style="width:60%;float:left">
		  <button id="glaucoma" class="layui-btn layui-btn-sm" onclick="glaucomaSelect()">青光眼</button>
          <button id="amd" class="layui-btn layui-btn-sm" onclick="amdSelect()">黄斑病</button>
          <button id="dr" class="layui-btn layui-btn-sm" onclick="drSelect()">糖网病</button>
          <button id="pm" class="layui-btn layui-btn-sm" onclick="pmSelect()">病理性近视</button>
          <select id="sex" name="sex" style="width: 110px; height: 30px;">
			<option value="2">性别选择</option>
			<option value="0">男性</option>
			<option value="1">女性</option>
		  </select>
		</div>
		<div style="width:30%;float:left">
		   <label><font class="jan-card-size">切换主题</font></label>
		   <select id="theme-select" style="width: 110px; height: 30px;">
		      <option selected="true" name="macarons">macarons</option>
		      <option value="infographic">infographic</option>
		      <option value="shine">shine</option>
		      <option value="dark">dark</option>
		      <option value="blue">blue</option>
		      <option value="green">green</option>
		      <option value="red">red</option>
		      <option value="gray">gray</option>
		      <option value="helianthus">helianthus</option>
		      <option value="roma">roma</option>
		      <option value="mint">mint</option>
		      <option value="macarons2">macarons2</option>
		      <option value="sakura">sakura</option>
		      <option value="default">default</option>
		   </select>
		   <button class="layui-btn layui-btn-sm layui-btn-green" onclick="refresh()">
		    <i class="layui-icon">&#x1002;</i>刷新
		   </button>
		</div>
	</div>
	<script type="text/javascript">
	   $(function(){
		   var heigh =  document.body.scrollHeight || document.documentElement.scrollHeight;
		   var heigh1 = document.body.clientHeight;
		   var heigh2 = document.body.offsetHeight || document.documentElement.offsetHeight;
		   var heigh3 = document.documentElement.clientHeight || document.body.clientHeight;
		   var heigh4 = document.body.scrollTop || document.documentElement.scrollTop;
		   var heigh6 = document.body.scrollHeight;
		   //alert(heigh3);
		   heigh5 = heigh3*0.78;
		   var newheigh = heigh5 + 'px';
		   //console.log('height=' + newheigh);
		   var element = document.getElementById("card");
		   element.style.height = newheigh;
		   
		   init();
	   });
	   
	   function glaucomaSelect(){
		   //var element = document.getElementById("glaucoma");
		   //var color = element.style.backgroundColor;
		   var rgb = document.getElementById('glaucoma').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   document.getElementById('glaucoma').style.backgroundColor = "#009688";
		   }else{
			   document.getElementById('glaucoma').style.backgroundColor = "#A6A6A6";
		   }
		   
		   //console.log('color=' + rgb);
	   }
	   
	   function amdSelect(){
		   var rgb = document.getElementById('amd').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   document.getElementById('amd').style.backgroundColor = "#009688";
		   }else{
			   document.getElementById('amd').style.backgroundColor = "#A6A6A6";
		   }
	   }
	   
	   function drSelect(){
		   var rgb = document.getElementById('dr').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   document.getElementById('dr').style.backgroundColor = "#009688";
		   }else{
			   document.getElementById('dr').style.backgroundColor = "#A6A6A6";
		   }
	   }
	   
	   function pmSelect(){
		   var rgb = document.getElementById('pm').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   document.getElementById('pm').style.backgroundColor = "#009688";
		   }else{
			   document.getElementById('pm').style.backgroundColor = "#A6A6A6";
		   }
	   }
	   
	   function refresh(){
		   init();
	   }
	   
	   function init(){
		   var param = '';
		   var rgb = document.getElementById('glaucoma').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   param = param + '0';
		   }else{
			   param = param + '1';
		   }
		   rgb = document.getElementById('amd').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   param = param + ',0';
		   }else{
			   param = param + ',1';
		   }
		   rgb = document.getElementById('dr').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   param = param + ',0';
		   }else{
			   param = param + ',1';
		   }
		   var rgb = document.getElementById('pm').style.backgroundColor;
		   if(rgb == "rgb(166, 166, 166)"){
			   param = param + ',0';
		   }else{
			   param = param + ',1';
		   }
		   var sex = document.getElementById("sex").value;
		   
		   $.ajax({
			   type:"POST",
			   url:"<%=request.getContextPath()%>/riskAnalysis.do",
			   data:{'param':param, 'sex':sex},
			   dataType:"json",
			   success:function(msg){
				   if(msg.length == 0){
					   console.log('没有相关数据');
				   }else{
					   graphInit(msg);
				   }
			   }
		   });
	   }
	   
	   function graphInit(data){
		   var theme = document.getElementById("theme-select").value;
		   var myCharts = echarts.init(document.getElementById("main"), theme);
		   
		   var nameArray = [];
		   var series = [];
		   for(var i = 0; i < data.length; i++){
			   var map = {
					  name:'',
					  type:'bar',
					  data:[],
					  markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			   };
			   nameArray.push(data[i].name);
			   map['name'] = data[i].name;
			   map['data'] = data[i].series.map(Number);
			   //map['data'] = [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8];
			   series.push(map);
		   }
		   
		   option={
				   title : {
				        text: '风险统计',
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data: nameArray
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            data : data[0].xAxis
				                    
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series: series
		   };
		   myCharts.setOption(option);
	   }
	   
	</script>​
</body>
</html>