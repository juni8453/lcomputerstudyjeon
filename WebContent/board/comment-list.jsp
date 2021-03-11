<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
	<tr>
		<th>번호</th>
		<th>댓글 내용</th>
		<th>등록 날짜</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<c:forEach items="${boardcomment}" var="comment" varStatus="status">
	<%//item - collection 객체, var - 사용할 변수, varStatus - 반복 index 변수 %>
	<!-- ${boardcomment} 객체를 사용 / 객체에 들어가있는 값들을 하나씩 comment에 넣음 -->
		<tr>
			<td>${comment.c_idx}</td>
			<td>${comment.c_content}</td>
			<td>${comment.c_date}</td>	
			<td><button style="margin-left:20px;" class="btn-update">수정</button></td>
			<td><button style="margin-left:20px;">삭제</button></td>
			<!-- 넣은 것들을 comment에 있는 c_idx, c_content, c_date로 뽑아내서 사용함 -->
		</tr>
		<tr style="display: none;" class="tr-reg">
			<td>${comment.c_idx}</td>
			<td><textarea rows="1" cols="60">${comment.c_content}</textarea></td>
			<td>${comment.c_date}</td>	
			<td><button style="margin-left:20px;" class="btn-reg" c_idx="${comment.c_idx}">등록</button></td>
			<td><button style="margin-left:20px;">취소</button></td>
			<!-- 넣은 것들을 comment에 있는 c_idx, c_content, c_date로 뽑아내서 사용함 -->
		</tr>
	</c:forEach>
</table>