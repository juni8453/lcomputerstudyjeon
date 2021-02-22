<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>
<%@ include file="db_connection.jsp" %>
	<h1>회원 정보 수정</h1>
	<%
	String idx = request.getParameter("u_idx");
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String query = "select * from user where u_idx=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setString(1,idx);
	
/*	public class MariaDBConnection implements Connection {
		public PreparedStatement prepareStatement(String query) {
			PreparedStatement pstmt = null;
			
			return pstmt;
		}
	}
*/
	
	
	rs = pstmt.executeQuery();
	
	while(rs.next()){
		String u_idx = rs.getString("u_idx");
		String u_id = rs.getString("u_id");
		String u_password = rs.getString("u_pw");
		String u_name = rs.getString("u_name");
		String u_tel = rs.getString("u_tel");
		String[] tel = u_tel.split("-");
		String u_age = rs.getString("u_age");
	%>
	<form action="editProcess.jsp" name="user" method="post">
		<input type="hidden" name="u_idx" value="<%=u_idx%>">
		<p> 아이디 : <input type="text" name="edit_id" value="<%=u_id%>"></p>
		<p> 비밀번호 : <input type="password" name="edit_password" value="<%=u_password%>"></p>
		<p> 이름 : <input type="text" name="edit_name" value="<%=u_name%>"></p>
		<p> 연락처 : <input type="text" maxlengrh="4" size="4" name="edit_tel1" value="<%=tel[0]%>">-
				<input type="text" maxlengrh="4" size="4" name="edit_tel2" value="<%=tel[1]%>">-
				<input type="text" maxlengrh="4" size="4" name="edit_tel3" value="<%=tel[2]%>">
		</p>
		<p> 나이 : <input type = "text" name= "edit_age" value="<%=u_age %>"></p>
		<p> <input type="submit" value="수정완료"></p>
	</form>
	<%
	}
	%>
</body>
</html>