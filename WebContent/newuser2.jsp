<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������ �� �Ѱܹޱ�</title>
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

<p> ���̵� : <%=id %></p>
<p> ��й�ȣ : <%=password %></p>
<p> �̸� : <%=name %></p>
<p> ����ó : <%=tel1 %>-<%=tel2 %>-<%=tel3 %></p>
<p> ���� : <%=age %></p>
</body>
</html>