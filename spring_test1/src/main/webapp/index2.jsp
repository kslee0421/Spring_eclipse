<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>페이지2 접근형태 -sessionStorage +get 파라미터</title>
</head>
<body>
페이지3</br>
chrome, safari, firefox => 보안이슈
자신의 창을 강제 종료(X)
<input type="button" value="클릭" onclick="abc()">
</body>
<script>
	var today = "";
	var data = "";

	today = Date.now();
	data = sessionStorage.setItem("time",today);
	
	function abc(){
		window.open("./index3.jsp?days="+today,"","");
		location.href="./login.html";
		//console.log("test");
	}
</script>
</html>