<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%-- JSP 페이지 설정: Java 언어 사용, 문자 인코딩 UTF-8 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	<%-- HTML 문서 자체의 문자 인코딩 설정 --%>
<title> 방 명 록 </title>	<%-- 브라우저 탭에 표시될 제목 --%>

<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	
</style>

</head>
<body>
	<div>
		<h2>방 명 록  : 삭 제 화 면</h2>		<%-- 페이지 제목 --%>
		<hr>
		<p>[<a href="/guestBookList">목록으로 이동]</a></p>		<%-- 목록으로 돌아가는 링크 --%>
		<form action="/guestBookDeleteOK" method="post" >	<%-- 삭제 처리 요청 보낼 폼 --%>
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>		<%-- 비밀번호 입력란 제목 --%>
					<td><input type="password" name="gb_pw" id="gb_pw" size ="20" required></td>	<%-- 비밀번호 입력 필드 (필수 입력) --%>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" onclick="delete_ok(this.form)" value="저장">		<%-- 자바스크립트 함수 호출 버튼 --%>
							<input type="hidden" name="gb_idx" value="${gvo.gb_idx}" >		<%-- 글 번호를 숨겨서 함께 보냄 --%>
							<input type="reset" value="취소">			<%-- 입력 내용 초기화 버튼 --%>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function delete_ok(f) {
			let pwdchk = `${gvo.gb_pw}`;	<%-- 서버에서 받아온 원래 비밀번호 (EL로 표현된 것처럼 보이지만 실제 실행 X) --%>
			let pwd = document.querySelector("#gb_pw").value;	<%-- 사용자가 입력한 비밀번호 값 --%>
	//		console.log("pwdchk : " + pwdchk);
	//		console.log("pwd : " + pwd);
			if(pwd === pwdchk){			<%-- 비밀 번호 일치 여부 확인 --%>
				const msg = confirm("정말 삭제 할까여?");	<%-- 사용자에게 재확인 --%>
				if(msg){
					f.submit();			<%-- 확인 눌렀으면 폼 제출 --%>
				}else{
					// 이전 화면으로 이동
					window.history.go(-1);	<%-- 아니면 이전 페이지로 돌아감 --%>
				}
			}else{
				alert("비밀번호 매칭 X");		<%-- 비밀번호 다를 경우 알림 --%>	
				document.querySelector("#gb_pw").value = "";	<%-- 입력값 초기화 --%>
				document.querySelector("#gb_pw").focus();		<%-- 다시 입력할 수 있도록 포커스 줌 --%>
				return;
			}		
	
		
		}
	</script>	
</body>
</html>









