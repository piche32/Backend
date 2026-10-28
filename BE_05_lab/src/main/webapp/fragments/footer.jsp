<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="container">
    <hr />
    SSAFY since 2018! <span id="timeout"></span>
</div>

<script>
let remain = 60*1;
setInterval(()=>{
    document.querySelector("#timeout").innerHTML = remain--;
}, 1000);
</script>
