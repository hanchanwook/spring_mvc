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
	<h3>검색 결과</h3>
		<c:choose>
			<c:when test="${not empty error }">
				<p style="color : red">%{error}
			</c:when>
			<c:when test="${not empty emplist }">
				<table>
					<thead>
						<tr><th>번호</th><th>사번</th><th>이름</th><th>직종</th>
						<th>매니저</th><th>입사일</th><th>부서번호</th></tr>
					</thead>
					<tbody>
						<c:forEach var="k" items="${emplist}" varStatus="v">
							<tr>
								<td>${v.count }</td>
								<td>${k.empno }</td>
								<td>${k.ename }</td>
								<td>${k.job }</td>
								<td>${k.mgr }</td>
								<td>${k.hiredate }</td>
								<td>${k.deptno }</td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p id="result" style="color: blue">조회된 결과 없습니다.</p>
			</c:otherwise>
		</c:choose>
</body>
</html>

















