<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<% 
	//CORS를 해결할 수 있는 response 메소드 이며
	//Access-Control-Allow-Origin(도메인) * : 모든 도메인을 말함
	//Access-Control-Allow-Credentials(인증요청) : true,false - 도메인에 대한 인증요청을 받을건지
	//이게 있어야 외부 서버에서 접속 가능
	response.addHeader("Access-Control-Allow-Origin","*");
	response.addHeader("Access-Control-Allow-Credentials","true");
%>    
${mid}

