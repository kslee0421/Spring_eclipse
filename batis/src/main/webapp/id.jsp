<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복찾기</title>
<style>

.btn {
padding:5px 10px;
dosplay : inline-block;
background-color: black;
color: white;
line-height: 30px;
text-align: center;
border-radius:10px;
cursor: pointer;
}
.input {
display: inline-block;
}

</style>
</head>
<body>
<form id="f">
아이디 : <input type="text" name="mid" id="mid" class="input">
<span class="btn" onclick="ajax_load()">중복체크</span>
</form>
</body>
<script>
function ajax_load(){
	var id = document.getElementById("mid");
	
	if(id.value == "") {
		alert("아이디를 입력해 주시길 바랍니다.");	
	}
	else{	
	var http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == XMLHttpRequest.DONE && http.status==200){
			var result = this.response;
			//yes: 중복, no: 가능, error: 오류
			if(result == "no"){
				alert("사용가능한 아이디 입니다.");
				id.readOnly = true;
			}
			else if(result == "yes"){
				alert("다른 아이디를 입력해 주시길 바랍니다.");
			}
			
		}
	}
	//http.open("post","./idcheck.do",true);  //나한테 보내기
	http.open("post","http://192.168.110.218:8080/batis/idcheck.do",true);
	http.setRequestHeader("content-type","application/x-www-form-urlencoded");
	http.send("userid="+id.value);
	}
}
</script>
</html>