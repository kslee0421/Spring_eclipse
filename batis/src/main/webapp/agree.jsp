<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용약관 샘플 페이지</title>
<style>
.agree{
width:600px; 
height:150px;
border:1px solid black;
overflow-y: auto;
overflow-x:none; 
}
</style>
</head>
<body>
<p>이용약관</p>
<div class="agree" id="agree1"></div>
<p>개인정보 약관</p>
<div class="agree" id="agree2"></div>
</body>
<script>
var agree =function(f){	
var http =new XMLHttpRequest(); // 통신할거다

//async : true(비동기), false(동기)
//open(method,urlmasync,user,password)
http.open("GET",f,false); //false : 동기
http.send();
//console.log(http);
//console.log(http.responseText);
//console.log(http.response);
return http.response;
}
//agree();
document.getElementById("agree1").innerHTML = agree("./agree1.txt");
document.getElementById("agree2").innerHTML = agree("./agree2.txt?v=1");
console.log(agree());
</script>
</html>










