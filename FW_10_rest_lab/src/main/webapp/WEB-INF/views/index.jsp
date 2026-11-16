<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/fragments/header.jsp"%>
    <div class="container">
      <h1>rest client(ajax)</h1>

      <form>
        <input type="text" id="email" value="ajax@ssafy.com" />
        <input type="text" id="name" value="ajax" />
        <input type="text" id="password" value="1234" />
        <button id="btnRegist" type="button">등록</button>
      </form>
      <hr />
      <button id="btnBingImage1">Bing 바탕화면 조회(direct)</button>
      <button id="btnBingImage2">Bing 바탕화면 조회(restClient)</button>
      <hr />
      <div>
        <p>호출 결과:</p>
        <div id="result"></div>
      </div>
    </div>
    <%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
  </body>
  <script src="/js/common.js"></script>
  <script>
    // TODO: 03-1. ajax를 이용해 REST API를 호출하는 모습을 살펴보자.
    document.querySelector("#btnRegist").addEventListener("click", async () => {
      let url = "${root}/api/v1/public/members";
      url = "http://127.0.0.1:8080/api/v1/public/members"
      let html;
      try {
        const response = await fetch(url, {
          method: "post",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            email: document.querySelector("#email").value,
            name: document.querySelector("#name").value,
            password: document.querySelector("#password").value,
          }),
        });
        console.log("성공 여부(2XX)? ", response.ok, response);
        const json = await response.json();
        html = JSON.stringify(json);
      } catch (err) {
        console.error("네트워크에러, CORS 위배 등", err);
        html = JSON.stringify(err);
      } finally {
        document.querySelector("#result").innerHTML = html;
      }
    });
  </script>
  <!-- TODO: 10-1. 다음의 ajax 요청이 CORS 오류를 발생시키는 것을 확인하세요. -->
  <script>
    document
      .querySelector("#btnBingImage1")
      .addEventListener("click", async () => {
        try {
          const url =
            "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=ko-KR";
          console.log(url);
          const response = await fetch(url);
          const json = await response.json();
          document.querySelector("#result").innerHTML = JSON.stringify(json);
        } catch (e) {
          document.querySelector("#result").innerHTML = e.message;
        }
      }); //
  </script>
  <!-- TODO: 10-3. 다음의 ajax 요청이 bing image 정보를 가져오는 것을 확인하세요.  -->
  <script>
    document
      .querySelector("#btnBingImage2")
      .addEventListener("click", async () => {
        try {
          const url = "/api/v1/etc/bing-image";
          const response = await fetch(url);
          const json = await response.json();
          document.querySelector("#result").innerHTML =
            `<img src="https://www.bing.com\${json.images[0].url}" alt="bing image" width="100%"/>`;
        } catch (e) {
          document.querySelector("#result").innerHTML = JSON.stringify(e);
        }
      });
  </script>
</html>
