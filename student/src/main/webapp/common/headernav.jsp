<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath }"> </c:set>
<c:if test="${empty loginStudent}">
	<span>
	<a href="/student/member?mymenu=login_form">로그인</a>
	</span>
	
	<span>
	<a href="/student/member?mymenu=regist_form">회원가입</a>
	</span>
</c:if>

<c:if test="${!empty loginStudent}">
	[${loginStudent }] 님 안녕하세요.
	<span>
	<a href="/student/member?mymenu=logout">로그아웃</a>
	</span>
</c:if>

<script>
	const showAlertMessage = (msg) => {
		if(msg && msg != "null") {
			alert(msg);
		}
	}
	
	showAlertMessage(`${alertMsg}`);
</script>