<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子报工系统</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="http://demo.dwzjs.com"><img src="themes/default/images/login_logo.gif" /></a>
			</h1>
			<!-- <div class="login_headerContent">
				<h2 class="login_title"><img src="themes/default/images/login_title.png" /></h2>
			</div> -->
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="rest/user/login" method="post">
					<p>
						<label style="font-size:15px;color: black;">用户名：</label>
						<input type="text" name="username" size="20" class="login_input" />
					</p>
					<p>
						<label style="font-size:15px;color: black;">密码：</label>
						<input type="password" name="password" size="20" class="login_input" />
					</p>
					
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />	
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			
		</div>
		<div id="login_footer">
			Copyright &copy; 2009 www.dwzjs.com Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>