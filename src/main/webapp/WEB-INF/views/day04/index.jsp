<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Day04</title>
</head>
<body>
	<p><button onclick="dynamic()">동적쿼리(DynamicQuery)</button></p>
	<p><button onclick="ajax01()">Ajax(공공데이터포털)</button></p>
	<p><button onclick="ajax02()">Ajax(공공데이터포털)2-숙제</button></p>
	
	<script type="text/javascript">
		function dynamic() {
			location.href="/dynamicForm";
		}
		function ajax01() {
			location.href="/ajax01Form";
		}	
		function ajax02() {
			location.href="/ajax02Form";
		}
		</script>
</body>
</html>