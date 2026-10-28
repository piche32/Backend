<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@include file="/fragments/header.jsp"%>
    <div class="container">
        <h1>회원가입</h1>
        <form action="${root }/member" method="post" class="m-3">
            <input type="hidden" name="action" value="member-regist" />

            <div class="mb-3 row">
                <label for="name" class="col-sm-2 col-form-label">이름</label>
                <div class="col-sm-10">
                    <input type="text" name="name" id="name" class="form-control"
                        required />
                </div>
            </div>

            <div class="mb-3 row">
                <label for="email" class="col-sm-2 col-form-label">이메일</label>
                <div class="col-sm-10">
                    <input type="email" name="email" id="email" class="form-control"
                        required />
                    <!-- TODO: 04. 오류 정보를 표시하기 위한 div를 확인해보자. -->
                    <div class="invalid-feedback">이미 사용중인 email입니다.</div>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="password" class="col-sm-2 col-form-label">비밀번호</label>
                <div class="col-sm-10">
                    <input type="password" name="pass" id="pass" class="form-control"
                        required />
                </div>
            </div>

            <button type="submit" class="btn btn-primary">등록</button>
        </form>
        <%
        Object error = request.getAttribute("error");
        if (error != null) {
            out.println("<div class='alert alert-danger' role='alert'>" + error + "</div>");
        }
        %>

        <%@include file="/fragments/footer.jsp"%>
    </div>
</body>
<!-- TODO: 05. #email에서 input event 발생 시 중복 여부를 확인(/member?action=email-check)하세요.-->
<!--  상황에 따라 is-valid와 is-invalid를 추가/삭제한다. -->
<!--  참조: https://getbootstrap.com/docs/5.3/forms/validation/#server-side -->
 <script></script> 

<!-- END -->
</html>
