<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제</title>
</head>
<body>
<%@ include file="db_connection.jsp" %>

<%
	String idx = request.getParameter("u_idx");
	System.out.println(idx);
	
	PreparedStatement pstmt = null;

    String query = "delete from user where u_idx=?";
   	pstmt = conn.prepareStatement(query);
   	pstmt.setString(1, idx);
   	pstmt.executeUpdate();
%>
	<h2>삭제 완료</h2>
<script>
setTimeout(function () {
	window.location.href = "userlist.jsp";
</script>
</body>
</html>
