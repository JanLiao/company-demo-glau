<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>HTML to PDF</title>
		<meta content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no" />
<link rel="stylesheet" type="text/css" href="layui/css/layui.css" media="all" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		
		<div>ID:</div>
		<div class="layui-row" align="center">
			<div class="layui-col-md4">左眼</div>
			<div class="layui-col-md4 layui-col-md-offset4">右眼</div>
		</div>
		<div class="layui-row" align="center">
			<div class="layui-col-md4">
			    <img id="result_image" src='img/82012_l.jpg' width=150px height=150px />
			</div>
			<div class="layui-col-md4 layui-col-md-offset4">
			   <img id="result_image" src='C:/img/82056_r.jpg' width=150px height=150px />
			</div>
		</div>

		<hr/>
		
		<div>质量评估</div>
		<div class="layui-row">
			<div class="layui-col-md4">好</div>
			<div class="layui-col-md4 layui-col-md-offset4">好</div>
		</div>

		<hr/>

		<div>疾病筛查(定性/风险)</div>
		<div class="layui-row">
			<div class="layui-col-md4">
				<div>glaucoma : </div>
				<div>AMD : </div>
				<div>DR : </div>
				<div>myopia : </div>
			</div>
			<div class="layui-col-md4 layui-col-md-offset4">
				<div>glaucoma : </div>
				<div>AMD : </div>
				<div>DR : </div>
				<div>myopia : </div>
			</div>
		</div>
		
		<hr/>
		
		<div>青光眼定量分析(视神经损伤)</div>
		<div class="layer-row">
		   <div class="layui-col-md4">
		       <div class="layer-row">
		           <div class="layui-col-md4">
		               <img id="result_image" src='img/l_result1.jpg' width=150px height=150px />
		           </div>
		          <div class="layui-col-md4 layui-col-md-offset4">
		              <img id="result_image" src='img/l_result2.jpg' width=150px height=150px />
		          </div>
		       </div>
		       <div>Glaucoma Risk：CDR = 0.6613704562187195</div>
		   </div>
			<div class="layui-col-md4 layui-col-md-offset4">
			   <div class="layer-row">
		           <div class="layui-col-md4">
		               <img id="result_image" src='img/r_result1.jpg' width=150px height=150px />
		           </div>
		          <div class="layui-col-md4 layui-col-md-offset4">
		              <img id="result_image" src='img/r_result2.jpg' width=150px height=150px />
		          </div>
		       </div>
		       <div>Glaucoma Risk：CDR = 0.6613704562187195</div>
			</div>
		</div>
	</body>
</html>