<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="container">
        <h2>el의 연산자</h2>
        <%
        request.setAttribute("num1", "10");
        request.setAttribute("num2", 20);
        request.setAttribute("arr", new ArrayList<>());
        %>

        <ul>
            <li>문자열 결합 연산 : ${num1 + num2 }</li>
            <li>나누기 연산 : ${num1 / num2 }</li>
            <li>비교연산 : ${num1 > 9 }</li>
            <li>empty : ${empty num1 }, ${ empty arr }</li>
            <li>not empty : ${!empty num1 }</li>
        </ul>
    </div>
</body>
</html>
