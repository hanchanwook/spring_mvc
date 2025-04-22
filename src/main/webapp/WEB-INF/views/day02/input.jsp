<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Day02</h2>
	<form action="/logIn01" method="post">
		<label> ID : <input type="text" name="userId" required="required"></label>
		<label> PW : <input type="text" name="userPw" required="required"></label>
		<input type="submit" value="로그인">
	</form>
	
	<h2>계 산 기</h2>
	<form action="/calc" method="get">
		<p><label>	num1 : <input type="number" name="su1" required="required" ></label></p>
		<p><label>	num2 : <input type="number" name="su2" required="required" ></label></p>
		<p> 연산자 :
		<input type="radio" name="op" value="+" checked > +
		<input type="radio" name="op" value="-" > -
		<input type="radio" name="op" value="*" > *
		<input type="radio" name="op" value="/" > /
		</p>
		<input type="submit" value="계산하기">
				
	</form>
	
	<form action="/hobby" method="post">
		<p> 취미 : 
			<input type="checkbox" name="hobby" value="축구"> 축구
			<input type="checkbox" name="hobby" value="야구"> 야구
			<input type="checkbox" name="hobby" value="농구"> 농구
			<input type="checkbox" name="hobby" value="배구"> 배구
		</p>
		<input type="submit" value="보내기">
			
	</form>
	
	<button onclick="getBookList()">book 테이블 정보 보기</button>
	<script type="text/javascript">
		function getBookList() {
			location.href = "/bookList";
		}
	</script>
	
	
</body>
</html>





