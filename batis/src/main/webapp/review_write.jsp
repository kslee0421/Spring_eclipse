<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리뷰 간편 등록 페이지</title>
</head>
<body>
<form id="f" mrthod="post" action="./data_insert.do">
고객명: <input type="text" name="rname"><br>
패스워드: <input type="password" name="rpass"><br>
리뷰내용: <textarea cols="200" rows="4" name="rtext"></textarea><br>
<input type="submit" value="리뷰등록">
</form>
</body>

<script>
document.querySelector("f").addEventListener("submit",function(s){
	s.preventDefault();
	if(f.rpass.value==""){
		alert("패스워드를 입력하세요");
	}
	else{
		f.submit();
	}
});
</script>
</html>