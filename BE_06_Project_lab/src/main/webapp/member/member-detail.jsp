<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#profile {
 max-width: 150px;
}

th:nth-child(1) {
 width: 80px;
}

th:nth-child(2) {
 width: 50%;
}

th:nth-child(4) {
 width: 75px;
}

input[type='text'] {
 width: 100%;
}

#map {
 display: none;
 width: 100%;
 height: 300px;
}
</style>
</head>
<body>
    <%@ include file="/fragments/header.jsp"%>
    <div class="container">
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-5">
                    <div class="card mb-4">
                        <div class="card-header">
                            <h4>회원 정보</h4>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <p>
                                        <strong>회원번호:</strong> ${member.mno}
                                    </p>
                                    <p>
                                        <strong>이름:</strong> ${member.name}
                                    </p>
                                    <p>
                                        <strong>이메일:</strong> ${member.email}
                                    </p>
                                    <p>
                                        <strong>권한:</strong> ${member.role}
                                    </p>
                                </div>
                            </div>
                            <!-- TODO: 15. 회원의 정보가 로그인한 사용자거나 admin role일 경우 div를 표현해보자.-->
                             <c:if test="true">

                            <!--END-->
                                <div class="d-flex mt-5 justify-content-center">
                                    <form method="post" action="${root}/auth" id="form-delete">
                                        <input type="hidden" name="action" value="member-delete">
                                        <input type="hidden" name="email" value="${member.email }">
                                        <button type="button" class="btn btn-primary mx-3" onclick="memberModify()">수정</button>
                                        <button type="button" class="btn btn-danger  mx-3" onclick="memberDelete()">삭제</button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${!empty error }">
            <div class="alert alert-danger" role="alert">${error }</div>
        </c:if>
    </div>
    <%@ include file="/fragments/footer.jsp"%>
</body>


<!-- TODO: 16. 수정 버튼 클릭 시 동작을 확인하세요. -->
<script>
const memberModify = ()=>{
    location.href="${root}/auth?action=member-modify-form&email=${member.email}"
};
</script>

<!-- TODO: 19. 삭제 버튼 클릭 시 동작을 처리하세요.-->
<!--  의도를 다시 한번 확인하고 맞다면 제출-->
 <script></script>

<!-- END -->
</html>
