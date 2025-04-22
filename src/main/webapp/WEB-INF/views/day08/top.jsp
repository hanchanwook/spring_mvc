<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a:link {
	text-decoration: none;
	color: navy
}

a:visited {
	text-decoration: none;
	color: navy
}

a:hover {
	text-decoration: none;
	color: red
}

body {
	text-align: center
}

hr {
	width: 600px;
	border: 1px;
	color: navy;
}

div#header, div#nav {
	width: 600px;
	margin: 20px auto;
	text-align: center;
}

div#wrap {
	margin:0px auto;
}
div#log{float: right;}
.title{font-size: 32px;}
</style>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<span class="title"> ICT SHOPPING CENTER </span>
		</div>
		<div id="nav">
			<a href="/day08?category=com001">컴퓨터</a> | 
			<a href="/day08?category=ele002">가전 제품</a> | 
			<a href="/day08?category=sp003">스포츠</a>
			<div id="log">
				<c:choose>
					<c:when test="${loginchk == 'ok' }">
					   ${membersVO.m_name }님 환영합니다.  |
					   
					   <c:if test="${admin == 'ok' }">
					   	 <a href="/shop_product_add_form">상품등록하기</a>  |
					   </c:if>
					   <c:if test="${admin != 'ok' }">
					   	  <a href="/shop_show_cart">장바구니</a>  |
					   </c:if>
					   
					   <a href="/members_logout">로그아웃</a>
					</c:when>
					<c:otherwise>
						<a href="/members_loginForm">로그인</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>