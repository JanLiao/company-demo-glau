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
        <h2>青光眼分析</h2>
      </blockquote>
   </div>
​
	<div id="card" class="jan-card-container">
		<div id="main" style="height:90%;">
		   
		</div>
		<div style="width:50%;float:left">
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
		   $.ajax({
			   type:"POST",
			   url:"<%=request.getContextPath()%>/cdrAnalysis.do",
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
		   
		   var nameCdr = [];
		   var seriesCdr = [];
		   for(var i = 0; i < data.length; i++){
			   nameCdr.push(data[i].name);
			   if(data[i].name == "男性"){
				   var mandata = [];
				   var sumMan = 0.000;
				   var lenMan = data[i].data.length;
				   console.log('lenman=' + lenMan);
				   for(var j = 0; j < data[i].data.length; j++){
					   var da = [];
					   da.push(data[i].data[j]);
					   da.push(Math.round(Math.random()* 30));
					   sumMan = sumMan + parseFloat(data[i].data[j]);
					   mandata.push(da);
				   }
				   var avgMan = parseFloat(sumMan/lenMan).toFixed(3);
				   console.log('sumMan=' + sumMan + ',avrMan=' + avgMan);
				   var map = {
						   name:'',
				           type:'scatter',
				           data:[],
				           markLine:{
				        	   data:[
				        		   [
				        		        {name: '男性CDR平均值', value: avgMan, xAxis: avgMan, yAxis: 0, itemStyle:{normal:{color:'#dc143c'}}},      // 当xAxis为类目轴时，数值1会被理解为类目轴的index，通过xAxis:-1|MAXNUMBER可以让线到达grid边缘
				        		        {name: '', xAxis: avgMan, yAxis: 30, itemStyle:{normal:{color:'#dc143c'}}},             // 当xAxis为类目轴时，字符串'周三'会被理解为与类目轴的文本进行匹配
				        		    ],
				        	   ]
				           }
				   };
				   map['name'] = data[i].name;
				   map['data'] = mandata;
				   seriesCdr.push(map);
			   }else if(data[i].name == "女性"){
				   var womandata = [];
				   var sumWoman = 0.000;
				   var lenWoman = data[i].data.length;
				   console.log('lenwoman=' + lenWoman);
				   for(var j = 0; j < data[i].data.length; j++){
					   var dat = [];
					   dat.push(data[i].data[j]);
					   dat.push(Math.round(Math.random()* 30));
					   sumWoman = sumWoman + parseFloat(data[i].data[j]);
					   womandata.push(dat);
				   }
				   var avgWoman = parseFloat(sumWoman/lenWoman).toFixed(3);
				   console.log('sumWoman=' + sumWoman + ',avgWoman=' + avgWoman);
				   var map = {
						   name:'',
				           type:'scatter',
				           data:[],
				           markLine:{
				        	   data:[
				        		   [
				        		        {name: '女性CDR平均值', value: avgWoman, xAxis: avgWoman, yAxis: 0, itemStyle:{normal:{color:'#dc143c'}}},      // 当xAxis为类目轴时，数值1会被理解为类目轴的index，通过xAxis:-1|MAXNUMBER可以让线到达grid边缘
				        		        {name: '', xAxis: avgWoman, yAxis: 30, itemStyle:{normal:{color:'#dc143c'}}},             // 当xAxis为类目轴时，字符串'周三'会被理解为与类目轴的文本进行匹配
				        		    ]
				        	   ]
				           }
				   };
				   map['name'] = data[i].name;
				   map['data'] = womandata;
				   seriesCdr.push(map);
			   }
		   }
		   
		   option = {
				    title : {
				        text: '青光眼杯盘比分布',
				    },
				    tooltip : {
				        trigger: 'axis',
				        showDelay : 0,
				        axisPointer:{
				            show: true,
				            type : 'cross',
				            lineStyle: {
				                type : 'dashed',
				                width : 1
				            }
				        }
				    },
				    legend: {
				        data:nameCdr
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataZoom : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    xAxis : [
				        {
				            type : 'value',
				            scale:true,
				            min: 0,
				            max: 1,
				            precision: 3, 
				            splitNumber: 20, 
				            boundaryGap: [0.001, 0.001], 
				            splitArea: { show: true } 
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            scale:true
				        }
				    ],
				    series : seriesCdr
				};
		   myCharts.setOption(option);
	   }
	   
	</script>​
</body>
</html>