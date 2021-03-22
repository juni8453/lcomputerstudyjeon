<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<style>
	table{
		border-collapse:collapse;
		}
		
		table tr th{
			font-weight:700;
		}
		table tr th, table tr td{
			border:10px solid #818181;
			text-align:center;
		}
	textarea{
		resize:none;
		width:500px;
		height:100px;
	}	
		
</style>
<body>
<h1>답글 작성</h1>
<table>
	<tr>
		<th><a href="user-list.do">회원 목록</a></th>
		<th><a href="logout.do">로그아웃</a></th>
		<th><a href="board-list.do">게시판 목록</a></th>
	</tr>
</table>	

	<form action="board-reply-process.do" name="reply" method="post">
		<input type="hidden" name="u_idx" value="${sessionScope.user.u_idx}">
		<input type="hidden" name="b_idx" value="${board.b_idx }">
		<!-- sessionScope.user에 있는 u_idx값을 함께 DB에 보내야하기 때문에 hidden 타입으로 지정해주면 됨 -->
		<div style="width:500px; height:100px; font-size:12px;">
			<textarea name="b_content"></textarea>
		</div>
		<div>
			<p>작성자:${sessionScope.user.u_name}</p>
		</div>
	<p><input type="submit" value="작성하기"></p>
	</form>

</body>
</html>