<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>增加图书</title>
</head>
<body>
<center>
	<%if(request.getSession().getAttribute("username") != null) { %>
	<form action="addbookServlet" method="post" enctype="multipart/form-data">
		请输入书名:<input type="text" name="bookname"></br>
		请输入作者:<input type="text" name="author"></br>
		请输入价格:<input type="text" name="price"></br>
		请选择电子书文件:<input type="file" name="file"></br>
		<input type="submit" value="增加">
		<a href="welcome.jsp">返回菜单</a></br>
		<font color="red" size="2"> ${msg }</font>
	</form>
	<%} else {%>
	<h1>请登录后再访问此页面</h1>
	<a href="LoginOut"><i>返回登陆</i></a>
	<%} %>
</center>

</body>
</html>