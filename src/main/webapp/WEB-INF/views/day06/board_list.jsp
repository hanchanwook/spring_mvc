<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#bbs table {
	width: 700px;
	margin: 0 auto;
	margin-top: 20px;
	border: 1px solid black;
	border-collapse: collapse;
	font-size: 14px;
}

#bbs table caption {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 10px;
}

#bbs table th, #bbs table td {
	text-align: center;
	border: 1px solid black;
	padding: 4px 10px;
}

.no {
	width: 15%
}

.subject {
	width: 30%
}

.writer {
	width: 20%
}

.reg {
	width: 20%
}

.hit {
	width: 15%
}

.title {
	background: lightsteelblue
}

.odd {
	background: silver
}

/* paging */
table tfoot ol.paging {
	list-style: none;
}

table tfoot ol.paging li {
	float: left;
	margin-right: 8px;
}

table tfoot ol.paging li a {
	display: block;
	padding: 3px 7px;
	border: 1px solid #00B3DC;
	color: #2f313e;
	font-weight: bold;
}

table tfoot ol.paging li a:hover {
	background: #00B3DC;
	color: white;
	font-weight: bold;
}

.disable {
	padding: 3px 7px;
	border: 1px solid silver;
	color: silver;
}

.now {
	padding: 3px 7px;
	border: 1px solid #ff4aa5;
	background: #ff4aa5;
	color: white;
	font-weight: bold;
}
a{text-decoration: none; color: skyblue;}
</style>

</head>
<body>
	<div id="bbs" align="center">
		<table summary="게시판 목록">
			<caption>Borad 게시판 목록</caption>
			<thead>
				<tr class="title">
					<th class="no">번호</th>
					<th class="subject">제목</th>
					<th class="writer">글쓴이</th>
					<th class="reg">날짜</th>
					<th class="hit">조회</th>
			</thead>
			<thead>
			<tbody>
				<c:choose>
					<c:when test="${empty boardList}">
						<tr>
							<td colspan="5"><h3>게시물이 존재하지 않습니다.</h3></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${boardList }" varStatus="v">
							<tr>
								<td>${paging.totalRecord - ((paging.nowPage-1) * paging.numPerPage + v.index) }</td>
								<td style="text-align: left;">
								<c:forEach begin="1" end="${k.b_step}">&nbsp; [Re]</c:forEach>
								<c:choose>
									<c:when test="${k.active == 1 }">
										<span style="color: lightgray">삭제된 게시물 입니다.</span>
									</c:when>
									<c:otherwise>
										<a href="/board_detail?b_idx=${k.b_idx }&cPage=${paging.nowPage}">${k.title }</a>
									</c:otherwise>
								</c:choose>
								</td>
								<td>${k.writer }</td>
								<td>${k.regdate.substring(0,10) }</td>
								<td>${k.hit }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
				<%--<p>beginBlock: ${paging.beginBlock}, endBlock: ${paging.endBlock}, totalPage: ${paging.totalPage}</p>--%>			
			<tfoot>
				<tr>
					<td colspan="4">	
						<ol class="paging">
						<!-- 이전 버튼 --> 
							<c:choose>
								<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
									<li class="disable">이전으로</li>
								</c:when>
								<c:otherwise>
									<li><a href="/day06?cPage=${paging.beginBlock-paging.pagePerBlock}">이전으로</a></li>
								</c:otherwise>
							</c:choose>
						<!-- 번호들 --> 
								<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k" >
								
									<c:choose>
										<c:when test="${k == paging.nowPage }">
											<li class="now">${k}</li>
										</c:when>
										<c:otherwise>
											<li><a href="/day06?cPage=${k}">${k}</a></li>
										</c:otherwise>
									</c:choose>	
								</c:forEach>
							<!-- 다음버튼 -->
							<c:choose>
								<c:when test="${paging.endBlock >= paging.totalPage }">
									<li class="disable">다음으로</li>
								</c:when>
								<c:otherwise>
									<li><a href="/day06?cPage=${paging.beginBlock + paging.pagePerBlock }">다음으로</a></li>								
								</c:otherwise>
							</c:choose>
						</ol>
					</td>
					<td><input type="button" value="글쓰기" onclick="board_write()"></td>
				</tr>
			</tfoot>

		</table>
	</div>
<script type="text/javascript">
	function board_write() {
		
		location.href="/board_write";
	
	}
</script>
</body>
</html>