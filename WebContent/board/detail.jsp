<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	ul{
		width:600px;
		heigh:50px
		margin:10px auto;
	}
	li{
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- jquery script는 </head>위에 씀(먼저 실행되어 생기는 오류방지) -->
</head>
<h1>게시판 상세 내용</h1>
<body>

<div class="flex">
	<div class="label border">제목</div>
	<div class="value border">${board.b_title}</div>
	<!--컨트롤러에 저장되어있는 board값을 setAttribute를 사용하여 뽑아내기 -->
</div>

<div class="flex">
	<div class="label border">내용</div>
	<div class="value border">${board.b_content}</div>
	<!--컨트롤러에 저장되어있는 board값을 setAttribute를 사용하여 뽑아내기 -->
</div>

<div class="flex">
	<div class="label border">날짜</div>
	<div class="value border">${board.b_date}</div>
	<!--컨트롤러에 저장되어있는 board값을 setAttribute를 사용하여 뽑아내기 -->
</div>

<form action="board-comment-process.do" name="comment" method="post">
<input type="hidden" name="b_idx" value="${board.b_idx}">
<input type="hidden" name="u_idx" value="${sessionScope.user.u_idx}">
<!--board에 있는 b_idx값, sessionScope.user에 있는 u_idx 값을 함께 DB에 보내야하기 때문에 hidden 타입으로 
	지정해주면 됨  -->
 
<div class="flex" style="margin-top: 20px; border: 1px solid black; width: 600px;">
	<div style="margin-right: 30px;">${sessionScope.user.u_id}</div>
	<div><textarea name="content" cols="50" rows="2"></textarea></div>
	<div><button style="margin-left: 20px;">등록</button></div>
</div>
</form>

<div id="comment-list">
	<table>
		<tr>
			<th>번호</th>
			<th>댓글 내용</th>
			<th>등록 날짜</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${boardcomment}" var="comment" varStatus="status">
		<!-- item - collection 객체, var - 사용할 변수, varStatus - 반복 index 변수 -->
		<!-- ${boardcomment} 객체를 사용 / 객체에 들어가있는 값들을 하나씩 comment에 넣음 -->
			<tr>
				<td>${comment.c_idx}</td>
				<td>${comment.c_content}</td>
				<td>${comment.c_date}</td>	
				<!-- 넣은 것들을 comment에 있는 c_idx, c_content, c_date로 뽑아내서 사용함 -->
				<td><button style="margin-left:20px;" class="btn-update">수정</button></td>
				<td><button style="margin-left:20px;" class="btn-delete" c_idx="${comment.c_idx}">삭제</button></td>
			</tr>
			<!-- <tr>~</tr> 똑같이 복사 > 붙여넣기 -->
			<tr style="display: none;" class="tr-reg">
				<td>${comment.c_idx}</td>
				<td><textarea rows="1" cols="60">${comment.c_content}</textarea></td>
				<td>${comment.c_date}</td>	
				<td><button style="margin-left:20px;" class="btn-reg" c_idx="${comment.c_idx}">등록</button></td>
				<td><button style="margin-left:20px;">취소</button></td>
			</tr>
		</c:forEach>
	</table>
</div>

<script>
$(document).on('click', '.btn-update', function () {
	$(this).parent().parent().next().show(500);
});

$(document).on('click', '.btn-reg', function () {
	let c_idx = $(this).attr('c_idx');
	let u_idx = '${sessionScope.user.u_idx}';
	let b_idx = '${board.b_idx}';
	let c_content = $(this).parent().prev().prev().find('textarea').val();

	$.ajax({
	  method: "POST",
	  url: "board-comment-update.do",
	  data: {b_idx: b_idx, u_idx: u_idx, c_content: c_content, c_idx: c_idx}
	})
	.done(function( html ) {
	  console.log(html);
	  $('#comment-list').html(html);
	});
});

$(document).on('click', '.btn-delete', function () {
	if (confirm("정말 삭제하시겠습니까?")) {
		let c_idx = $(this).attr('c_idx');
		let b_idx = '${board.b_idx}';
	
		$.ajax({
		  method: "POST",
		  url: "board-comment-delete.do",
		  data: {c_idx: c_idx, b_idx: b_idx}
		})
		.done(function( html ) {
		  $('#comment-list').html(html);
		});
	}
});


	
</script>
</body>
</html>