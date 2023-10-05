<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유미 팝업창</title>
</head>
<body>
<h1>배유미 데이터 출력 리스트</h1>
<!-- <img src="http://192.168.110.217:8080/batis/files/20230926160357_1.jpg" width="200"/> -->
<h2><span onclick="datacall('java')" style="font-weight: bold; cursor: pointer; color:hotpink;">java</span></h2>
<h2><span onclick="datacall('html')" style="font-weight: bold; cursor: pointer; color:blue;">html</span></h2>
<h2><span onclick="datacall('css')" style="font-weight: bold; cursor: pointer; color:gray;">css</span></h2>
</body>
<script>
	function datacall(pname) {
		/* 
		postMessage : 서로 다른 도메인에 한하여 배열 형태로 데이터를 보내는 형식
		postMessage(배열값, "상대방 ip주소 또는 도메인");		
		postMessage(배열값, "*"); //모든 사이트로 접속에 대해서 허용함, 보안때문에 현업에서는 거의 사용하지않음
		
		parent : 자신을 로드한 페이지를 말함
		 */
		//window.opener.f.m.value = "java";
		parent.window.postMessage({msg:pname},"*");
	}
</script>
</html>