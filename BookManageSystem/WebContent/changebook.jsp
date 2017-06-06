<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改图书</title>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
	<!-- 可选的Bootstrap主题文件（一般不使用） -->
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
 
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
 
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<center>
	<%if(request.getSession().getAttribute("username") != null) { %>
	<div class="container" id="menu">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3>
					请输入按哪种方式修改图书
				</h3>
				<div class="row clearfix">
					<div class="col-md-4 column">
						 <button type="button" class="btn btn-lg btn-info" id="idBT">编号</button>
					</div>
					<div class="col-md-4 column">
						 <button type="button" class="btn btn-info btn-lg" id="nameBT" >书名</button>
					</div>
					<div class="col-md-4 column">
						 <button type="button" class="btn btn-info btn-lg" id="backBT">返回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container" id="idchange">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form action="changebookServlet" method="post" class="form-horizontal" role="form">
					<div class="form-group">
						 <label for="inputEmail3" class="col-sm-2 control-label">请输入您要修改的书的编号:</label>
						<div class="col-sm-10">
							<input class="form-control" id="inputEmail3" type="text" name="id" />
						</div>
					</div>
					<div class="form-group">
						 <label for="inputPassword3" class="col-sm-2 control-label">请输入您修改后的书名:</label>
						<div class="col-sm-10">
							<input class="form-control" id="inputPassword3" type="text" name="change" />
							<input type="hidden" value="id" name="choice">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							 <button type="submit" class="btn btn-default">提交</button>
						</div>
					</div>
				</form>
				<button class="btn btn-default" id="back1">返回</button>
			</div>
		</div>
	</div>
	
	<div class="container" id="namechange">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form action="changebookServlet" method="post" class="form-horizontal" role="form">
					<div class="form-group">
						 <label for="inputEmail3" class="col-sm-2 control-label">请输入您要修改的书的书名:</label>
						<div class="col-sm-10">
							<input class="form-control" id="inputEmail3" type="text" name="bookname" />
						</div>
					</div>
					<div class="form-group">
						 <label for="inputPassword3" class="col-sm-2 control-label">请输入您修改后的书名:</label>
						<div class="col-sm-10">
							<input class="form-control" id="inputPassword3" type="text" name="change" />
							<input type="hidden" value="bookname" name="choice">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							 <button type="submit" class="btn btn-default">提交</button>
						</div>
					</div>
				</form>
				<button class="btn btn-default" id="back2">返回</button>
			</div>
		</div>
	</div>
	<%} else {%>
	<h1>请登录后再访问此页面</h1>
	<a href="LoginOut"><i>返回登陆</i></a>
	<%} %>
	<font color="red" size="2"> ${msg }</font>
	</center>
<script src="js/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/changebook.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>