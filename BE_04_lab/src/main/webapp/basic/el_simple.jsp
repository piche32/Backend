<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>simple el</title>
</head>
<body>
    <div class="container">
        <%
    pageContext.setAttribute("name", "hong");
    request.setAttribute("org", "ssafy");
    session.setAttribute("name", "jang");
    application.setAttribute("loc", "seoul");
        %>
        <!-- TODO: 11. 위 코드를 JSTL로 대체하고 context 경로명을 출력해보자. -->
		<c:set var="name" value="hong" scope="page"/>
		<c:set var="org" value="ssafy" scope="request"/>
        <!-- END -->

        <h2>EL 기본</h2>
        <ul>
            <!-- TODO: 08. 원하는 값을 출력해보세요. -->
        <li>hong: <%=pageContext.getAttribute("name") %>, ${name }</li>
        <li>jang: ${sessionScope.name }</li>
        <li>ssafy: ${org }</li>
        <li>addr: ${addr }</li>

            <!-- END -->
        </ul>
    </div>
</body>
<script>
    let org = "${org}"
    let message = "I am in " + org;
    console.log(message);
</script>

<!-- TODO: 09. ``를 이용해서 "message: I am in SSAFY" 형태로 출력해보자. -->
 <script>
 	let message2 = `Message: \${message}` //js의 template 문자열
	console.log(message2)
</script>

<!-- END -->
</html>
