<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript Node</title>
</head>
<body>
<input type="button" value="노드 앞에 추가" onclick="a()">
<input type="button" value="노드 뒤에 추가" onclick="b()">
<input type="button" value="노드 중간에 추가" onclick="c()">
<input type="button" value="노드 삭제" onclick="d()">
<input type="button" value="노드 갯수" onclick="e()">
<input type="button" value="노드 강제생성" onclick="f()">
<ul id ="list">
<li>윤보미</li>
<li>박초롱</li>
</ul>
<!-- 
enter를 치면 text라는 빈 노드가 생긴다
DOM : innerHTML, innerText
Node : 객체의 node 번호를 확인해서 출력하는 형태 (배열기본) 
 -->
</body>
<script>
var code = document.getElementById("list");
function a(){
	var a = document.createElement("li"); //node의 시작 createElement: 태그를 생성
	var text = document.createTextNode("정은지"); // createTextNode :문자만 적용
	a.append(text); //해당 태그안에 값을 적용할 때 사용
	//code.insertBefore(a,code.firstChild);
	code.insertBefore(a,code.childNodes[0]); //insertBefore : 노드 앞에 firstChild:기존의 맨 앞 태그 앞에 
}

function b(){
	var a = document.createElement("li"); //node의 시작 createElement: 태그를 생성
	var text = document.createTextNode("김남주"); // createTextNode :문자만 적용
	a.append(text); //해당 태그안에 값을 적용할 때 사용
	//code.insertBefore(a,code.lastChild); //어짜피 배열은 add하면 뒤로가므로 그냥 아래 처럼 append만 써도 된다.
	code.append(a);
}
/*
 childNodes : 빈 배열도 포함
 children : 빈 배열 제거 후 실제 태그에 대한 node값만 가져옴 

 */

function c(){
	var a = document.createElement("li"); //node의 시작 createElement: 태그를 생성
	var text = document.createTextNode("오하영"); // createTextNode :문자만 적용
	a.append(text); //해당 태그안에 값을 적용할 때 사용
	code.insertBefore(a,code.children[1]); //어짜피 배열은 add하면 뒤로가므로 그냥 아래 처럼 append만 써도 된다.
}
function d(){
	//code.removeChild(code.childNodes[0]);
	code.removeChild(code.children[0]);
}
function e(){
	//console.log(code.childNode);
	//console.log(code.children);
	var ea = code.children.length;
	console.log(ea);
}

function f(){
	var data = ["정은지","오하영","김남주"];
	var w = 0;
	do{
		var a = document.createElement("li");
		var text = document.createTextNode(data[w]);
		a.append(text);
		code.append(a);
		w++;
	}
	while(w <data.length);
}

</script>



</html>