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
	<%@ include file="/layui/header.jsp"%>
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
<script type="text/javascript">
layui.use('element', function(){
	  var $ = layui.jquery
	  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
	  
	  function change(){
		  element.progress('percentL1', ldata.percent1);
		  element.progress('percentL2', ldata.percent2);
		  element.progress('percentL3', ldata.percent3);
		  element.progress('percentL4', ldata.percent4);
		  
		  element.progress('percentR1', rdata.percent1);
		  element.progress('percentR2', rdata.percent2);
		  element.progress('percentR3', rdata.percent3);
		  element.progress('percentR4', rdata.percent4);
	  }
	  
	  //触发事件
	  var active = {
	    setPercent: function(){
	      //设置50%进度
	    	element.progress('percentL1', ldata.percent1);
			  element.progress('percentL2', ldata.percent2);
			  element.progress('percentL3', ldata.percent3);
			  element.progress('percentL4', ldata.percent4);
			  
			  element.progress('percentR1', rdata.percent1);
			  element.progress('percentR2', rdata.percent2);
			  element.progress('percentR3', rdata.percent3);
			  element.progress('percentR4', rdata.percent4);
	    }
	    ,loading: function(othis){
	      var DISABLED = 'layui-btn-disabled';
	      if(othis.hasClass(DISABLED)) return;
	    
	      //模拟loading
	      var n = 0, timer = setInterval(function(){
	        n = n + Math.random()*10|0;  
	        if(n>100){
	          n = 100;
	          clearInterval(timer);
	          othis.removeClass(DISABLED);
	        }
	        element.progress('demo', n+'%');
	      }, 300+Math.random()*1000);
	      
	      othis.addClass(DISABLED);
	    }
	  };

	  var runTime = 0
	  var interval = setInterval(function() {
		  runTime += 1;
		  if(runTime === 60){
		  clearInterval(interval);
		  }else{
			  var othis = $(this), type='setPercent';
			  active[type] ? active[type].call(this, othis):'';
		  }
		  //do whatever here..
		  }, 500);
	});
	</script>
	
	<script type="text/javascript" defer="defer">
$(function(){
	 init();
});
var data;
var ldata;
var rdata;
function init(){
	var pid=window.opener.document.getElementById("pid").value; 
	console.log('pid=' + pid);
	  $.ajax({
			type:"POST",
		    url: '<%=request.getContextPath()%>/eyeInfo.do',
		    data: {"pid": pid},
		    dataType : "json",
		    success: function(msg){
		    	var len = msg.length;
		    	data = msg;
		    	console.log(len);
				if(len == 0){
					
				}else if(len > 0){
					retDom();
				}
		    }
		});
	  //setTimeout("init()", 20000);
}

function retDom(){
	//console.log("data length=" + data.length);
	if(data[0].flag == 0){
		ldata = data[0];
		rdata = data[1];
	}else{
		ldata = data[1];
		rdata = data[0];
	}
	leftReset();
	rightReset();
}

function leftReset(){
	
	if(ldata.eid == ""){
		console.log("没有左眼数据");
	}else{
		document.getElementById("uid").innerText = ldata.eid;
	}
	
	document.getElementsByTagName('img')[0].setAttribute('src',ldata.e_url);
	document.getElementsByTagName('img')[2].setAttribute('src',ldata.e_result1);
	document.getElementsByTagName('img')[3].setAttribute('src',ldata.e_result2);
	
	if(ldata.qulity == 1 || ldata.qulity == 2){
		document.getElementById('evalL1').style.color= "green";
	}else if(ldata.qulity == 0){
		document.getElementById('evalL2').style.color= "red";
	}
		
	/* layui.use('element', function(){
		  var element = layui.element;
		  element.progress('percentL1', ldata.percent1);
		  element.progress('percentL2', ldata.percent2);
		  element.progress('percentL3', ldata.percent3);
		  element.progress('percentL4', ldata.percent4);
	}); */
	
	document.getElementById("cdrL").innerText = ldata.cdr;
	
	document.getElementById("confLdisc").innerText = ldata.fullconf;
	document.getElementById('confLdisc').style.color= "cyan";
	document.getElementById("confLcup").innerText = ldata.cupconf;
	document.getElementById('confLcup').style.color= "blue";
}

