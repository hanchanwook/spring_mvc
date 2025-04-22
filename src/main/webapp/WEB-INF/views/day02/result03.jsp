<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>배열 처리 결과 보기</h2>
	<c:choose>
		<c:when test="${empty cvo.hobby}">
			<h2>정보가 없습니다.</h2>
		</c:when>
		<c:otherwise>
			<c:forEach var="k" items="${cvo.hobby}">
				<li>${k}</li>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>