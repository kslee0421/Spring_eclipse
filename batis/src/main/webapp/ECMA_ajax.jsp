<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA 전용 - Ajax 사용법</title>
</head>
<body>
<input type="button" value="클릭" id="btn"><br>
<input type="text" name="mid"><br>
<input type="button" value="클릭2" id="btn2"><br>
</body>
<script>
	document.querySelector("#btn").addEventListener("click",function(){
		//Ajax get통신
		fetch("./news.json") //fetch 라는 함수 (ajax통신 함수) 
		.then(function(aa){  //해당 파일을 통신을 통하여 배열로 변환작업
			return aa.json();
		}).then(function(bb){ //try 데이터 출력 
			console.log(bb);
		}).catch(function(error){ //error 예외처리로 데이터 에러 발생
			console.log("Data Error!!");
		});
		//console.log("test");
	});
	
	document.querySelector("#btn2").addEventListener("click",function(){
		this.mid = document.querySelector("#mid").value;
		fetch("./ajax.do",{
			method:"POST",
			cache:"no-cache",
			headers:{
				"Content-Type":"application/x-www-form-urlencoded"
			},
			body: JSON.stringify({
				userid:this.mid
			})
		})
		.then(function(aa){  //해당 파일을 통신을 통하여 배열로 변환작업
			return aa.json();
		}).then(function(bb){ //try 데이터 출력 
			console.log(bb);
		}).catch(function(error){ //error 예외처리로 데이터 에러 발생
			console.log("Data Error!!");
		});
		
		console.log(mid);
	});
</script>
</html>