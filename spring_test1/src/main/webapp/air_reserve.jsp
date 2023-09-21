<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//long time = System.currentTimeMillis();
	Date time = new Date();
	SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String check = today.format(time);
%> 
    <!-- datetime-local 임시방편 실무에선 text로 사용 크롬에서는 사용가능하지만 다른 브라우저에서는 오류 될 수 있음-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>항공리스트 등록 페이지 - JS + ES</title>
</head>
<body>
<p>항공 리스트</p>
<form id="f" method="post" action="./air_reserveok.do" enctype="application/x-www-form-urlencoded">
	<input type="text" name="acode" placeholder="비행기 코드 넘버(10~14자리)" maxlength="14"><br>
	<select name="aplain">
		<option value="ANA항공">ANA항공</option>
		<option value="오케이항공">오케이항공</option>
		<option value="인디고항공">인디고항공</option>
		<option value="중화항공">중화항공</option>
		<option value="에어아시아">에어아시아</option>
		<option value="대한항공">대한항공</option>
		<option value="아시아나항공">아시아나항공</option>
		<option value="제주항공">제주항공</option>
	</select><br>
	<select name="anation">
		<option value="한국">한국</option>
		<option value="일본">일본</option>
		<option value="중국">중국</option>
		<option value="베트남">베트남</option>
		<option value="말레시아">말레시아</option>
		<option value="크로아티아">크로아티아</option>
		<option value="이집트">이집트</option>
		<option value="터키">터키</option>
	</select><br>
	출발시간 : <input type="datetime-local" name="adepart"><br>
	<input type="text" name="aperson" placeholder="예약 가능 인원수를 입력하세요" onkeyup="air_abc(event)" maxlength="3"><br>
	<input type="text" name="aprice"  placeholder="1인 기준 항공료" onkeyup="air_abc(event)"><br>
	예매시작시간 : <input type="datetime-local" name="astart"><br>
	예매종료시간 : <input type="datetime-local" name="aend"><br>
	<input type="button" value="여행항공 정보 입력완료" id="btn">
</form>
</body>
<!-- 
onkeypress : keydown 되었을때 값을 가져옴 , 키를 누르고 있으면 값이 적용됌
onkeydown : keydown 되었을때 값을 가져옴 (특수키도 적용됌), 키를 떼면 값이 적용됌
onkeyup : keydown 후 keyup 되었을때 값을 가져옴
 -->
<script>
//파이어폭스로 접속시 차단 시키기
window.onload = function(){
	var bw = navigator.userAgent.toUpperCase();
	if(bw.indexOf("FIREFOX") >= 0){
		alert("해당 브라우져로는 접속을 차단합니다.");
	}
}

//숫자만 입력되게 하기
function air_abc(event){
	if(event.key >= 0 || event.key <= 9){
		return;
	}
	else{
		alert("숫자값만 입력하세요");
		f.amoney.value = "";
	}
}

/* javascript 형태  - jsp형태로 상단에 써서 times 선언해서 사용도 가능함
var today = new Date();
console.log(today.getFullYear());
console.log(today.getMonth()+1);
console.log(today.getDate());
console.log(today.getHours());
console.log(today.getMinutes());
console.log(today.getSeconds());
*/
const times = "<%=check%>";
//console.log(times);

document.querySelector("#btn").addEventListener("click",function(){
	/* 
		/내용/g : g(golbal)-모든패턴 다 보겠다.
				정규표현식 방법으로 데이터 값을 체크, 치환, 삭제 등 할 수 있습니다. 
	*/
	var s = f.astart.value.replaceAll(/-|T|:/g,"");
	var e = f.aend.value.replaceAll(/-|T|:/g,"");
	//console.log(s);
	
	if(f.acode.value == ""){
		alert("비행기 코드 넘버 10~14자리 입력하세요");
	}
	else if(f.adepart.value == ""){
		alert("출발일자를 선택하세요"); 
	}
	else if(f.adepart.value <= times){
		alert("출발일자 시간이 정확하지 않습니다.");
	}
	else if(Number(s) >= Number(e)){
		alert("예매시간이 잘못 셋팅 되었습니다.");
		f.astart.value = "";
		f.aend.value = "";
	}
	else{
		f.submit();
	}
});
</script>

</html>