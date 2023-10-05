<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품리스트</title>
</head>
<body>
<h3>자신의 local 창</h3>
<!-- 상대방이 제시하는 웹 페이지 값을 iframe으로 가져옴 -->
<iframe frameborder="0" src="http://192.168.110.218:8080/batis/popup.jsp" width="400" height="400" style="background-color: yellow;" >
</iframe>
</body>

<script>
/* 
 addEventListener : 실시간으로 정보값을 가져옴
 ("message",받을 함수명,동기or비동기로 선택가능);
 message : postMessage에 대한 정보값
*/
window.addEventListener("message",product_code,false);   //비동기방식
function product_code(a) {
	/* 해당 iframe에서 받은 데이터 값을 부모창으로 전달함 */
	//console.log(a.response);
	window.opener.f.m.value = a.data.msg;
	//a : 이벤트 출력, data : 배열값을 가지고 있는 속성, msg : 배열키(랜덤)
	self.close();
}
</script>

</html>