<%@page import="com.ssafy.live.model.dto.Member"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="container">
        <c:forEach var="i" begin="0" end="10" step="2">
            <c:set var="sum" value="${sum +i}"></c:set>
        </c:forEach>
        <div>0부터 10까지 짝수의 합 결과 : ${sum }</div>
        <br>

        <%
        String[] strings = { "Hello", "JSTL", "World" };
        %>
        <ul>
            <c:forEach var="item" items="<%=strings%>">
                <li>${item }</li>
            </c:forEach>
        </ul>
        <hr>
        <%
        Map<String, String> map = Map.of("name", "andy", "age", "20");
        request.setAttribute("person", map);
        %>

        <h2>map에서의 데이터 추출</h2>
        <ul>
            <c:forEach var="data" items="${person }" varStatus="status">
                <li>${status.count}번째요소: ${data.key}, ${data.value}</li>
            </c:forEach>
        </ul>
        <hr>
        <%
        List<Member> users = List.of(new Member("a@ssafy.com", "a", "1"), new Member("b@ssafy.com", "b", "2"), new Member("c@ssafy.com", "c", "3"));
        %>
        <!-- TODO: 14. users의 내용을 반복문을 이용해서 출력해보자. -->

        <!-- END -->
    </div>
</body>
</html>
