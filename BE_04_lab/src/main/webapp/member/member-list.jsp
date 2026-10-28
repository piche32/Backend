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
        <h1>멤버 목록</h1>
        <table class="table">
            <tbody>
                <tr>
                    <td>no</td>
                    <td>name</td>
                    <td>email</td>
                </tr>
                <c:forEach items="${members }" var="item">
                    <tr>
                        <td>${item.mno }</td>
                        <td>${item.name }</td>
                        <td><a href="${root }/auth?action=member-detail&email=${item.email}">${item.email }</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
    <%@ include file="/fragments/footer.jsp"%>
</body>
</html>
