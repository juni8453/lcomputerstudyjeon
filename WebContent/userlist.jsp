<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<style>
	table {
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
</style>
<body>
<%@ include file="db_connection.jsp" %>
	<h1>회원 목록</h1>
	<table >
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
		</tr>
		<%
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String query = "select*from user";
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String u_idx = rs.getString("u_idx");
				String u_id = rs.getString("u_id");
				String u_name = rs.getString("u_name");
		%>
		<tr>
		 	<td><a href="userDetail.jsp?u_idx=<%=u_idx%>"><%=u_idx %></a></td>
		 	<td><%=u_id%></td>
		 	<td><%=u_name%></td>
		</tr>
		

		<%
			}
			rs.close();
			pstmt.close();
			conn.close();
		%>
	</table>
</body>
</html>