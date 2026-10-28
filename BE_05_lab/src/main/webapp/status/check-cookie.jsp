<%@ page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
    <%@ include file="/fragments/header.jsp"%>
    <div class="container">
        <div>
            <h1>개별 쿠키 확인(el의 쿠키 내장 객체 활용):</h1>
            <span>for-domain: ${cookie["just-10-min"].value }</span>
        </div>
        <br />
        <h1>cookie 목록</h1>
        <table class="table ">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>값</th>
                </tr>
            </thead>
            <!-- TODO: 04. 서버로 전송된 쿠키의 목록을 출력하세요. -->

            <!-- END -->
            <c:if test="${cookie == null}">
                <tr>
                    <td colspan="3">쿠키가 없어요!</td>
                </tr>
            </c:if>
        </table>
        <a href="http://localhost:8080${root }/main?action=check-cookie">localhost</a>
    </div>


    <%@ include file="/fragments/footer.jsp"%>
</body>
</html>
