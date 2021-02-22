<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입 값 넘겨받기</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String tel1 = request.getParameter("tel1");
	String tel2 = request.getParameter("tel2");
	String tel3 = request.getParameter("tel3");
	String age = request.getParameter("age");
%>

<p> 아이디 : <%=id %></p>
<p> 비밀번호 : <%=password %></p>
<p> 이름 : <%=name %></p>
<p> 연락처 : <%=tel1 %>-<%=tel2 %>-<%=tel3 %></p>
<p> 나이 : <%=age %></p>
</body>
</html>