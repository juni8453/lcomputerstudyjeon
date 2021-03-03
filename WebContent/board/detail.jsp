<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
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
	
	.flex {
		display: flex;
	}
	
	.border {
		border: 1px solid black;
	}
	
	.label {
		width: 100px; text-align: right;
	}
	
	.value {
		width: 200px; text-align: left; padding-left: 10px;
	}
</style>
</head>
<body>

<div class="flex">
	<div class="label border">제목</div>
	<div class="value border">${board.b_title}</div>
	<%//컨트롤러에 저장되어있는 board값을 setAttribute를 사용하여 뽑아내기 %>
</div>
<div class="flex">
	<div class="label border">내용</div>
	<div class="value border">${board.b_content}</div>
	<%//컨트롤러에 저장되어있는 board값을 setAttribute를 사용하여 뽑아내기 %>
</div>
<div class="flex">
	<div class="label border">날짜</div>
	<div class="value border">${board.b_date}</div>
	<%//컨트롤러에 저장되어있는 board값을 setAttribute를 사용하여 뽑아내기 %>
</div>

</body>
</html>