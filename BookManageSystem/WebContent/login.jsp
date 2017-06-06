<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登陆</title>
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
		<h1>欢迎使用xx图书管理系统，请登陆:</h1>
		<form action="LoginServlet" method="post">  
		    <table>  
		    <tr>  
		    <td width="66" align="right"><font size="3">帐号：</font></td><td colspan="2"><input type="text" name="username" value="${username }" style="width:200;height:25;"/></td>  
		    </tr>  
		    <tr>  
		    <td align="right"><font size="3">密码：</font></td><td colspan="2"><input type="password" name="password"  style="width:200;height:25;"/></td>  
		    </tr>
		    <tr>  
		    <td align="right"><font size="3">验证码：</font></td>  
		    <td width="108" valign="middle"><input type="text" name="verifycode" style="width:100;height:25;"/></td>  
		    <td width="90" valign="middle"><a href="javascript:change()"><img src="VerifyCodeServlet" id="verify" border="0"></a></td>  
		    </tr>  
		    <tr>
		    <td colspan="3" align="center"><input type="submit" value="登录" style="width:100;height:30;"/></td>
		    </tr>
		    </table>
		</form>
		<a href="regist.jsp"><font size="2"><i>没有帐号？点击注册</i></font></a>
		<font color="red" size="2"> ${msg }</font>
	</div>
</center>
</body>
</html>