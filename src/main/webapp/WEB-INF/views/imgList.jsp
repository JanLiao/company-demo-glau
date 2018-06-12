<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <%@ include file="/layui/header.jsp"%>
   <title>统计列表</title>
   <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   <%-- <script src="<%=request.getContextPath()%>/layui/jquery-1.8.2.min.js"></script> --%>
   <script type="text/javascript" defer="defer">
   var node = [];
   $(function(){
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
		  
   	       //名称
		   var materialName = $("#materialName").val();
		   if(materialName != ""){
			   param = param + "materialName" + ",like,%" + materialName + "%,";
		   }
		   
		   
		   //类型
		   var fileType = $("#fileType").val();
		   if(fileType != ""){
			   param = param + "fileType" + ",=," + fileType + ",";
		   }
		   
		   
		   //状态
		   var statusId = $("#statusId").val();
		   if(statusId != ""){
			   param = param + "statusId" + ",=," + statusId + ",";
		   }
		   
		   //时间
		   var dateTime = $("#dateTime").val();
    	  
    	  layui.use('table', function(){
    		  var table = layui.table;
    		  table.render({
    		    elem: '#table1'
    		    ,id: 'flagTwo'
    		    ,url:'<%=request.getContextPath()%>/searchPatient.do'
    		    ,height: 550
    		    //,cellMinWidth: 120
    		    ,limits:[10,25,50,75,100]
    		    ,cols: [[
    		      //{field:'id', width:'1%'}
    		      {checkbox: true, event: 'set1', fixed: true}
    		      ,{field:'materialName',width:280, event: 'set2', title: '终端ID', fixed: true, sort: true}
    		      ,{field:'materialName',width:280, event: 'set2', title: '用户ID', fixed: true, sort: true}
    		      ,{field:'terminalName',width:120, event: 'set3', title: '质量', sort: true }
    		      ,{field:'resolution',width:100, event: 'set5', title: '青光眼风险'}
    		      ,{field:'size',width:130, event: 'set6', title: '黄斑病风险'}
    		      ,{field:'duration',width:100, event: 'set7', title: '糖网病风险', sort: true}
    		      ,{field:'usedNum',width:100, event: 'set8', title: '病理性近视', sort: true}
    		      ,{field:'pdfTime',width:160, event: 'set10', title: '拍片日期', sort: true
    		    	  ,templet: function(d){
    		    		  var date = new Date(d.uploadTime);
    		    		  var Y = date.getFullYear() + '-';
    		    		  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    		    		  var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
    		    		  return Y+M+D;
    		    	  }
    		      }
    		      ,{fixed: 'right', width:80, event: 'set12', title: '操作', align:'center', toolbar: '#barDemo'}
    		    ]]
    		    ,page: true
    		    ,where: {"param": param,"dateTime":dateTime}
    		    ,done: function(res, curr, count){
    		    	  //changeBg();
    		      }
    		  });
    		  
    		  table.on('tool(tableEvent)', function(obj){
    			  var tmpdata = obj.data;
    			  var mid = tmpdata.mid;
    			  var md5 = tmpdata.md5;
    			  var filePath = tmpdata.filePath;
    			  var name = filePath.split("/");
    			  var realname = name[name.length - 1];
    			  document.getElementById("videoView").value = realname;
    			  if(obj.event === 'mediaView'){
    				  $.ajax({
    						type:"POST",
    					    url: '<%=request.getContextPath()%>/material/deposeMaterial.do',
    					    data: {"mid": mid},
    					    dataType : "json",
    					    success: function(msg){
    					    	var value = msg.toString();
    							if(value=="true"){
    								window.open('<%=request.getContextPath()%>/file/videoViewDepose.jsp',"_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes,left=300,top=20, width=800, height=620");
    							}else if(value == "false"){
    								window.open('<%=request.getContextPath()%>/file/videoView.jsp',"_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes,left=300,top=20, width=800, height=620");
    							}
    					    }
    					});
    			  }else if(obj.event == "md5"){
    				  layer.open({
    					  title: 'MD5'
    					  ,content: md5
    					});
    			  }
    		  });
    		});
      }
      
      function myTrim(x) {
    	    return x.replace(/^\s+|\s+$/gm,'');
    	}
           
   </script>
   
</head>
<body>
  <div style="width: 86%; height:100%; float: left">
	<div class="layui-fluid">
		<div class="layui-row layui-col-space1">
			<div class="layui-col-md12">
				<div class="layui-form-query">
					<form class="layui-form" id="query_form">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-mid">终端：</label>
								<div class="layui-input-inline"
									style="width: 140px; height: 35px;">
									<select name="terminal" id="terminal" lay-verify="required" lay-search="" style="width: 140px; height: 35px;">
										<option value="">直接选择或搜索</option>
										<c:forEach items="${terminalPtable}" var = "terminal" varStatus = "status">
                                        <option value="${terminal.terminalId}">${terminal.terminalName}</option>
                                        </c:forEach>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-mid">类型：</label>
								<div class="layui-input-inline"
									style="width: 90px; height: 35px;">
									<select id="fileType" name="fileType" style="width: 150px; height: 35px;">
										<option value="">请选择</option>
										<option value="vedio">视频</option>
										<option value="picture">图片</option>
										<option value="other">其他</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-mid">状态：</label>
								<div class="layui-input-inline"
									style="width: 90px; height: 35px;">
									<select id="statusId" name="statusId" style="width: 150px; height: 35px;">
										<option value="">请选择</option>
										<option value="1">未审核</option>
										<option value="3">通过</option>
										<option value="4">未通过</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
			                    <label class="layui-form-mid">拍照范围：</label>
			                    <div class="layui-inline" style="">
				                    <input type="text" id="dateTime" name="dateTime" autocomplete="off" style="width: 170px; height: 36px;" class="layui-input fsDate" dateRange="1" placeholder=" - "/>
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