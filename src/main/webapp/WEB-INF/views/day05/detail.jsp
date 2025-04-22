<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	.odd {background:silver}
	
	input[type="submit"]{
		float: right
	}
</style>

</head>
<body>
	<div id="bbs">
	<form method="post" encType="multipart/form-data">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td>${bvo.subject}</td>
				</tr>
				<tr>
					<th>이름:</th>
					<td>${bvo.writer}</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><pre>${bvo.content}</pre></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<c:choose>
						<c:when test="${empty bvo.f_name}">
							<td><b>첨부파일 없음</b></td>
						
						</c:when>
						<c:otherwise>
							<td>
							<a href="/bbs_down?f_name=${bvo.f_name }">
								<img width="150px" src='<c:url value="/resources/upload/${bvo.f_name}" />'>
							</a>
							</td>
						</c:otherwise>
					</c:choose>	
				</tr>
				<tr >
					<td colspan="2" style="text-align: center;">
						<input type="button" value="수정" onclick="bbs_update(this.form)">
						<input type="button" value="삭제" onclick="bbs_delete(this.form)">
						<input type="button" value="목록" onclick="bbs_list(this.form)">
						<input type="hidden" name="b_idx" value="${bvo.b_idx}">
						<input type="hidden" name="cPage" value="${cPage}">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	
	<div style="width: 580px; margin: 0px auto;">
		<!-- 댓글 쓰기 -->
	 	<form action="/comm_insert" method="post">
			<input type="hidden" name="b_idx" value="${bvo.b_idx }"> 	
	 		<table style="width: 100%; border: 1px solid #aaa; border-collapse: collapse; margin-top: 30px;">
	 			<tbody>
		 			<tr>
		 				<!-- 로그인 처리 시 정보 가져오면 된다. -->
		 				<th style="width: 20%; background-color: skyblue">작성자</th>
		 				<td style="width: 80%;"><input type="text" name="writer" style="width: 98%"> </td>
		 			</tr>
		 			<tr>
		 				<th style=" background-color: skyblue">댓글 내용</th>
		 				<td><textarea rows="4" name="content" style="width: 98%"></textarea></td>
		 			</tr>
	 			</tbody>
	 			<tfoot>
	 				<tr>
	 					<td colspan="2" style="text-align: right;">
	 						<input type="hidden" name="cPage" value="${cPage}">
	 						<input type="submit" value="댓글작성">
	 					</td>
	 				</tr>
	 			</tfoot>
	 		</table>
	 	</form>
	 	
	 	<!-- 댓글 목록 -->
	 	<table style="width: 100%; border: 1px solid #aaa; border-collapse: collapse; margin-top: 30px;">
			<caption style="padding: 10px;">댓글 목록</caption>
	 		<c:choose>
	 			<c:when test="${empty commList }">
	 				<tr><td style="text-align: center; font-size: 15px; padding: 10px;">등록된 댓글이 없습니다.</td></tr>
	 			</c:when>
	 			<c:otherwise>
	 				<c:forEach var="k" items="${commList }">
		 					<tr style="border: 1px solid #aaa;" >
		 						<td style="padding: 15px;">
		 						${k.writer} (${k.write_date})<br>
		 							${k.content}<br>
		 							<form action="/comm_delete" method="post">
		 								<input type="hidden" name="c_idx" value="${k.c_idx}">
		 								<input type="hidden" name="b_idx" value="${bvo.b_idx}">
		 								<input type="hidden" name="cPage" value="${cPage}">
		 								<input type="submit" value="댓글삭제">
		 							</form>
		 						</td>
		 					</tr>
		 				
	 				</c:forEach>
	 			</c:otherwise>
	 		</c:choose>
	 	</table>
	</div>
	<script type="text/javascript">
		function bbs_update(f) {
			f.action="/bbs_update";
			f.submit();
		}
		function bbs_delete(f) {
			f.action="/bbs_delete";
			f.submit();
		}		
		function bbs_list(f) {
			f.action="/day05";
			f.submit();
		}
		
	</script>
	
</body>
</html>

