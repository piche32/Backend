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
	<br> 
	id : ${student.userid} <br> 
	이름 : ${student.userName} <br> 
	점수 : ${student.score} <br> 
</body>
</html>