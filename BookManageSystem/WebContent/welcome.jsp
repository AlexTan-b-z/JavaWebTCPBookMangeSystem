<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎注册</title>
</head>
<body>
	<center>
	<%if(request.getSession().getAttribute("username") != null) { %>
	<h1>欢迎使用图书管理系统</h1>
	<h2>${msg }</h2>
	<div id="menu">
		<div id="add"><a href="addbook.jsp"><i>增加图书</i></a></div>
		<div id="delete"><a href="deletebook.jsp"><i>删除图书</i></a></div>
		<div id="change"><a href="changebook.jsp"><i>修改图书</i></a></div>
		<div id="find"><a href="findbookServlet"><i>查找图书</i></a></div>
		<div id="out"><a href="LoginOut"><i>退出登陆</i></a></div>
	</div>
	<%} else {%>
	<h1>请登录后再访问此页面</h1>
	<a href="LoginOut"><i>返回登陆</i></a>
	<%} %>
	</center>
</body>
</html>