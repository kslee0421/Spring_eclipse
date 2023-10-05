<%@page import="batis.datavo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
1. DB -> DTO -> Array 1차원 -> return -> Array 2차원
현재방식 
2. DB -> VO -> Array 1차원 -> viewpage 
-->
<%     
  //List<String> rv = (List<String>)request.getAttribute("data");
	List<datavo> rv = (List<datavo>)request.getAttribute("data");
	int total = Integer.parseInt((String)request.getAttribute("ct"));
	String search = (String)request.getAttribute("search");
	int pg = (int)Math.ceil(total / 5f);
	if(search == "null" || search == null || search == ""){
		search = "";
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 목록 리스트</title>
</head>
<body>
<table border="1">
	<thead>
		<tr>
			<th>순번</th>
			<th>고객명</th>
			<th>리뷰내용</th>
			<th>등록일</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
		<% 
			if(rv.size() == 0){
		%>
		<tr>
			<td colspan="6" align="center">게시글이 확인되지 않습니다.</td>
		</tr>
		<% 
			}
			else{
			int w = 0;
			do{
		%>
		<tr>
			<td>번호</td>
			<td><%=rv.get(w).getRname() %></td>
			<td><%=rv.get(w).getRtext() %></td>
			<td><%=rv.get(w).getRindate().substring(0,10) %></td>
			<td><input type="button" value="수정" onclick="modify_review(<%=rv.get(w).getRidx()%>)"></td>
			<td><input type="button" value="삭제" onclick="delete_review(<%=rv.get(w).getRidx()%>)"></td>
		</tr>
		<% 
			w++;
			} while(w < rv.size());
			}
		%>
	</tbody>
</table>
<table border="1">
	<tr>
		<% 

			int ww = 1;
			while(ww <= pg){
		%>
		<td><a href="./review_list.do?ridx=<%=ww%>"><%=ww%></a></td>
		<% 
			ww++;
			}
		%>
	</tr>
</table>
<form id="f" method="get" action="./review_list.do">
	검색 : <input type="text" name="search" value="<%=search%>">
	<input type="submit" value="검색">
</form>
</body>
<script>
document.querySelector("#f").addEventListener("submit",function(a){
	a.preventDefault();
	if(f.search.value == ""){
		location.href='./review_list.do';
	}
	else{
		f.submit();
	}
});


function modify_review(no){
	location.href='./review_modify.do?ridx='+no;		
}

function delete_review(no){
	if(confirm("해당 내용 삭제시 데이터 복구 되지 않습니다.")){
		location.href='./review_delete.do?ridx='+no;		
	}
}
</script>
</html>