<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>

<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	
	/* summernote toolbar 수정  */
	/* .note-btn-group{width: auto;} */
	/* .note-toolbar{width: auto;} */
</style>

</head>
<body>
	<div>
		<h2>방 명 록  : 작 성 화 면</h2>
		<hr>
		<p>[<a href="/guestBookList">목록으로 이동]</a></p>
		<form action="/guestBookWriteOK" method="post" enctype="multipart/form-data"  >
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td><input type="text" name="gb_name" size ="20" required></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td><input type="text" name="gb_subject" size ="20" required></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td><input type="text" name="gb_email" size ="20" required></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="gb_pw" size ="20" required></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">첨부파일</td>
					<td><input type="file" name="gb_file_name" size ="20"></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" name="gb_content" id="gb_content" ></textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="submit" value="저장">
							<input type="reset" value="취소">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	
</body>
</html>









