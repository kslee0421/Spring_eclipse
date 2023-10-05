<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품리스트 선택 출력파트</title>
</head>
<body>

<form id="f">
<input type="text" name="m" id="m">
<input type="button" value="상품추가" onclick="popup()">
</form>

</body>
<script>
function popup(){
	//자신의 임의 파일을 이용하여 팝업을 띄움 (load.jsp)
	window.open("./load.jsp","","width=500 height=500");
	///window.open("http://192.168.110.217:8080/batis/popup.jsp","","width=500 height=500");  //CORS 걸림
}
</script>
</html>