function rightReset(){
	if(rdata.eid == ""){
		console.log("没有右眼数据");
	}else{
		document.getElementById("uid").innerText = rdata.eid;
	}
	
	document.getElementsByTagName('img')[1].setAttribute('src',rdata.e_url);
	document.getElementsByTagName('img')[4].setAttribute('src',rdata.e_result1);
	document.getElementsByTagName('img')[5].setAttribute('src',rdata.e_result2);
	
	if(rdata.qulity == 1 || rdata.qulity == 2){
		document.getElementById('evalR1').style.color= "green";
	}else if(rdata.qulity == 0){
		document.getElementById('evalR2').style.color= "red";
	}
	
	layui.use('element', function(){
		  var element = layui.element;
		  element.progress('percentR1', rdata.percent1);
		  element.progress('percentR2', rdata.percent2);
		  element.progress('percentR3', rdata.percent3);
		  element.progress('percentR4', rdata.percent4);
	});
	
	document.getElementById("cdrR").innerText = rdata.cdr;
	
	document.getElementById("confRdisc").innerText = rdata.fullconf;
	document.getElementById('confRdisc').style.color= "cyan";
	document.getElementById("confRcup").innerText = rdata.cupconf;
	document.getElementById('confRcup').style.color= "blue";
}
</script>
	
