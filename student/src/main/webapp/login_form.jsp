<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3반에 오신 것을 환영합니다.</title>
</head>
<body>
	<%@ include file="/common/headernav.jsp" %>
	<h1>3반 홈</h1>
	<h3>로그인</h3>
	<form action="${root }/member" method="post">
		<input type="hidden" name="mymenu" value="login">
		<label for="userid">id</label>
		<input type="text" id="userid" placeholder="id를 입력해주세요" name="userid" value="ssafy"> <br/>
		<label for="userpw">pw</label>
		<input type="password" id="userpw" placeholder="pw를 입력해주세요" name="userpw" value="1234"> <br/>
		<input type="checkbox" id="idsave">
		<label for="idsave">id 저장</label> <br/>
		<input type="submit" value="로그인">
<!-- 		<button>로그인2</button> -->
<!-- 		똑같이 동작됨 -->
		<a href="/student/member?mymenu=regist_form">회원가입</a>
	</form>
</body>
</html>
