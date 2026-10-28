<%@page import="com.ssafy.live.model.dto.Member"%>
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
        <!-- TODO: 12. parameter loginUser에 따라 별도의 화면을 출력하세요. -->
        <!--  값이 있는 경우- 사용자에 대한 welcome message를 출력한다. -->
        <!--  값이 없는 경우- 로그인을 위한 화면을 출력한다. -->

        <!-- END -->
        <hr>

        <h3>회원 등급 확인</h3>
        <%
        int point = 18500;
        int visitCount = 300;
        boolean isPremium = point > 10000 || visitCount > 50;
        %>

        <p>
            포인트:
            <%=point%>, 방문횟수:
            <%=visitCount%>
        </p>
        <p>
            회원등급:
            <!-- TODO: 13. 회원 등급을 포인트와 방문횟수를 기준으로 구분하여 출력하세요. -->
            <!--  VIP(15000 && 100), 골드(10000 || 50), 실버(5000), 일반 회원으로  -->

            <!-- END -->
        </p>

    </div>
</body>


</html>
