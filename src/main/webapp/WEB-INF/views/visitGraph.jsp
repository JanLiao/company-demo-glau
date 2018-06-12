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
        <h2>就诊统计分析</h2>
      </blockquote>
   </div>
​
	<div id="card" class="jan-card-container">
		<div id="main" style="height:90%;">
		   
		</div>
		<div style="width:55%;float:left">
		  <label><font class="jan-card-size">日期范围</font></label>
		  <input type="text" class="jan-input" id="visitDate" style="width: 160px; height: 30px;" placeholder="默认前一个月">
		  &nbsp;&nbsp;&nbsp;
		  <label><font class="jan-card-size">性别</font></label>
          <select id="sex" name="sex" style="width: 110px; height: 30px;">
			<option value="2">请选择</option>
			<option value="0">男性</option>
			<option value="1">女性</option>
		  </select>
		</div>
		<div style="width:45%;float:left">
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
		   &nbsp;&nbsp;&nbsp;
		   <button class="layui-btn layui-btn-sm layui-btn-green" onclick="refresh()">
		    <i class="layui-icon">&#x1002;</i>刷新
		   </button>
		</div>
	</div>
	<script type="text/javascript">
	
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#visitDate' //指定元素
		    ,range: true
		  });
		});
	
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
	   
	   function refresh(){
		   init();
	   }
	   
	   function init(){
		   var date = document.getElementById("visitDate").value;
		   var sex = document.getElementById("sex").value;
		   
		   $.ajax({
			   type:"POST",
			   url:"<%=request.getContextPath()%>/visitAnalysis.do",
			   data:{'date':date, 'sex':sex},
			   dataType:"json",
			   success:function(msg){
				   if(msg.length == 0){
					   console.log('没有相关数据');
					   layui.use('layer', function(){
						   var layer = layui.layer;
						   
						   layer.msg("查询失败",{icon:5});
						 });
				   }else{
					   graphInit(msg);
				   }
			   }
		   });
	   }
	   
	   function graphInit(data){
		   var theme = document.getElementById("theme-select").value;
		   var myCharts = echarts.init(document.getElementById("main"), theme);
		   
		   var nameVisit = [];
		   var seriesVisitPie = [];
		   var seriesVisitData = [];
		   for(var i = 0; i < data.length; i++){
			   var map = {
					  value: 0,
					  name: ""
			   };
			   nameVisit.push(data[i].name);
			   map['name'] = data[i].name;
			   map['value'] = parseInt(data[i].total);
			   //map['data'] = [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8];
			   seriesVisitPie.push(map);
			   
			   //折线图
			   var map1 = {
					   name:'最高',
			            type:'line',
			            data:[]
			   };
			   map1['name'] = data[i].name;
			   map1['data'] = data[i].data.map(Number);
			   seriesVisitData.push(map1);
		   }
		   var map2 = {
				   name:'就诊总人数',
		            type:'pie',
		            tooltip : {
		                trigger: 'item',
		                formatter: '{a} <br/>{b} : {c} ({d}%)'
		            },
		            center: [150,130],
		            radius : [0, 60],
		            itemStyle :{
		                normal : {
		                    labelLine : {
		                        length : 20
		                    }
		                }
		            },
		            data: seriesVisitPie
		   };
		   seriesVisitData.push(map2);
		   
		   option = {
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data: nameVisit
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            dataZoom : {show: true},
				            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    dataZoom : {
				        show : true,
				        realtime : true,
				        start : 20,
				        end : 80
				    },
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : data[0].date
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				          position: 'right'
				        }
				    ],
				    series : seriesVisitData
				};
		   myCharts.setOption(option);
	   }
	   
	</script>​
</body>
</html>