<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <%@ include file="/fragments/header.jsp" %>
    <div class="container">

        <h1>회원 가입 처리</h1>
        <!-- TODO: 03. 회원 가입을 요청하는 링크(action=member-regist-form)를 만들어보자. -->
		<a href="<%=request.getAttribute("root")%>/member?action=member-regist-form">회원가입</a>
        <!-- END -->
        <h2>EL Test</h2>
        <ul>
            <li><a href="<%=request.getAttribute("root")%>/basic/el_simple.jsp">el simple</a></li>
            <li><a href="${root }/basic/el_object.jsp?email=admin@ssafy.com&name=ssafy&pass=1234">el object</a></li>
            <li><a href="${root }/basic/el_operator.jsp">el operator</a></li>
        </ul>

        <h2>JSTL Test</h2>
        <ul>
            <li><a href="${root }/basic/jstl_if.jsp">jstl if</a></li>
            <li><a href="${root }/basic/jstl_foreach.jsp">jstl foreach</a></li>
        </ul>
    </div>
     <%@ include file="/fragments/footer.jsp" %>
</body>
<!-- TODO: 05. 전달된 alertMsg 처리해보자. -->
 <script>
 	showAlertMessage('<%=request.getAttribute("alertMsg")%>')
 </script> 

<!-- END -->
</html>
