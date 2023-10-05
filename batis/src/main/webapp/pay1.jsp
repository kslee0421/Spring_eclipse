<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   	Random rd = new Random();
    int number = 0;
   	String pdcode = "";
    for(int a=1; a <7; a++){
    	number = rd.nextInt(9);  //9자리만 나오게
    	pdcode += number;
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품선택 및 장바구니 - step1</title>
<!-- 상품선택페이비에서 > 장바구니 > 사용자 정보(이름, 이메일, 전화번호...) >결제수단을 선택> PG API > return.jsp(졀제승인및 미승인 결과)
close.jsp (결제 진행시 취소 버튼 클릭 되면 레이어 팝업 사라짐)
 -->
</head>
<body>
<form id="f" method="post" action="./pay2.do">
상품코드 : <input type="text" name="product_code" readonly="readonly" value="<%=pdcode%>"><br>
상품명 : <input type="text" name="product_name" value=""><br>
결제금액 : <input type="text" name="product_money" value="" placeholder="3천원 이상"><br> <!-- 숫자만-->
상품수량 : <input type="text" name="product_ea" value=""><br> <!-- 숫자만-->
<input type="button" value="상품구매확정" id="btn">
</form>
</body>
<script>
	document.querySelector('#btn').addEventListener("click",function(){
		f.submit();
	});
</script>
</html>