</head>
<body>
   <div align="center" class="jan-head">
       智能眼底病筛查报告单
   </div>
   
   <div class="jan-id">ID:&nbsp;&nbsp;<label id="uid"></label></div>
   <div class="jan-pic1">
      <div align="center">
      <div class="jan-size">左眼</div>
      <div style="width:50%;">
          <img id="imageL" src='<%=request.getContextPath()%>/img/u13_p13_2018_R_CON_1.jpg' />
      </div>
      </div>
   </div>
   <div class="jan-pic2">
      <div align="center">
      <div class="jan-size">右眼</div>
      <div style="width:50%;">
          <img id="imageL" src='<%=request.getContextPath()%>/img/u13_p13_2018_L_CON_1.jpg'/>
      </div>
      </div>
   </div>
   <div class="jan-hr">
      <hr style="width:100%;height:3px;border:none;border-top:3px groove skyblue;"/>
   </div>
   
   <div class="jan-qulity">质量评估</div>
   <div class="jan-left-qulity">
      <div style="width: 30%; float: left" ><p>&nbsp;<label class="text-size">左眼评估情况:&nbsp;</label></div>
	  <div style="width: 30%; float: left"><p>&nbsp;&nbsp;</p></div>
	  <div style="width: 20%; float: left"><p><label id="evalL1" class="text-size">可用</label></p></div>
	  <div style="width: 20%; float: left"><p><label id="evalL2" class="text-size">不可用</label></p></div>
   </div>
   <div class="jan-right-qulity">
      <div style="width: 30%; float: left" ><p>&nbsp;<label class="text-size">右眼评估情况:&nbsp;</label></div>
	  <div style="width: 30%; float: left"><p>&nbsp;&nbsp;</p></div>
	  <div style="width: 20%; float: left"><p><label id="evalR1" class="text-size">可用</label></p></div>
	  <div style="width: 20%; float: left"><p><label id="evalR2" class="text-size">不可用</label></p></div>
   </div>
   <div class="jan-hr1">
      <hr style="width:100%;height:3px;border:none;border-top:3px groove skyblue;"/>
   </div>
   
   <div class="jan-percent">疾病筛查(定性/风险)</div>
   <div class="jan-glaucoma-left">
         <div style="width: 40%;float: left;"><label class="text-size">青光眼(Glaucoma) : </label></div>
		 <div id="grad" class="layui-progress "  lay-filter="percentL1"  lay-showpercent="true" style="width: 55%; float: left;height:50%;top:10%;">
                  <div class="layui-progress-bar layui-bg-red" lay-percent="40%" style="height:100%;"></div>
         </div>
   </div>
   <div class="jan-glaucoma-right">
		<div style="width: 40%;float: left;"><label class="text-size">青光眼(Glaucoma) : </label></div>
	    <div id="grad" class="layui-progress "  lay-filter="percentR1"  lay-showpercent="true" style="width: 58%; float: left;height:50%;top:10%;">
                   <div class="layui-progress-bar layui-bg-red" lay-percent="50%" style="height:100%;"></div>
        </div>
   </div>
   <div class="jan-amd-left">
       <div style="width: 40%;float: left;"><label class="text-size">黄斑病变(AMD) : </label></div>
	   <div id="grad" class="layui-progress "  lay-filter="percentL2"  lay-showpercent="true" style="width: 55%; float: left;height:50%;top:10%;">
               <div class="layui-progress-bar" lay-percent="60%" style="height:100%;"></div>
       </div>
   </div>
   <div class="jan-amd-right">
       <div style="width: 40%;float: left;"><label class="text-size">黄斑病变(AMD) : </label></div>
	   <div id="grad" class="layui-progress "  lay-filter="percentR2"   lay-showpercent="true" style="width: 58%; float: left;height:50%;top:10%;">
              <div class="layui-progress-bar" lay-percent="80%" style="height:100%;"></div>
       </div>
   </div>
   <div class="jan-dr-left">
        <div style="width: 40%;float: left;"><label class="text-size">糖网病(DR) : </label></div>
		<div id="grad" class="layui-progress "  lay-filter="percentL3"   lay-showpercent="true" style="width: 55%; float: left;height:50%;top:10%;">
            <div class="layui-progress-bar layui-bg-blue" lay-percent="75%" style="height:100%;"></div>
        </div>
   </div>
   <div class="jan-dr-right">
        <div style="width: 40%;float: left;"><label class="text-size">糖网病(DR) : </label></div>
		<div id="grad" class="layui-progress "  lay-filter="percentR3"    lay-showpercent="true" style="width: 58%; float: left;height:50%;top:10%;">
              <div class="layui-progress-bar layui-bg-blue" lay-percent="30%" style="height:100%;"></div>
        </div>
   </div>
   <div class="jan-pm-left">
        <div style="width: 40%;float: left;"><label class="text-size">病理性近视(PM) : </label></div>
		<div id="grad" class="layui-progress "  lay-filter="percentL4"   lay-showpercent="true" style="width: 55%; float: left;height:50%;top:10%;">
            <div class="layui-progress-bar layui-bg-orange" lay-percent="80%" style="height:100%;"></div>
        </div>
   </div>
   <div class="jan-pm-right">
        <div style="width: 40%;float: left;"><label class="text-size">病理性近视(PM) : </label></div>
		<div id="grad" class="layui-progress "  lay-filter="percentR4"    lay-showpercent="true" style="width: 58%; float: left;height:50%;top:10%;">
             <div class="layui-progress-bar layui-bg-orange" lay-percent="90%" style="height:100%;"></div>
         </div>
   </div>
   <div class="jan-hr2">
      <hr style="width:100%;height:3px;border:none;border-top:3px groove skyblue;"/>
   </div>
   
   <div class="jan-analysis">青光眼定量分析</div>
   <div class="jan-result-left">
        <div align="center">
           <div style="width:45%;float: left;">
          <img id="result_image1" src='<%=request.getContextPath()%>/img/full_u13_p13_2018_R_CON_1.jpg' />
        </div>
        <div style="width:10%;float: left;">&nbsp;</div>
        <div style="width:45%;float: left;">
          <img id="result_image2" src='<%=request.getContextPath()%>/img/crop_u13_p13_2018_R_CON_1.jpg' />
        </div>
        </div>
   </div>
   <div class="jan-result-right">
        <div align="center">
           <div style="width:45%;float: left;">
          <img id="result_image3" src='<%=request.getContextPath()%>/img/full_u13_p13_2018_L_CON_1.jpg' />
        </div>
        <div style="width:10%;float: left;">&nbsp;</div>
        <div style="width:45%;float: left;">
          <img id="result_image4" src='<%=request.getContextPath()%>/img/crop_u13_p13_2018_L_CON_1.jpg' />
        </div>
        </div>
   </div>
   <div class="jan-cdr-left">
       <div>杯盘比CDR : <label id="cdrL"></label></div>
   </div>
   <div class="jan-cdr-right">
       <div>杯盘比CDR : <label id="cdrR"></label></div>
   </div>
   <div class="jan-odoc-left">
       <div>视盘置信度(OD)：<label id="confLdisc"></label> &nbsp;&nbsp;视杯置信度(OC)：<label id="confLcup"></label></div>
   </div>
   <div class="jan-odoc-right">
       <div>视盘置信度(OD)：<label id="confRdisc"></label> &nbsp;&nbsp;视杯置信度(OC)：<label id="confRcup"></label></div>
   </div>
</body>
</html>
