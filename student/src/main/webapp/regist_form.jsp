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
	<h3>회원가입</h3>
	<form action="${root }/member" method="post">
		<input type="hidden" name="mymenu" value="regist">
		<label for="userName">name</label>
		<input type="text" id="userName" placeholder="이름을 입력해주세요" name="userName" value="홍길동"> <br/>
		<label for="userid">id</label>
		<input type="text" id="userid" placeholder="id를 입력해주세요" name="userid" value="ssafy"> <br/>
		<label for="userpw">pw</label>
		<input type="password" id="userpw" placeholder="pw를 입력해주세요" name="userpw" value="1234"> <br/>
		<label for="score">score</label>
		<input type="number" id="score" placeholder="점수를 입력해주세요" name="score" value="1234"> <br/>
		<input type="submit" value="회원가입">
<!-- 		<button>로그인2</button> -->
<!-- 		똑같이 동작됨 -->
	</form>
</body>
</html>