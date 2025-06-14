<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
tr {
	text-align: center;
	padding: 4px 10px;
	background-color: #F6F6F6;
}

th {
	width: 120px;
	text-align: center;
	padding: 4px 10px;
	background-color: #B2CCFF;
}
table {
	width: 700px;
	
}
th, td {margin 5px; padding: 10px;}

table caption {
	font-size: 20px;
	font-weight: bold;
	margin: 30px 0px;
}
</style>
</head>
<body>
<div id="bbs" align="center">
	<form method="post">
		<table summary="게시판 상세보기">
			<caption>Board 게시판 상세보기</caption>
			<tbody>
				<tr>
					<th bgcolor="#B2EBF4">작성자</th>
					<td>${boardVO.writer }</td>
				</tr>

				<tr>
					<th bgcolor="#B2EBF4">제목</th>
					<td>${boardVO.title }</td>
				</tr>
				<tr>
					<th bgcolor="#B2EBF4">날짜</th>
					<td>${boardVO.regdate.substring(0,10) }</td>
				</tr>
				<tr>
					<th bgcolor="#B2EBF4">내용</th>
					<td><pre>${boardVO.content }</pre></td>
				</tr>
				<tr>
					<th bgcolor="#B2EBF4">첨부파일</th>
					<c:choose>
						<c:when test="${empty boardVO.f_name }">
							<td><b>첨부파일 없음</b></td>
						</c:when>
						<c:otherwise>
							<td>
								<a href="/board_down?f_name=${boardVO.f_name }">
									<img style="width: 100px;" alt="" src='<c:url value="/resources/upload/${boardVO.f_name }" />'>
								</a>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2"> 
					<input type="button" value="목록" onclick="board_list(this.form)" /> 
					<input type="button" value="답글" onclick="board_ans_write(this.form)" /> 
					<input type="button"value="수정" onclick="board_update(this.form)" /> 
					<input type="button" value="삭제" onclick="board_delete(this.form)" />
					<input type="hidden" name="b_idx" value="${boardVO.b_idx }" />
					<input type="hidden" name="cPage" value="${cPage }" />
					<input type="hidden" name="pwd" value="${boardVO.pwd }" />
					</td>
				</tr>
			</tfoot>
		</table>
		
	</form>
	</div>
	<script type="text/javascript">
		function board_list(f) {
			f.action="/day06";
			f.submit();
		}
		function board_ans_write(f) {
			f.action="/board_ans_write";
			f.submit();
		}
		function board_update(f) {
			f.action="/board_update";
			f.submit();
		}
		function board_delete(f) {
			f.action="/board_delete";
			f.submit();
		}
		
	</script>	
	
</body>
</html>










