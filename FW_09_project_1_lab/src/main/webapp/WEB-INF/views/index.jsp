<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/fragments/header.jsp"%>
    <div class="container">
      <h1>도움말 페이지</h1>
      <ul>
        <li><a href="${root }/auth/help">Auth 도움말</a></li>
      </ul>
      </div>
    <%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
  </body>
  <script src="/js/common.js"></script>
</html>
