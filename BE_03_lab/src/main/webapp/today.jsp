<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- script-let: 자사 실행문을 작성한다. --%>
	<%
		LocalDateTime now = LocalDateTime.now();
		out.append("이게 뭐야: " + now);
	%>
	
	<%-- expression: 출력 --%>
	<%=prettyNow(now) %>
	
	<%--declaration: member 선언 --%>
	<%!
		private String prettyNow(LocalDateTime now) {
			return "오늘은: " +now;
	}
	%>
</body>
</html>