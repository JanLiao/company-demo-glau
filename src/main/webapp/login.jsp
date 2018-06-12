<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Glaucoma</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	

  

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/style.css">


	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body class="style-2">

		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-push-8">
					
					<!-- Start Sign In Form -->
					<div class="fh5co-form animate-box" data-animate-effect="fadeInRight">
						<h1>登  录</h1>
						<div class="form-group">
							<label for="username" class="sr-only">用户名</label>
							<input type="text" class="form-control" id="username" placeholder="用  户  名" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">密码</label>
							<input type="password" class="form-control" id="password" placeholder="密  码" autocomplete="off">
						</div>
						<!-- <div class="form-group">
							<label for="remember"><input type="checkbox" id="remember"> Remember Me</label>
						</div> -->
						<div class="form-group">
							<p>No account ? <a href="sign-up3.html">&nbsp;</a> | <a href="<%=request.getContextPath()%>/noAccount.do" class="jan-a" style="text-decoration:none;color: green;">&nbsp;游 客 访 问</a></p>
						</div>
						<div class="form-group">
							<input type="submit" value="登  录" style="width:100%;height:50px;" class="btn btn-primary" onclick="login()">
						</div>
					</div>
					<!-- END Sign In Form -->


				</div>
			</div>
			<div class="row jan-padding">
				<div class="col-md-12 text-center"><p><small></small></p></div>
			</div>
			
		</div>
		<!-- <br><br><br><br>
	<div align="center">&copy; All Rights Reserved cvte</div> -->
	<!-- jQuery -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript">
	//form表单 不是ajax
	  $("body").keydown(function(event){
	  	if(event.keyCode == "13"){
	  		login();
	  	}
	  }); 
	  
	layui.use('layer', function(){
		var layer = layui.layer;
    });
	  
	function validate(){
		  var account = document.getElementById("username").value;
		  var password = document.getElementById("password").value;
		  if(account == ""){
			  layer.msg("用户名不能为空",{icon:5, time:4000});
			  return false;
		  }
		  if(password == ""){
			  layer.msg("密码不能为空",{icon:5, time:4000});
			  return false;
		  }
		  return true;
	  }
	
	function login(){
		  var account = document.getElementById("username").value;
		  var password = document.getElementById("password").value;
		  if(validate()){
			  //console.log(account + '=' + password);
			  $.ajax({
					type:"POST",
					url:"<%=request.getContextPath()%>/loginIn.do",
					data:{"account": account, "password": password},
					dataType:"json",
					success:function(data){
						console.log(data.msg);
						console.log(data.fail);
						if(data.fail == 0){
							layer.msg(data.msg,{icon:6, time:2000});
							document.location = '<%=request.getContextPath()%>/index.do';
						}else if(data.fail == 1){
							layer.msg(data.msg,{icon:5, time:4000});
						}
					}
				});
		  }else{
			  
		  }
	}
	</script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
	
	</body>
</html>

