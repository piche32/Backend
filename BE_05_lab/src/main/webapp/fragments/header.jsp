<!-- /fragments/header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<div class="container">
    <h1>Welcome to SSAFY</h1>
    <div>
        <!-- TODO: 08. 세션 값(loginUser) 여부에 따라 달라지는 메뉴 구성을 확인하자.  -->
        <span>
            <a href="${root }">홈으로</a>
        </span>
        |
        <c:if test="${empty loginUser }">
            <span>
                <a href="${root }/member?action=login-form">로그인</a>
            </span> |
            <span>
                <a href="${root }/member?action=member-regist-form">회원 가입</a>
            </span>  |
            <span>
                <a href="${root }/member?action=member-list-reset">목록 초기화</a>
            </span>

        </c:if>
        <c:if test="${!empty loginUser }">
            <span>
                <a href="${root }/auth?action=member-list">멤버 목록</a>
            </span>

            |
            <span>
                <a href="#">${loginUser.name }</a>
            </span> |
            <span>
                <a href="${root }/member?action=logout">로그아웃</a>
            </span> |
        </c:if>
    </div>
    <hr />
</div>
<script>
const showAlertMessage = (msg) => {
    if (msg && msg != "null") {
        alert(msg);
    }
}

    showAlertMessage(`${alertMsg}`)
    /*
       <c:remove var="alertMsg" scope="session"/>
     */
</script>
