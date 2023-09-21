<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비행기 예매 페이지</title>
</head>
<body>
<form id="f" method="post" action="./air_personok.do" enctype="application/x-www-form-urlencoded">
<input type="hidden" name="acode">
<p>비행기 예매</p>
<input type="text" name="aid" placeholder="아이디를 입력하세요"><br>
<input type="text" name="aname" placeholder="고객명을 입력하세요"><br>
<input type="text" name="aport" placeholder="여권번호를 입력하세요"><br>
<input type="text" name="atell" placeholder="고객 연락처('-' 미입력)" maxlength="11"><br>
<input type="hidden" name="aplain">
<input type="hidden" name="aprice" value="65000000">
<input type="hidden" name="aidx" value="1">
<select  onchange="data(this.value)">
	<option value="">항공사를 선택하세요</option>
	<option value="<%=request.getAttribute("acode") %>/<%=request.getAttribute("aplain")%>/<%=request.getAttribute("aplain")%>"> <%=request.getAttribute("aplain") %> </option>
	<option value="항공기">항공기 </option>
</select><br>
<input type="text" name="anumber" placeholder="인원수를 입력하세요" maxlength="3" onkeyup="person(this.value)"><br>
<p>총 항공료</p>
<input type="text" name="atotal" readonly="readonly" value="0"><br>
<input type="button" value="예매완료" id="btn">
</form>
</body>
<script>
var ori_money;
function data(z){
	var a = z.split("|");
	f.acode.value = a[0];	//비행기코드
	f.aplain.value = a[1];	//항공사
	f.atotal.value = a[2];	//총 금액
	ori_money = a[2];	//1인 기준 금액
	f.aperson.value = 1;	//해당 항공사 변경시 인원 초기화
}
function person(p){
	var sum = Number(p) * Number(ori_money);
	f.atotal.value = 200;
}
document.querySelector("#btn").addEventListener("click",function(){
	if(f.aid.value == ""){
		alert("아이디를 입력하세요");
	}
	else if(f.aname.value == ""){
		alert("고객명을 입력하세요"); 
	}
	else if(f.aport.value == ""){
		alert("여권번호를 입력하세요");
	}
	else if(f.atell == ""){
		alert("전화번호를 입력하세요");
	}
	else if(comfirm("예약을 확정하시겠습니까?")){
		f.submit();
	}	
});



</script>
</html>