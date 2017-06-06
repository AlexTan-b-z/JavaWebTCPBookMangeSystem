<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎注册</title>
	<script type="text/javascript">  
      function change(){  
          var img =document.getElementById("verify");  
          img.src="VerifyCodeServlet?a="+new Date().getTime();  
      }  
    </script>
</head>
<body>
<center>  
   <div>  
   <h1>欢迎注册</h1>  
   <form action="RegistServlet" method="post">
    请输入帐号：<input type="text" name="username"><br/>  
    请输入密码：<input type="password" name="password"><br/>  
    请确认密码：<input type="password" name="rpsw"><br/>  
    请输入验证码：<input type="text" name="verifycode" style="width:66px; text-align:left" />
   <a href="javascript:change()"><img src="VerifyCodeServlet" id="verify" border="0"></a></br>
   <input type="submit" value="注册">
   </form>
   <font color="red" size="2"> ${msg }</font>
   <a href="login.jsp"><font size="2"><i>返回登陆</i></font></a>
   </div>  
</center> 
</body>
</html>