<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동적쿼리</title>
<style type="text/css">
	a{
		text-decoration: none;
	}
	table {
	width: 800pxl; border-collapse: collapse;
	text-align: center;
	margin: 0px auto;
	}
	table, th, td {
	border: 1px solid black; 
	padding: 3px;
	}
	h3{text-align: center;}
	#result{text-align: center; font: 20px}
</style>
</head>
<body>
	<h2>사원 정보 동적 검색</h2>
	<form method= "post">
		<p><input type="button" value="전체 사원 조회" onclick="send_go('/emp_list', this.form)"></p>
		<p>
		부서번호 : <input type="number" name="deptno">
		<input type="button" value="부서별 조회" onclick="send_go('/emp_search', this.form)">
		</p>
		<p>
			<select name="idx">
				<option value="1">사번</option>
				<option value="2">이름</option>
				<option value="3">직종</option>
				<option value="4">부서번호</option>
			</select>
			<input type="text" name="keyword">
			<input type="button" value="선택 동적검색" onclick="send_go('/emp_dynamic_search', this.form)">
		</p>
		<p>
			직종선택(Ctrl+선택)<select name="jobs" multiple size="4">
				<option value="MANAGER">MANAGER</option>
				<option value="CLERK">CLERK</option>
				<option value="SALESMAN">SALESMAN</option>
				<option value="ANALYST">ANALYST</option>
			</select>
			<input type="button" value="직종별 검색" onclick="send_go('/emp_by_jobs', this.form)">
		</p>
		<p>
			사번 : <input type="number" name="empno">
			이름 : <input type="text" name="ename">
			<input type="button" value="사번 or 이름" onclick="send_go('/emp_by_no_name', this.form)">
		</p>
	</form>
	<hr>
	<%@ include file="dynamicResult.jsp" %>
	
	<script type="text/javascript">
		function send_go(url,f){
			f.action=url;
			f.submit();
		}
	</script>
</body>
</html>

















