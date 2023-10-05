<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저장된 파일 리스트</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>파일 리스트</th>
			</tr>
		</thead>
		<tbody id="datalist">
			
		</tbody>
	</table>
</body>
<script>
//표현식 코드는 자바스크립트 주석으로 사용하더라도 작동됨
//JSTL 먼저 작동을 하며, javascript는 이후에 작동하게 됩니다.
//java -> JSP(표현식) -> Javascript -> HTML

var data = Number("${data.size()}");
var ea0 ="${data.get(0).getFname()}";
var ea1 ="${data.get(1).getFname()}";
var ea2 ="${data.get(2).getFname()}";
var ea3 ="${data.get(3).getFname()}";
var ea4 ="${data.get(4).getFname()}";

var w = 0;
var html = document.getElementById("datalist");
while(w < data){
	var filenm = eval("ea"+w);
	var spnm = filenm.split(",");	//","을 기준으로 배열화 시키기
	console.log(spnm)
	var aa = document.createElement("tr");
	var bb = document.createElement("td");
	//var cc = document.createTextNode(filenm);	//text
	
	for(var f=0; f<spnm.length; f++){
		bb.innerHTML += "<a href='"+spnm[f]+"'>"+spnm[f]+"<img src='"+spnm[f]+"' width='30'/></a>\n";
		
	}
	
	aa.append(bb);
	html.append(aa);
	w++;
}
</script>
</html>