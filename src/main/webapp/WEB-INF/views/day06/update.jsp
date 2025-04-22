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
	    text-align:center;
	    padding:4px 10px;
	    background-color: #F6F6F6;
	}
	
th {
		width:120px;
	    text-align:center;
	    padding:4px 10px;
	    background-color: #B2CCFF;
	}
</style>
<script type="text/javascript">
	<c:if test="${pwdchk == 'fail' }">
		alert("비밀번호 틀림");
	</c:if>
</script>
</head>
<body>
	<div align="center">
	<form method="post" encType="multipart/form-data">
		<table summary="게시판 수정하기">
			<caption>게시판 수정하기</caption>
			<tbody>
				<tr>
					<th>작성자:</th>
					<td align="left"><input type="text" name="writer" value="${boardVO.writer }" ></td>
				</tr>
				<tr>
					<th>제목:</th>
					<td align="left"><input type="text" name="title" value="${boardVO.title }"></td>
				</tr>
					<tr>
					<th>내용:</th>
					<td align="left"><textarea rows="10" cols="60" name="content"  >${boardVO.content }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
						<c:choose>
							<c:when test="${empty boardVO.f_name }">
							
								<td align="left">
									<input type="file" name="file_name"><b>첨부 파일 없음</b>
									<input type="hidden" name="old_f_name" value="">
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<input type="file" name="file_name"><b>(${boardVO.f_name })</b>
									<input type="hidden" name="old_f_name" value="{boardVO.f_name }">											
								</td>
							</c:otherwise>										
						</c:choose>
					</tr>
				<tr>
					<th>비밀번호:</th>
					<td align="left"><input type="password" name="pwd" ></td>
				</tr>
				<tr>
					<td colspan="2" >
						<input type="button" value="수정하기" onclick="board_update_ok(this.form)">
						<input type="button" value="목록" onclick="bord_list_go(this.form)">
						<input type="hidden" name="cPage" value="${cPage }">	
						<input type="hidden" name="b_idx" value="${boardVO.b_idx }">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	<script type="text/javascript">
		function board_update_ok(f) {
			f.action="/board_update_ok";
			f.submit();
		}
		function bord_list_go(f) {
			f.action="/day06";
			f.submit();
		}
	</script>
	
</body>
</html>

