<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
<body>
<h1>게시판 글 작성</h1>

<form action="board-write-process.do" name="write" method="post">
	<input type="hidden" name="u_idx" value="${sessionScope.user.u_idx}">
	<!-- sessionScope.user에 있는 u_idx값을 함께 DB에 보내야하기 때문에 hidden 타입으로 지정해주면 됨 -->
	<p>제목:<input type="text" name="title"></p>
	<p>내용:<input type="text" name="content"></p>
	<p>작성자:${sessionScope.user.u_name}</p>
	<p><input type="submit" value="작성하기"></p>	
</form>
</body>
</html>