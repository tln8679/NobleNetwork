<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js">
</script>

	<style>
input.ng-invalid {
	background-color: pink;
}

input.ng-valid {
	background-color: lightgreen;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
	border-right: 1px solid #bbb;
}

li:last-child {
	border-right: none;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li
 
a
:hover
:not
 
(
.active
 
)
{
background-color
:
 
#111
;


}
.active {
	background-color: #4CAF50;
}
</style>

	<head>
		<base href="<%=basePath%>">

		<title>Register</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body ng-app="">
	<ul>
			<li>
				<a class="active" href="LoggedIn">Home</a>
			</li>
			<li>
				<a href="/Testing/NewPost">Articles</a>
			</li>
			<li>
				<a href="#contact">Contact</a>
			</li>
			<li style="float: right">
				<a href="#about">About</a>
			</li>
		</ul>
		<div align="center">
			<form action="/Testing/register" method="post">

				<strong>Full name <font face="Stencil"><br>
						<input type="text" name="fullname" ng-model="fullName" value="">
						<br> </font>User name <br>
					<input type="text" name="username" ng-model="userName" value="">
					<br> Password </strong><font face="Stencil"><br>
					<input type="password" name="password" ng-model="passWord" value=""
						r=""> <br>
					<br> <input type="submit" value="Submit">
				</font>


			</form>
		</div>
	</body>
</html>
