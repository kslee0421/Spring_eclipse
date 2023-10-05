<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript Node2 (각종 입력 사항)</title>
</head>
<body>
<div id ="filediv">
파일첨부 : <input type="file" name="file">
</div>
<input type="button" value="파일첨부 추가" onclick="plus()">
</body>
<script>
var code = document.getElementById("filediv");
function plus(){
	var ea = code.children.length;
	//console.log(ea);
	if(ea < 4){
		var html = document.createElement("input");
		html.type = "file"; //type 설절시 필요
		html.name = "mfile"; //name값 설정
		html.className = "filecss"; //value도 가지고 올수 있다
		//html.value = "mfile"; //value도 가지고 올수 있다
		code.append(html);
	}
}
</script>
</html>