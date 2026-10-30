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
	<h2>회원목록</h2>
	<c:forEach var="student" items="${list }">
		id : ${student.userid} </br>
		이름 : ${student.userName} </br>
		점수 : ${student.score} </br>
		<hr>
	</c:forEach>
	<a href="/student/member?mymenu=regist_form">회원가입</a>
</body>
</html>