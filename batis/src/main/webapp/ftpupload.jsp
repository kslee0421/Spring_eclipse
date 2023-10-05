<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CDN 서버로 이미지 전송</title>
</head>
<body>
<form id="f" method="post" action="./ftpcon.do" enctype="Multipart/form-data">
파일전송 : <input type="file" name="mfile"> <br>
<input type="button" value="전송" id="btn">
</form>
</body>
<script>
	document.querySelector("#btn").addEventListener("click",function(){
		f.submit();
	});
</script>
</html>