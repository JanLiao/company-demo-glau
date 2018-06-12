<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<link rel="stylesheet" href="layui/css/layui.css"  media="all">
<script src="layui/js/layui.js" charset="utf-8"></script>
<style type="text/css">
h1{font-size: 300%; padding: 0cm}
p {font-size: 220%}
p1 {font-size: 250%}
.example{font-size:48px; font-weight:bold;}
.headExample{font-size:55px; font-weight:bold;}
.layui-progress{height:30px;}
.layui-progress-bar{   height: 30px;}
.text-size{font-size:35px; line-height:30px;}
.text-size1{font-size:35px; line-height:70px;}
.text-size2{font-size:40px; line-height:70px;}
.layui-progress-text{font-size:25px; line-height:5px;}
.layui-bg-pig{background-color:0xF98E9!important;}

/* Safari 5.1 - 6.0 */ /* Opera 11.1 - 12.0 *//* Firefox 3.6 - 15 *//* 标准的语法（必须放在最后） */
/* #grad {
    background: -webkit-linear-gradient(left, green , red); 
    background: -o-linear-gradient(right, green, red); 
    background: -moz-linear-gradient(right, green, red); 
    background: linear-gradient(to right, green , red); 
} */

</style>
<script>

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
  
/*   function change(){
	  console.log(6666);
	  var othis = $(this), type = $(this).data('type');
	    console.log(othis);
	    console.log(type);
	    active[type] ? active[type].call(this, othis) : '';
  } */

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
  
/*   $('.site-demo-active').on('click', function(){
     var othis = $(this), type = $(this).data('type');
     console.log(othis);
    active[type] ? active[type].call(this, othis) : ''; 
  }); */
});
</script>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>

