<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//이니시스제공 
String mid					= "INIpayTest";		       //상점아이디            				
String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";  //상점키 
String goodcode	= (String)request.getAttribute("goodcode"); 
String mKey = SignatureUtil.hash(signKey, "SHA-256");
String timestamp			= SignatureUtil.getTimestamp();
String orderNumber			= mid+"_"+goodcode;	
String price				= (String)request.getAttribute("price");;	
//out.print(orderNumber);
//out.print(price);

Map<String, String> signParam = new HashMap<String, String>();

signParam.put("oid", orderNumber);
signParam.put("price", price);
signParam.put("timestamp", timestamp);

String signature =SignatureUtil.makeSignature(signParam);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 출력 및 결제 API</title>
<script language="javascript" type="text/javascript" src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
</head>
<body>
<form id="pay">
<input type="hidden" name="version" value="1.0">
<input type="hidden" name="gopaymethod" value="<%=(String)request.getAttribute("gopaymethod")%>">
<input type="hidden" name="mid" value="<%=mid%>">
<input type="hidden" name="oid" value="<%=orderNumber%>">
<input type="hidden" name="price" value="<%=(String)request.getAttribute("price")%>">
<input type="hidden" name="timestamp" value="<%=timestamp%>">
<input type="hidden" name="signature" value="<%=signature%>">
<input type="hidden" name="mKey" value="<%=mKey%>">
<input type="hidden" name="currency" value="WON">
<input type="hidden" name="buyername" value="<%=(String)request.getAttribute("buyername")%>">
<input type="hidden" name="buyertel" value="<%=(String)request.getAttribute("buyertel")%>">
<input type="hidden" name="buyeremail" value="<%=(String)request.getAttribute("buyeremail")%>">
<input type="hidden" name="returnUrl" value="http://localhost:8080/batis/return.jsp"> <!-- 결제가되면 콜 받음 -->
<input type="hidden" name="closeUrl" value="http://localhost:8080/batis/close.jsp">
<input type="hidden" name="goodname" value="<%=(String)request.getAttribute("goodname")%>">

</form>
<p>------------결제자 정보----------------</p>
고객명 : <%=(String)request.getAttribute("buyername")%><br>
고객 연락처 : <%=(String)request.getAttribute("buyertel")%><br>
고객명 이메일 : <%=(String)request.getAttribute("buyeremail")%><br>

<p>------------상품 정보----------------</p>
상품코드: <%=(String)request.getAttribute("goodcode")%><br>
상품명 : <%=(String)request.getAttribute("goodname")%><br>
상품갯수 : <%=(String)request.getAttribute("goodea")%><br>

<p>------------배송정보----------------</p>
수령인 : <%=(String)request.getAttribute("buyername")%><br>
배송지 : <%=(String)request.getAttribute("rec_post")%><br>
도로명주소 : <%=(String)request.getAttribute("rec_addr")%><br>
상세주소 : <%=(String)request.getAttribute("rec_addr_dtc")%><br>

<p>------------결제금액 및 결제수단----------------</p>
최종결제금액 : <%=(String)request.getAttribute("price")%><br>
결제수단 : <%=(String)request.getAttribute("gopaymethod")%><br>
포인트 및 적립금 : <%=(String)request.getAttribute("point")%><br>
<input type="button" value="결제하기" onclick="payok()">
</body>
<script>
	function payok() {
		INIStdPay.pay('pay');
	}
</script>
</html>







