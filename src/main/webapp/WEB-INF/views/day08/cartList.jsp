<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
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
}
td{
	padding: 10px 0px;
}
</style>
</head>
<body>
	<%@ include file="top.jsp" %>
	<table>
		<caption>
			<h2>:: 장바구니 내용</h2>
		</caption>
		<thead>
			<tr bgcolor="#dedede">
				<th>제품번호</th>
				<th width="20%">제품명</th>
				<th>단가</th>
				<th>수량</th>
				<th>금액</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty  cart_list}">
					<tr><td colspan="6"><span style="font-size: 24px">장바구니가 비었습니다.</span></td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${cart_list}">
						<tr>
							<td>${k.p_num }</td>
							<td>${k.p_name}</td>
							<td>정가:<fmt:formatNumber value="${k.p_price}" pattern="#,##0" />원
								<br><span style="color: red">
									(할인가:<fmt:formatNumber value="${k.p_saleprice}" pattern="#,##0" />)원
									</span>
							</td>
							<td>
								<form action="/cart_edit" method="post">
									<input type="number" name="p_su" min="1" value="${k.p_su}">
									<input type="hidden" name="cart_idx" value="${k.cart_idx}">
									<input type="submit" value="수정">
								</form>
							</td>
							<td>
								정가:<fmt:formatNumber value="${k.p_price * k.p_su}" pattern="#,##0" />원
								<br>(할인가:<fmt:formatNumber value="${k.p_saleprice * k.p_su}" pattern="#,##0" />)원
							</td>
							<td><button onclick="cart_delete(${k.cart_idx})">삭제</button></td>
						</tr>
						<%-- <c:set var="cartTotal" value="${ cartTotal = cartTotal + (k.p_price * k.p_su)}" /> --%>
						<c:set var="cartTotal" value="${ cartTotal = cartTotal + (k.p_saleprice * k.p_su)}" />
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<c:choose>
					<c:when test="${empty cartTotal}"> 
						<td colspan="6" style="text-align: right; padding-right: 10px;">총 결재액 : 0 원
					</c:when>
					<c:otherwise>
						<td colspan="6" style="text-align: right;">총 결재액 : 
						<fmt:formatNumber value="${cartTotal}" pattern="#,##0" />원</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</tfoot>
	</table>
	<script type="text/javascript">
		function cart_delete(cart_idx) {
			location.href="/cart_delete?cart_idx="+cart_idx ;
		}
	</script>
</body>
</html>