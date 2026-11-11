<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="jakarta.tags.core" %>
        <c:set var="root" value="${pageContext.servletContext.contextPath }"></c:set>
        <link rel="stylesheet" href="${root }/css/common.css">

        <!-- END -->
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Insert title here</title>
        </head>

        <body>
            <h1>Hello Spring MVC</h1>
            <hr />
            <!-- TODO: 05-2. /simple/echo를 호출하고 반환값을 확인해보자. -->
            <form action="${root }/simple/echo">
                <input type="text" name="msg" />
                <output>${requestScope.msg }</output>
                <br />
                <button>echo</button>
            </form>

            <hr />
            <!-- TODO: 05-4. /simple/add를 호출하고 반환값을 확인해보자.-->

            <!-- END -->
            <hr />
            <!-- TODO: 08-1. /simple/regist를 호출해서 회원가입하고 바로 로그인 해보자. -->
            <form action="${root }/simple/regist" method="post">
                <input type="text" name="id" placeholder="아이디" />
                <input type="password" name="pass" placeholder="비밀번호" />
                <fieldset>
                    <legend>취미</legend>
                    <label>
                        <input type="checkbox" name="hobby" value="운동" />
                        운동
                    </label>
                    <label>
                        <input type="checkbox" name="hobby" value="독서" />
                        독서
                    </label>
                    <label>
                        <input type="checkbox" name="hobby" value="게임" />
                        게임
                    </label>
                </fieldset>
                <button>regist</button>
            </form>
            <div>session에 저장된 객체: ${loginUser }</div>
            <hr />
            <!-- TODO: 10-1. /simple/redir-attr를 호출해서 flash/session 정보를 확인해보자. -->
            <h3>flash/session 정보 확인</h3>
            <a href="${root }/simple/redir-attr">정보 확인</a>
            <ul>
                <li>bySession: ${sessionScope.bySession}</li>
                <li>byModel: ${requestScope.byModel}</li>
                <li>byFlash: ${requestScope.byFlash }</li>
                <li>byParam: ${param.byParam }</li>
            </ul>
            <h1>예외 처리</h1>
            <a href="http://localhost:8080/member/login">404</a>
            <br>
            <a href="http://localhost:8080/simple/regist">405</a>
            <br>
            <a href="http://localhost:8080/simple/problem">500</a>
            <br>
            <hr>
            <h1>파일 업로드</h1>
            <!-- TODO: 12-1. /simple/fileupload를 호출해서 파일 업로드/다운로드 해보자. -->
            <form action="${root }/simple/fileupload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" accept="image/*" id="file">
                <input type="text" name="desc" id="desc" placeholder="파일 설명">
                <button>전송</button>
                <button type="button" id="btn-upload">전송(AJAX)</button>
            </form>

            <div style="display: flex; justify-content: space-between;">
                <div>
                    <h4>일반</h4>
                    <span>desc: ${desc }</span> <br>
                    <a href="${root }/upload/${fileName }" download="${fileName }">
                        <!--<img src="${root }/upload/${fileName }"  style="width: 100%" /> -->
                    </a>
                </div>
                <div>
                    <h4>Base64</h4>
                    <span>desc: ${desc }</span> <br>
                    <a href="${base64 }" download="${fileName }">
                        <img src="${base64 }" style="width: 100%" />
                    </a>
                </div>
                <div>
                    <h4>AJAX</h4>
                    <span id="ajax-desc"></span> <br>
                    <a href="${base64 }" download="${fileName }">
                        <img id="ajax-img" src="${base64 }" style="width: 100%" />
                    </a>
                </div>
            </div>
        </body>
        <script>
            document.querySelector("#btn-upload").addEventListener("click", async () => {
                const formData = new FormData();
                const file = document.querySelector("#file").files[0];
                const desc = document.querySelector("#desc").value;
                formData.append("file", file);
                formData.append("desc", desc)
                try {
                    const response = await fetch("${root}/simple/fileupload-ajax", { method: "post", body: formData })
                    const json = await response.json();
                    console.log("profile upload success", json);
                    document.querySelector("#ajax-img").src = json.base64;
                } catch (e) {
                    console.log("profile upload fail", e)
                }
            })

        </script>

        </html>
