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
<body>

<div class="flex">
	<div class="label border">이름</div>
	<div class="value border">${user.u_name}</div>
</div>
<div class="flex">
	<div class="label border">이름</div>
	<div class="value border">${user.u_age}</div>
</div>

</body>
</html>