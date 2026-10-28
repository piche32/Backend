<%@page import="java.util.List"%>
<%@page import="com.ssafy.live.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="container">
        <%
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        Member user = new Member(name, email, pass);

        request.setAttribute("user", user);

        List<String> friends = List.of("hong", "jang", "lim");
        request.setAttribute("friends", friends);
        %>

        <ul>
            <!-- TODO: 10. param 정보와 user의 정보를 출력해보자. -->
            <!--  param을 통해 name, email을 사용해보자. -->
            <!--  request scope의 user 정보가 가진 name, email 속성을 확인해보자. -->
            <!--  header 정보 중 accept-language를 출력해보자. -->
            <!--  friends가 몇 명인지 출력해보자. -->

            <!-- END -->
        </ul>
    </div>
</body>
</html>
