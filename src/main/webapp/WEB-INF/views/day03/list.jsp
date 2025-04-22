<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<style type="text/css">
	a{text-decoration: none;}
	table {
	width : 600px; border-collapse: collapse;
	text-align: center;}
	table, th, td{
		border: 1px solid black;
		padding: 3px;
	} 
	div{
		width: 600px; margin: 20px;
		text-align:center;
	}

</style>
</head>
<body>
	<div>
		<h2>방 명 록 : 전 체 보 기</h2>
		<hr>
		<p><a href="guestBookWrite">방 명 록 쓰 기</a></p>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="4"> 
								<h3>원하는 정보가 존재하지 않습니다.</h3>
								</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list }" var="k" varStatus="v">
							<tr>	
								<td>${v.count }</td>
								<td>${k.gb_name }</td>
								<td><a href="/guestBookDetail?gb_idx=${k.gb_idx}">${k.gb_subject }</a></td>
								<td>${k.gb_regdate.substring(0,10)}</td>
							</tr>							
						</c:forEach>
					
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>	
	</div>
</body>
</html>