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
        <h1>쇼핑카트</h1>
        <hr>
        <fieldset class="border border-secondary-subtle">
            <legend>구매 대상 항목</legend>
            <div>
                <!-- TODO: 17-1. session에 cart attribute가 있다면 출력하세요. -->

                <!-- END -->
            </div>
        </fieldset>
        <hr>
        <fieldset class="border border-secondary-subtle">
            <legend>추가로 사고 싶은 품목을 입력하세요</legend>
            <form>
                <label for="addToCart">
                    구매 희망 물품
                    <input type="text" placeholder="입력 후 엔터" name="product" />
                </label>
                <!-- TODO: 17-2. 하나의 form에서 두 개 이상의 action 처리를 확인하세요. -->
                <button formmethod="post" formaction="${root }/main?action=add-to-cart" class="btn btn-primary">카트에 담기</button>
                <button formmethod="post" formaction="${root }/main?action=buy" class="btn btn-primary">구매하고 비우기</button>
            </form>
        </fieldset>
    </div>
    <%@ include file="/fragments/footer.jsp"%>
</body>
</html>
