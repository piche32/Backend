<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="/fragments/header.jsp"%>
    <div class="container">
        <h1>${param.dan }단결과</h1>
        <table>
            <tbody>
                <c:forEach var="item" items="${result }">
                    <tr>
                        <td>${item }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <a href="${root }/main?action=gugu-form">다시하기</a>
    </div>
    <%@ include file="/fragments/footer.jsp"%>
</body>
</html>
