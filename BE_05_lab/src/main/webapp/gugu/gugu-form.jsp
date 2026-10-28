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
      <h1>알고싶은 단을 입력하세요.</h1>
      <form action="${root}/main">
        <input type="hidden" name="action" value="gugu" />
        <input type="number" name="dan" placeholder="단을 입력하세요" />
        <button>알려줘</button>
      </form>
      <br />
      <!-- TODO: 06. recentGugu 쿠키가 있다면 바로가기를 제공해보자. -->
      <c:if test="${!empty cookie.recentGugu }">
        <fieldset>
          <legend>최근에 찾아본 구구단</legend>
          <c:forTokens items="${cookie.recentGugu.value }" delims="-" var="item">
            <a href="${root }/main?action=gugu&dan=${item}">${item }</a>
          </c:forTokens>
        </fieldset>
      </c:if>
    </div>
    <%@ include file="/fragments/footer.jsp"%>
  </body>
</html>
