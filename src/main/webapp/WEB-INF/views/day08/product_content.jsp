<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 10px auto;
	width: 800px;
	border-collapse: collapse;
	font-size: 12pt;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
	padding: 10px;
}
</style>
</head>
<body>
	<jsp:include page="top.jsp" />
	<table>
		<tr>
			<td width="30%">제품Category</td>
			<td width="70%">${svo.category}</td>
		</tr>
		<tr>
			<td width="30%">제품번호</td>
			<td width="70%">${svo.p_num}</td>
		</tr>
		<tr>
			<td width="30%">제품이름</td>
			<td width="70%">${svo.p_name}</td>
		</tr>
		<tr>
			<td width="30%">제품판매사</td>
			<td width="70%">${svo.p_company}</td>
		</tr>
		<tr>
			<td width="30%">제품가격</td>
			<td width="70%">정가 : <fmt:formatNumber value="${svo.p_price}" pattern="#,##0" />원
			 	<br><span style="color: red;">(할인가: <fmt:formatNumber value="${svo.p_saleprice}" pattern="#,##0" />원)</span>
			</td>
		</tr>
		<tr>
			<td>제품설명</td>
			<td>${svo.p_content }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<img src='<c:url value="/resources/images/${svo.p_image_l}"/>' width="450px">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="장바구니에 담기" onclick="add_cart()"> 
				<input type="button" value="장바구니 보기" onclick="show_cart()" >
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		function add_cart() {
			const shop_idx = "${svo.shop_idx}";
			location.href="/shop_add_cart?shop_idx="+shop_idx;
		}
		function show_cart() {
			location.href="/shop_show_cart";	
		}
	</script>
</body>
</html>