<script type="text/javascript" defer="defer">
$(function(){
	   init();
});
var data;
var ldata;
var rdata;
function init(){
	  $.ajax({
			type:"POST",
		    url: '<%=request.getContextPath()%>/img.do',
		   // data: {"mid": mid},
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

function changePercent(){
	console.log(888);
	setTimeout(function() {
		// IE
		if(document.all) {
			document.getElementById("clickMe").click();
		}
		// 其它浏览器
		else {
			var e = document.createEvent("MouseEvents");
			e.initEvent("click", true, true);
			document.getElementById("clickMe").dispatchEvent(e);
		}
	}, 100);
}

function retDom(){
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
	
	document.getElementById("uid").innerText = ldata.eid;
	document.getElementsByTagName('img')[0].setAttribute('src',ldata.e_url);
	document.getElementsByTagName('img')[2].setAttribute('src',ldata.e_result1);
	document.getElementsByTagName('img')[3].setAttribute('src',ldata.e_result2);
	
	if(ldata.qulity == 1 || ldata.qulity == 2){
		document.getElementById('evalL1').style.color= "green";
	}else if(ldata.qulity == 0){
		document.getElementById('evalL2').style.color= "red";
	}
	
	changePercent();
	
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

<title>Insert title here</title>
</head>
<body>
  <div style="width: 15%; height: 3%; float: left">&nbsp;</div>
  <div style="width: 70%; height: 95%; float: left">
      <div align="center" class="headExample">智能眼底病筛查报告单</div>
      <div class="example">ID: &nbsp;&nbsp;<label id="uid"></label></div>
      <br><br>
		<div align="center" style="height: 5%;">
			<div style="width: 40%; float: left" class="text-size2">左眼</div>
			<div style="width: 20%; float: left">&nbsp;</div>
			<div style="width: 40%; float: left" class="text-size2">右眼</div>
		</div>
		<br>
		<div  align="center" style="height:25%;padding:2px;">
			<div style="width: 40%; float: left">
			   <img id="imageL" src='img/82012_l.jpg'  width=450px height=450px/>
			</div>
			<div style="width: 20%; float: left">&nbsp;</div>
			<div style="width: 40%; float: left">
			   <img id="imageR" src='img/82012_l.jpg'  width=450px height=450px/>
			</div>
		</div>
		<div>&nbsp;</div>
		<hr style="width:100%;height:3px;border:none;border-top:3px groove skyblue;"/>
		
		<div class="example">质量评估</div>
		  <br><br>
		  <div style="height:50px">
		   <div style="width: 40%; float: left">
			  <div style="width: 30%; float: left" ><p>&nbsp;<label class="text-size">左眼评估情况:&nbsp;</label></div>
			  <div style="width: 30%; float: left"><p>&nbsp;&nbsp;</p></div>
			  <div style="width: 20%; float: left"><p><label id="evalL1" class="text-size">可用</label></p></div>
			  <div style="width: 20%; float: left"><p><label id="evalL2" class="text-size">不可用</label></p></div>
		   </div>
		   <div style="width: 20%; float: left">&nbsp;</div>
		   <div style="width: 40%; float: left">
		      <div style="width: 30%; float: left" ><p>&nbsp;<label class="text-size">右眼评估情况:&nbsp;</label></div>
			  <div style="width: 30%; float: left"><p>&nbsp;&nbsp;</p></div>
			  <div style="width: 20%; float: left"><p><label id="evalR1" class="text-size">可用</label></p></div>
			  <div style="width: 20%; float: left"><p><label id="evalR2" class="text-size">不可用</label></p></div>
		    </div>
		</div>
		<br><br>
		<hr style="width:100%;height:3px;border:none;border-top:3px groove skyblue;"/>
		
		
		<div class="example">疾病筛查(定性/风险)</div>
			<br><br>
			<div style="height:250px; ">
			   <div style="width: 40%; float: left">
			       <div style="width: 40%;float: left"><label class="text-size">青光眼(Glaucoma) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentL1"  lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar layui-bg-red" lay-percent="40%"></div>
                 </div>
                 <br><br><br><br>
                 <div style="width: 40%;float: left"><label class="text-size">黄斑病变(AMD) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentL2"  lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar" lay-percent="60%"></div>
                 </div>
                 <br><br><br><br>
                 <div style="width: 40%;float: left"><label class="text-size">糖网病(DR) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentL3"   lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar layui-bg-blue" lay-percent="75%"></div>
                 </div>
                 <br><br><br><br>
                 <div style="width: 40%;float: left"><label class="text-size">病理性近视(PM) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentL4"   lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar layui-bg-orange" lay-percent="80%"></div>
                 </div>
			   </div>
			<div style="width: 20%; float: left">&nbsp;</div>
			<div style="width: 40%; float: left">
			    <div style="width: 40%;float: left"><label class="text-size">青光眼(Glaucoma) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentR1"  lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar layui-bg-red" lay-percent="50%"></div>
                 </div>
                 <br><br><br><br>
                 <div style="width: 40%;float: left"><label class="text-size">黄斑病变(AMD) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentR2"   lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar" lay-percent="80%"></div>
                 </div>
                 <br><br><br><br>
                 <div style="width: 40%;float: left"><label class="text-size">糖网病(DR) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentR3"    lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar layui-bg-blue" lay-percent="30%"></div>
                 </div>
                 <br><br><br><br>
                 <div style="width: 40%;float: left"><label class="text-size">病理性近视(PM) : </label></div>
			       <div id="grad" class="layui-progress "  lay-filter="percentR4"    lay-showpercent="true" style="width: 60%; float: left">
                       <div class="layui-progress-bar layui-bg-orange" lay-percent="90%"></div>
                 </div>
			</div>
		</div>
		<br><br>
		<hr style="width:100%;height:3px;border:none;border-top:3px groove skyblue;"/>
		
		<div class="example">青光眼定量分析</div>
		<br>
		<div style="height: 25%;">
		    <div style="width: 40%; float: left">
		           <div style="height:30px">
		           <div style="width: 5%; float: left">&nbsp;</div>
		           <div style="width: 45%; float: left">
		               <img id="result_image1" src='img/l_result1.jpg' width=450px height=450px />
		           </div>
		          <div style="width: 5%; float: left">&nbsp;</div>
		          <div style="width: 45%; float: left">
		              <img id="result_image2" src='img/l_result2.jpg' width=450px height=450px />
		          </div>
		          </div>
		   </div>
		   <div style="width: 20%; float: left">&nbsp;</div>
			<div style="width: 40%; float: left">
		          <div style="height:300px">
		           <div style="width: 5%; float: left">&nbsp;</div>
		           <div style="width: 45%; float: left">
		               <img id="result_image3" src='img/r_result1.jpg' width=450px height=450px />
		           </div>
		          <div style="width: 5%; float: left">&nbsp;</div>
		          <div style="width: 45%; float: left">
		              <img id="result_image4" src='img/r_result2.jpg' width=450px height=450px />
		          </div>
		          </div>
			</div>
		</div>
		<div style="height: 5%;" >
		    <div>&nbsp;</div>
		    <div style="width: 40%; float: left">
		        <div class="text-size1">杯盘比CDR : <label id="cdrL"></label></div>
		        <div class="text-size1">视盘置信度(OD)：<label id="confLdisc"></label> &nbsp;&nbsp;视杯置信度(OC)：<label id="confLcup"></label></div>
		    </div>
		    <div style="width: 20%; float: left">&nbsp;</div>
			<div style="width: 40%; float: left">
			    <div class="text-size1">杯盘比CDR : <label id="cdrR"></label></div>
			    <div class="text-size1">视盘置信度(OD)：<label id="confRdisc"></label> &nbsp;&nbsp;视杯置信度(OC)：<label id="confRcup"></label></div>
			</div>
		</div>
		<br/>

  </div>
  <div style="width: 15%; height: 5%; float: left">&nbsp;</div>
  <ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
</body>
</html>