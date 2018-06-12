<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
<script src="layui/js/layui.js" charset="utf-8"></script>
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
		    url: '<%=request.getContextPath()%>/watch.do',
		   // data: {"mid": mid},
		    dataType : "json",
		    success: function(msg){
		    	var len = msg.length;
		    	data = msg;
				if(len == 0){
					
				}else if(len > 0){
					retDom();
				}
		    }
		});
	  //setTimeout("init()", 20000);
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
	
	if(ldata.qulity == 0){
		document.getElementById('evalL1').style.color= "green";
	}else if(ldata.qulity == 1){
		document.getElementById('evalL2').style.color= "red";
	}
	
	document.getElementById("percentL1").innerText = ldata.percent1;
	document.getElementById("percentL2").innerText = ldata.percent2;
	document.getElementById("percentL3").innerText = ldata.percent3;
	document.getElementById("percentL4").innerText = ldata.percent4;
	
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
	
	if(rdata.qulity == 0){
		document.getElementById('evalR1').style.color= "green";
	}else if(rdata.qulity == 1){
		document.getElementById('evalR2').style.color= "red";
	}
	
	document.getElementById("percentR1").innerText = rdata.percent1;
	document.getElementById("percentR2").innerText = rdata.percent2;
	document.getElementById("percentR3").innerText = rdata.percent3;
	document.getElementById("percentR4").innerText = rdata.percent4;
	
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
              
  
<br>

 
<div class="layui-progress" lay-showpercent="true">
  <div class="layui-progress-bar" lay-percent="20%"></div>
</div>
 
<br>
 
<div class="layui-progress" lay-showpercent="true">
  <div class="layui-progress-bar" lay-percent="5 / 10"></div>
</div>
 
<br>
 
<div class="layui-progress layui-progress-big" lay-showpercent="true">
  <div class="layui-progress-bar layui-bg-red" lay-percent="70%"></div>
</div>
 
<br>
 
 
<!-- 示例-970 -->
<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
  
 
          
<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use('element', function(){
  var $ = layui.jquery
  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
  
  //触发事件
  var active = {
    setPercent: function(){
      //设置50%进度
      element.progress('demo', '50%')
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
  
  $('.site-demo-active').on('click', function(){
    var othis = $(this), type = $(this).data('type');
    active[type] ? active[type].call(this, othis) : '';
  });
});
</script>

</body>
</html>