<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/common/headernav.jsp" %>
	<h2>상세 정보</h2>

	<table>
		<tr>
			<th>아이디 </th>
			<td>${student.userid}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${student.userName }</td>

		</tr>
		<tr>
			<th>점수</th>
			<td>${student.score }</td>
		</tr>
		<a href="${root }/member?mymenu=delete&userid=${student.userid}">삭제</a> | 
		<a href="${root }/member?mymenu=update_form&userid=${student.userid}">수정</a>
		
	</table>
</body>
</html>