<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error 페이지</title>
</head>
<body>
	<h1>공 사 중</h1>
	<button onclick="go_guestBookList()">GuestBookList</button>
	<script type="text/javascript">
		function go_guestBookList() {
			location.href="/guestBookList";
		}
	</script>
</body>
</html>