<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA5~6 Node3 (오브젝트의 노드번호 체크)</title>
</head>
<body>
<ul id="menu">
	<li>메뉴1</li>
	<li>메뉴2</li>
	<li>메뉴3</li>
	<li>메뉴4</li>
	<li>메뉴5</li>
</ul>
<ol id="menu2">
	<li>menu1</li>
	<li>menu2</li>
	<li>menu3</li>
	<li>menu4</li>
	<li>menu5</li>
</ol>
</body>
<script>
var node2 = document.querySelectorAll("#menu2 > li");
document.querySelector("#menu2").addEventListener("click",function(z){
	this.index = [].indexOf.call(node2,z.target);
	node2[this.index].innerHTML = "메뉴";
});



var node = document.querySelectorAll("#menu > li");	//이벤트 핸들링 직접사용이 안됌
document.querySelector("#menu").addEventListener("click",function(z){
	//console.log(node);
	var n = [].indexOf.call(node,z.target);
	//indexOf : 배열 번호값 지정
	//indexOf.call(부모안에자식,클릭한 자식노드) = call (return)
	console.log(n);
	
	var url = "";
	switch(n){
	case 1:
		url = "http://nate.com";
		break;
	case 2:
		url = "http://naver.com";
		break;
	case 3:
		url = "http://google.com";
		break;
/* 	default:
		url = #;
	break; */
	}
	location.href= url;
	
	/*
	const data = ["A","B","C","D"];
	console.log(data.indexOf("C")); // 2 나옴 인덱스 번호 알려줌
	*/
});
</script>
</html>