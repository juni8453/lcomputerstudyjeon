<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
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
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
	}
</style>
<body>
<%@ include file="db_connection.jsp" %>
	<h1>회원 상세페이지</h1>
	<table>
	<%
		String idx = request.getParameter("u_idx");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	    String query = "select * from user where u_idx=?";
	   	pstmt = conn.prepareStatement(query);
	   	pstmt.setString(1, idx);
	   	
	    rs = pstmt.executeQuery();
	
	    while(rs.next()){     
	   	       String u_idx = rs.getString("u_idx");
	           String u_id = rs.getString("u_id");
	           String u_name = rs.getString("u_name");
	           String u_tel = rs.getString("u_tel");
	           String u_age = rs.getString("u_age");
	  %>
	  <tr>
			<td>회원 번호</td>
			<td><%=u_idx %></td>		
		</tr>
		<tr>
			<td>회원 ID</td>
			<td><%=u_id %></td>		
		</tr>
		<tr>
			<td>회원 이름</td>
			<td><%=u_name %></td>		
		</tr>
		<tr>
			<td>회원 전화번호</td>
			<td><%=u_tel %></td>		
		</tr>
		<tr>
			<td>회원 나이</td>
			<td><%=u_age %></td>		
		</tr>
		
		<tr style="height:50px;">
			<td style="border:none;">
				<a href="userEdit.jsp?u_idx=<%=u_idx%>" style="width:70%;font-wight:700;background-color:#818181;color:#fff">수정</a>
		<td style="border:none;">
			<a href="userDelete.jsp?u_idx=<%=u_idx%>" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>
		</td>	
			
		</tr>		
	<%
	    }
	%>
		
	</table>
</body>
</html>