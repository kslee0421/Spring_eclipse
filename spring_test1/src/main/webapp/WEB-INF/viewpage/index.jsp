<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--
 ${} : 표현식 코드 문법 (JSTL)
 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring기초</title>
</head>
<heardr>
<%@ include file= "./top.jsp"%>
</heardr>
<body>
 <%=request.getAttribute("name") %><br><!-- JSP 전용코드-->
 고객명 : ${name}
</body>
</html>