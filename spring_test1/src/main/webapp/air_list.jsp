<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약인원 리스트 페이지</title>
</head>
<body>
<p>예약 인원 총 리스트 (총 : ${total_person}명)</p>
<table border="1">
<thead>
<tr>
<th>번호</th>
<th>아이디</th>
<th>고객명</th>
<th>항공사</th>
<th>인원</th> 
<th>등록일</th>
</tr>
</thead>
<tbody>
<%
int now_page =1;
try{
	now_page = Integer.parseInt(request.getParameter ("page"));
	// out.print("test");
}
catch(Exception e){
	// out.print("페이지 번호 오류");
}
//try성공해도 finally는 작동되, tey 실패 -=> catch -> finally
finally{ //무조건 작동됨
	if(now_page ==1){
		now_page =1;
	}
}

%>
<app:set var="now" value="<%=now_page%>"></app:set>
<app:set var="w" value="${total_person-(now-1)*2}"></app:set>
<app:forEach var="item" items="${total_list}">
<tr>
<td>${w}</td>
<td>${item[1]}</td>
<td>${item[2]}</td>
<td>${item[6]}</td>
<td>${item[7]}</td>
<td>${item[9]}</td>
</tr>
<app:set var="w" value="${w-1} }"></app:set>
</app:forEach>
</tbody>
<!-- set : 해당 값을 다른 표현식 이름으로 활용할 수 있도록 셋팅하는 태그 -->
<app:set var="page" value="${total_person/2} "/>
</table>
<!-- 페이지 번호 forEach의 기본은 기본은 배열이 기본으로 작동되는 원리 total_person은 배열이 아님-->
<app:forEach varStatus="no" begin="1" end="${page+(1-(page%1)%1)}" step="1"> <!-- 나머지 값을 확인하여 올림형태로 적용 -->
<div style="width:30px; height: 30px; border: 1px solid black; display: inline-block; line-height: 30px; text-align: center; cursor: pointer;" onclick="abc(${no.index})">${no.index}</div>
</app:forEach>
<!-- 페이지 번호 끝-->
</body>
<script>
	function abc(pg){
		//alert(pg)
		location.href='./air_list.do?page='+pg;
	}
</script>
</html>





