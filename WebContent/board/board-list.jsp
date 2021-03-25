<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
	h1{
		text-align:center;
	}
	table{
		border-collapse:collapse;
		magin:40px auto;
	}
	table tr th{
		font-weight:700;
	}
	table tr td, table tr th{
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
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
	body{
		margin:0;
		padding:0;
	}
	div:nth-child(1){
		background-color:rgba(75,189,217,0.1);
		padding:10px 30px;
		font-size:1.2rem;
		font-weight:700;
	}
	div:nth-child(2){
		width:200px;
	}
	div ul{
		width:100%;
		text-align:center;
		list-style:none;
		padding:0;
	}
	div ul li{
		padding:10px;
		font-size:1rem;
		background-color:rgba(75,189,217,0.1);
		border-radius:10px;
		margin:10px;
		font-weight:700;
		box-shadow:2px 3px 3px #bbbbbb;
	}
	div ul li a{
		text-decoration:none;
		color:#333333;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- jquery script는 </head>위에 씀(먼저 실행되어 생기는 오류방지) -->
</head>

<body>
<h1>게시판 목록</h1>
<form action="board-list.do" name="user" method="post">
	<select name="select">
		<option value="none">=== 선택 ===</option>
		<option value="all">통합검색</option>
		<option value="b_title">제목</option>
		<option value="b_content">내용</option>
		<option value="u_id">닉네임</option>
	</select>
<p><input type = "text" name = "keyWord" value="${keyWord }"><input type = "submit" value = "검색"></p>
</form>
<table>
	<tr>
		<th><a href="user-list.do">회원 목록</a></th>
		<th><a href="logout.do">로그아웃</a></th>
		<th><a href="board-write.do">게시판 글 작성</a></th>
		<th><a href="board-list.do">게시판 목록</a></th>
	</tr>
</table>		
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>내용</th>
		<th>글쓴이</th>
		<th>날짜</th>
		<th>조회수</th>
		<th>답글작성</th>
	</tr>
	<c:forEach items="${boardlist}" var="boardlist" varStatus="status">
	<%//item - collection 객체, var - 사용할 변수, varStatus - 반복 index 변수 %>
		<tr>
			<td><a href = "board-detail.do?b_idx=${boardlist.b_idx}">${boardlist.b_idx}</a></td>
			<%//값 넘길 때 링크 적고 ? 붙여서 뒤에 적어줌 %>
			<td style="text-align: left;">${boardlist.b_title}</td>
			<td>${boardlist.b_content}</td>
			<td>${boardlist.user.u_id}</td>
			<td>${boardlist.b_date}</td>
			<td>${boardlist.b_views}</td>
			<td><a href = "board-reply.do?b_idx=${boardlist.b_idx }&b_group=${boardlist.b_group }&b_depth=${boardlist.b_depth}&b_order=${boardlist.b_order}"><button style="margin-left:20px;">답글작성</button></a></td>
			<!-- 답글작성 버튼 클릭시 b_idx, b_depth 값이 넘어가도록 설정 -->
		</tr>
	</c:forEach>
</table>
<div>
	<ul>
		 <c:choose>
			<c:when test="${ pagination.page > pagination.pageUnit }">
				<li>
					<a href="board-list.do?page=${ pagination.prevPage }&keyWord=${keyWord}&select=${select}">◀</a>
				</li>
			</c:when>
		</c:choose> 
		<c:forEach var="i" begin="${ pagination.startPage }" end="${ pagination.endPage }" step="1">
			<c:choose>
				<c:when test="${ pagination.page == i }">
					<li style="background-color:#ededed;">
						<span>${i}</span>
					</li>
				</c:when>
				<c:when test="${ pagination.page != i }">
					<li>
						<a href="board-list.do?page=${i}&keyWord=${keyWord}&select=${select}">${i}</a>
					</li>
				</c:when>
			</c:choose>
		</c:forEach>
		 <c:choose>
			<c:when test="${ pagination.endPage < pagination.lastPage }">
				<li style="">
					<a href="board-list.do?page=${ pagination.nextPage }&keyWord=${keyWord}&select=${select}">▶</a>
				</li>
			</c:when>
		</c:choose> 
	</ul>
</div>
</body>
</html>