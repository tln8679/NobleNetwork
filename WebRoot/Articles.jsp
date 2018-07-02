<%@ page language="java" import="java.util.*,beans.Article"
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

li a:hover:not (.active ) {
	background-color: #111;
}

.active {
	background-color: #4CAF50;
}
</style>

	<head>
		<base href="<%=basePath%>">

		<title>Articles.jsp</title>

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
				<a href="Logout">Log out</a>
			</li>
		</ul>
		<h>
		Posts
		</h>
		<br />
		<table align="center" class="w3-table-all">
			<tr class="w3-light-grey">
				<th>
					Title
				</th>
				<th>
					Post
				</th>
				<th>
					Creator
				</th>
			</tr>
			<%
				ArrayList<Article> articles = (ArrayList) request
						.getAttribute("allArticles");
				if (articles != null) {
			%>
			<b>All user records: </b>
			<%
				Iterator<Article> iterator = articles.iterator();
					while (iterator.hasNext()) {
						Article article = iterator.next();
			%>
			<tr>
				<td><%=article.getTitle()%></td>
				<td><%=article.getPostContent()%></td>
				<td><%=article.getCreator()%></td>
			</tr>

			<%
				}
				}

				else if (articles == null) {
			%>
			<b>None</b>
			<%
				}
			%>
		</table>
		</div>
	</body>
</html>
