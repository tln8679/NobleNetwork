<%@ page language="java" import="java.util.*,beans.User,beans.Article"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<style>
.first {
	width: 70%;
	height: 300px;
	position: absolute;
	border: 1px solid red;
}

.second {
	border: 2px solid blue;
	width: 40%;
	height: 200px;
	position: relative;
	top: 315px;
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

		<title>My Info</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


	</head>

	<body>
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
		<div class="w3-container" class="first">
			<div align="right">


				</font>
			</div>
			<h1>
				My Information
			</h1>
			<table align="center" class="w3-table-all">

				<tr class="w3-light-grey">
					<th>
						Username
					</th>
					<th>
						Password
					</th>
					<th>
						Full name
					</th>
					<th>
						ID
					</th>
				</tr>
				<%
					ArrayList<User> records = (ArrayList) request
							.getAttribute("records");
					if (records != null) {
				%>
				<b>All user records: </b>
				<%
					Iterator<User> iterator = records.iterator();
						while (iterator.hasNext()) {
							User user = iterator.next();
				%>
				<tr>
					<td><%=user.getUserName()%></td>
					<td><%=user.getPassWord()%></td>
					<td><%=user.getFullName()%></td>
					<td><%=user.getId()%></td>
				</tr>

				<%
					}
					}

					else if (records == null) {
				%>
				<tr>
					<td>
						${username}
					</td>
					<td>
						${password}
					</td>
					<td>
						${fullname}
					</td>
					<td>
						${id }
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>

		<br />

		<div class="w3-container" class="second">
			<div align="center">
				<h2>
					What's up ${fullname}?
				</h2>
				<form action="/Testing/NewPost" method="post" id="postform"
					name="postform">
					Title:
					<input type="text" name="title">
					<br>
					<br>

					<textarea name="postcontent" form="postform" class="form-control"
						rows="4" cols="50"></textarea>
					<!--  <input type="file" name="picture" /> -->

					<br>
					<input type="hidden" name="username" value=${username } } />
				
					<input type="submit" value="Post" />


					<br>
					<br>
				</form>
				<a href="/Testing/NewPost">View Posts</a>
			</div>
		</div>

	</body>
</html>
