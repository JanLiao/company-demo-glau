<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <%@ include file="/layui/header.jsp"%>
   <title>结果列表</title>
   <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   <%-- <script src="<%=request.getContextPath()%>/layui/jquery-1.8.2.min.js"></script> --%>
   <script type="text/javascript" defer="defer">
   var node = [];
   var heigh5;
   $(function(){
	 //网页高度
	   var heigh = document.body.scrollHeight || document.documentElement.scrollHeight;
	   var heigh1 = document.body.clientHeight;
	   var heigh2 = document.body.offsetHeight || document.documentElement.offsetHeight;
	   var heigh3 = document.documentElement.clientHeight || document.body.clientHeight;
	   var heigh4 = document.body.scrollTop || document.documentElement.scrollTop;
	   heigh5 = heigh - heigh2;
	   console.log(heigh + "=" + heigh1 + "=" + heigh2 + "=" + heigh3 + "=" + heigh4);
	   init();
   });
   
      function init(){

    	  layui.use('layer', function(){
    		  var layer = layui.layer;
    		});
    	  
    	  layui.use('laydate', function(){
    		  var laydate = layui.laydate;
    		  //执行一个laydate实例
    		  laydate.render({
    		    elem: '#dateTime' //指定元素
    		    ,range: true
    		  });
    		});
    	  
		  
    	   var param = "";
		  
   	       //终端名称
   	       var tid = $("#terminal").val();
   	       console.log('tid=' + tid);
		   if(tid != ""){
			   param = param + "tid" + ",=," + tid + ",";
		   }
		   
		   //风险类型
		   var risk = $("#riskId").val();
		   if(risk != ""){
			   param = param + "risk,=," + risk + ",";
		   }
		   
		   
		   //类型
		   var radioId = document.getElementsByName("info");
		   var flag = 0;
		   var radioValue;
		   for(var i = 0; i < radioId.length; i++){
			   if(radioId[i].checked == true){
				   flag = flag + 1;
				   radioValue = radioId[i].value;
				   //param = param + "normal,=," + radioValue + ",";
			   }
		   }
		   if(flag == 0){
			   param = param + "normal,=,3,";
		   }else if(flag == 1){
			   param = param + "normal,=," + radioValue + ",";
		   }
		   
		   //时间
		   var dateTime = $("#dateTime").val();
		   console.log('param=' + param);

    	  layui.use('table', function(){
    		  var table = layui.table;
    		  table.render({
    		    elem: '#table1'
    		    ,id: 'flagTwo'
    		    ,url:'<%=request.getContextPath()%>/searchImgResult.do'
    		    ,height: heigh5
    		    //,cellMinWidth: 120
    		    ,limits:[10,25,50,75,100]
    		    ,cols: [[
    		      //{field:'id', width:'1%'}
    		      {checkbox: true, event: 'set1', fixed: true}
    		      ,{field:'tid',width:100, event: 'set2', title: '终端ID', fixed: true, sort: true}
    		      //,{field:'uid',width:100, event: 'set3', title: '用户ID', fixed: true, sort: true}
    		      ,{field:'imgName',width:100, event: 'set3', title: '图片名', fixed: true, sort: true}
    		      ,{field:'qulity',width:120, event: 'set4', title: '质量', sort: true}
    		      ,{field:'glaucomaRisk',width:150, event: 'set5', title: '青光眼风险', sort: true
    		    	  ,templet: function(d){
    		    		  var glau = d.glaucomaRisk;
    		    		  return glau + "%";
    		    	  }
    		      }
    		      ,{field:'amdRisk',width:150, event: 'set6', title: '黄斑病风险', sort: true
    		    	  ,templet: function(d){
    		    		  var amd = d.amdRisk;
    		    		  return amd + "%";
    		    	  }  
    		      }
    		      ,{field:'drRisk',width:150, event: 'set7', title: '糖网病风险', sort: true
    		    	  ,templet: function(d){
    		    		  var dr = d.drRisk;
    		    		  return dr + "%";
    		    	  }  
    		      }
    		      ,{field:'pmRisk',width:150, event: 'set8', title: '病理性近视', sort: true
    		    	  ,templet: function(d){
    		    		  var pm = d.pmRisk;
    		    		  return pm + "%";
    		    	  }  
    		      }
    		      ,{field:'cdr',width:150, event: 'set9', title: '杯盘比(CDR)', sort: true}
    		      ,{field:'od',width:150, event: 'set10', title: '视盘置信度(OD)', sort: true}
    		      ,{field:'oc',width:150, event: 'set11', title: '视杯置信度(OC)', sort: true}
    		      ,{field:'saveDate',width:160, event: 'set12', title: '日期', sort: true
    		    	  ,templet: function(d){
    		    		  var date = new Date(d.saveDate);
    		    		  var Y = date.getFullYear() + '-';
    		    		  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    		    		  var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';    		   		 
    		    		  return Y+M+D;
    		    	  }
    		      }
    		      ,{fixed: 'right', width:160, event: 'set13', title: '操作', align:'center', toolbar: '#barDemo'}
    		    ]]
    		    ,page: true
    		    ,where: {"param": param,"dateTime":dateTime}
    		    ,done: function(res, curr, count){
    		    	  //changeBg();
    		      }
    		  });
    		  
    		  table.on('tool(tableEvent)', function(obj){
    			  var tmpdata = obj.data;
    			  var tid = tmpdata.tid;
    			  var imgName = tmpdata.imgName;
    			  if(obj.event === 'userInfo'){
    				  $.ajax({
    						type:"POST",
    					    url: '<%=request.getContextPath()%>/imgUserInfo.do',
    					    data: {"tid": tid, "imgName": imgName},
    					    dataType : "json",
    					    success: function(msg){
    					    	//var value = msg.toString();
    							if(msg.fail == 0){
    								document.getElementById("pid").value = msg.msg;
    								console.log('pid=' + msg.msg);
    								var w=screen.availWidth - 10;
    								var h=screen.availHeight - 60;
    								window.open('<%=request.getContextPath()%>/views/userInfo.jsp', '_blank', 'resizable=yes,directories=no,top=0,left=0,width='+w+',height='+h);
    							}else if(msg.fail == 1){
    								layer.msg("宇宙大爆炸,出现未知错误", {icon: 5});
    							}
    					    }
    					});
    			  }else if(obj.event == "pdfDownload"){
    				  var tmpdata = obj.data;
        			  var tid = tmpdata.tid;
        			  var imgName = tmpdata.imgName;
        			  $.ajax({
        				  type:"POST",
        				  url:'<%=request.getContextPath()%>/imgUserInfo.do',
        				  data:{"tid": tid, "imgName": imgName},
        				  dataType:"json",
        				  success: function(msg){
        					  if(msg.fail == 1){
        						  layer.msg("宇宙大爆炸,出现未知错误", {icon:5});
        					  }else if(msg.fail == 0){
        						  var pid = msg.msg;
        						  console.log('pid=' + pid);
        						  var form=$("<form>");//定义一个form表单
        		  				    form.attr("style","display:none");
        		  				    form.attr("target","");
        		  				    form.attr("method","post");
        		  				    form.attr("action","<%=request.getContextPath()%>/downloadPDF.do");//请求url
        		  				    var input1=$("<input>");
        		  				    input1.attr("type","hidden");
        		  				    input1.attr("name","pid");//设置属性的名字
        		  				    input1.attr("value",pid);//设置属性的值
        		  				    $("body").append(form);//将表单放置在web中
        		  				    form.append(input1);
        		  				    form.submit();//表单提交
        					  }
        				  }
        			  });
    			  }
    		});
      });
    }
      
      function myFunction(pid){
    	  console.log('pid=' + pid);
    	  var percent = document.getElementById("percent" + pid).value;
	    	if(parseInt(percent) != percent){
	    		layer.msg("输入的不是整数",{icon:5,time:2000});
	    		document.getElementById("percent" + pid).value = "";
				return ;
	    	}
	    	if(isNaN(percent)){
	    		layer.msg("输入的不是整数",{icon:5,time:2000});
	    		document.getElementById("percent" + pid).value = "";
				return ;
			}else{
				var va = parseInt(percent);
				if(va >100){
					layer.msg("输入的值超过100",{icon:5,time:2000});
					document.getElementById("percent" + pid).value = "";
					return ;
				}else if( va < 0){
					layer.msg("输入的值为负",{icon:5,time:2000});
					document.getElementById("percent" + pid).value = "";
					return ;
				}
			}
      }
           
   </script>
   
