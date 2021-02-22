<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<h1> 로그인 </h1>
<form action="user-login-process.do" name="user" method="post">
	<p> 아이디 : <input type="text" name = "login_id"></p>
	<p> 비밀번호 : <input type = "password" name = "login_password"></p>
	<p> <input type = "submit" value = "로그인"></p>
</form>
</body>
</html>