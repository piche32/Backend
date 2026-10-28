<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div>
        <%
        String root = request.getContextPath();
        %>
        <!--TODO: 10. 구구단 결과를 화면에 반영하세요.-->
		<h1>구구단 <%=request.getParameter("dan") %> 단</h1>
		<%
			List<String> result = (List)request.getAttribute("result");
			for(String row: result) {
				out.println(row+"<br>");
			}
		%>
        <!--END-->
        <a href="<%=root%>/main?action=gugu-form">다시하기</a>
    </div>
</body>
</html>
