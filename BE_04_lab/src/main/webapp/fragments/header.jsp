<!-- /fragments/header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- TODO: 15. bootstrap, core taglib 선언, root 경로 등 공통 모듈을 작성하고 index.jsp에 추가하세요. -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!-- END -->
<div class="container">
    <h1>Welcome to SSAFY</h1>
    <div>
        <!-- TODO: 16. 다음의 링크들을 구성해보자. -->
        <!--  홈으로: / -->
        <a href="${root }">홈으로</a>
        <!--  멤버 목록: /auth?action=member-list-->
        <a href="${root }/auth?action=member-list">멤버 목록</a>
        <!--  멤버 가입: /member?action=member-regist-form-->
        <a href="${root }/member?action=member-regist-form">멤버 가입</a>
        <!--  목록 초기화: /member?action=member-list-reset -->
        <a href="${root }/member?action=member-list-reset">멤버 초기화</a>
        <!-- END -->
    </div>
    <hr />
</div>


<!-- TODO: 17. ${alertMsg}가 있다면 alert으로 출력하자.-->
<!--  index.html의 showAlertMessage를 이동시킨다. -->
<script>
    const showAlertMessage = (msg)=>{
        if(msg &&  msg !='null'){
            alert(msg);
        }
    }
</script>