</head>
<body>
<input type="hidden" id="pid" value="" />
  <div style="width: 100%; height:100%; float: left">
	<div class="layui-fluid">
		<div class="layui-row layui-col-space1">
			<div class="layui-col-md12">
				<div class="layui-form-query">
					<form class="layui-form" id="query_form">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-mid">终端:</label>
								<div class="layui-input-inline"
									style="width: 110px; height: 35px;">
									<select name="terminal" id="terminal" lay-verify="required" lay-search="" style="width: 110px; height: 35px;">
										<option value="">选择或搜索</option>
										<c:forEach items="${terminal}" var = "terminal" varStatus = "status">
                                        <option value="${terminal}">${terminal}</option>
                                        </c:forEach>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-mid">风险类型：</label>
								<div class="layui-input-inline"
									style="width: 110px; height: 35px;">
									<select id="riskId" name="riskId" style="width: 110px; height: 35px;">
										<option value="">请选择</option>
										<option value="1">青光眼</option>
										<option value="2">黄斑病</option>
										<option value="3">糖网病</option>
										<option value="4">病理性近视</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-label">高风险：</label>
								    <div class="layui-input-block" >
                                       <input type="radio" name="info" value="1" title="否" lay-filter="infoValue" checked>
                                       <input type="radio" name="info" value="0" title="是" lay-filter="infoValue">
                                    </div>
							</div>
							
							<div class="layui-inline">
			                    <label class="layui-form-mid">日期:</label>
			                    <div class="layui-inline" style="">
				                    <input type="text" id="dateTime" name="dateTime" autocomplete="off" style="width: 170px; height: 36px;" class="layui-input jan-input" dateRange="1" placeholder=""/>
			                    </div>
		                    </div>
							
							<div class="layui-inline">
								<div class="layui-inline">
									<button class="layui-btn" type="button" function="query" onclick="init()">
										<i class="layui-icon">&#xe615;</i>查询
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			
		 <div class="layui-col-md12">
            <table class="layui-table" id="table1" lay-filter="tableEvent"></table>
			<!-- <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="top" topUrl="views/datagrid2/one.html" topMode="readonly" topWidth="800px" topHeight="600px" topTitle="查看demo" inputs="id:">查看</a> -->
			<script type="text/html" id="barDemo">
 				<a class="layui-btn layui-btn-sm" lay-event="userInfo" >详细</a>
                <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="pdfDownload" >PDF下载</a>
			</script>
			<script type="text/html" id="barDemo1">
 				<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="pdfDownload" >PDF下载</a>
			</script>
	      </div>
			
		</div>
	</div>
  </div>
	<script type="text/javascript">
	layui.use('form', function(){
		  var form = layui.form;
		});
	</script>
</body>
</html>