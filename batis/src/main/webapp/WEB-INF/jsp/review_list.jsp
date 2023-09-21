<%@page import="batis.datavo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
1. DB -> DTO -> Array1차원 -> return -> Array 2차원
현재 방식
2. DB -> Vo-> Array1차원 -> view
 -->
<%
List<datavo> rv =(List<datavo>)request.getAttribute("data");
int total =Integer.parseInt((String)request.getAttribute("ct"));  //여기서 불러줘야 아래서 쓸 수 있다
String search = (String)request.getAttribute("search");
if(search=="null"||search==""||search==null){
	search ="";
}
//out.print(request.getAttribute("ct").getClass());
//out.print(rv.get(0).getRname());
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
<th>번호</th>
<th>고객명</th>
<th>리뷰내용</th>
<th>등록일</th>
<th>수정</th>
<th>삭제</th>
</tr>
</thead>
<tbody>
<% 
	if(rv.size()==0){ //검색된 데이터가 없을 경우
%>
<tr><td colspan=6>검색하신 내용은 확인 되지 않습니다.</td></tr>
<% 
	}
	else{
	int w= 0;
	do{
		
%>
<tr>
<td><%=total-w%></td>
<td><%=rv.get(w).getRname()%></td>
<td><%=rv.get(w).getRtext()%></td>
<td><%=rv.get(w).getRindate().substring(0,10)%></td>
<td><input type="button" value="수정" onclick="modify_review(<%=rv.get(w).getRidx()%>)"></td>
<td><input type="button" value="삭제" onclick="delete_review(<%=rv.get(w).getRidx()%>)"></td>
</tr>
<%
	w++;
	}while(w < rv.size());
	}
%>
</tbody>
</table>
<table border="1">
<tr>
<%
	int pg =(int)Math.ceil(total/ 2f);
	int ww= 1;
	while(ww <= pg){
	//out.print(pg);
%>
<td><a href="./review_list.do?ridx=<%=ww%>"><%=ww%></a></td>
<%ww++;
	}
%>
</tr>
</table>
<form id="f" method="get" action="./review_list.do">
검색 : <input type="text" name="search" value="<%=search%>">
<input type="submit" value="검색" >
</form>
</body>

<script>
documnent.querySelector("#f").addEventListener("submit",functuin(a){
		a.preventDefault();
		if(f.search.value==""){
			location.href='./review_list.do';
		}
		else{
			f.submit();
		}
	});

function delete_review(no){
	if(confirm("해당 내용 삭제시 데이터 복구되지 않습니다."))
	location.href='./review_delete.do?ridx='+no;
}

function modify_review(no){
	location.href='./review_modify.do?ridx='+no;
}
</script>
</html>












