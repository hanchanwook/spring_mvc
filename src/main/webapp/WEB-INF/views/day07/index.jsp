<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div>
        <input type="email" id="email" placeholder="등록된 이메일 입력" />
        <button onclick="sendCode()">인증코드 전송</button>
    </div>
    <div>
        <input type="text" id="code" placeholder="인증코드 입력" />
        <button onclick="verifyCode()">인증 확인</button>
    </div>
    <div id="pw-change-form" style="display: none;">
        <input type="password" id="newPw" placeholder="새 비밀번호 입력" />
        <button onclick="changePw()">비밀번호 변경</button>
    </div>
    <br>
    <br>
	<div id="result"></div>    
    <!-- 
    <div id="pw-change-frm" style="display:none">
    	<input type="password" id="newPw" placeholder="새 비밀번호 입력" />
    	<button onclick="changePw()">비밀번호 변경</button>
    </div>
     -->
    
    <script type="text/javascript">
    	function sendCode() {
    		//	갈곳, 파라미터, 성공했을 때 function()
			$.post("/sendCode",  {email:$("#email").val()},function(response){
				if(response === "success"){
					alert("인증코드가 전송되었습니다.")
				}else{
					alert("전송실패");
				}
			});		
		}
    	
    	function verifyCode() {
			$.post("/verifyCode",{code:$("#code").val()}, function(response) {
				let msg = {
					match : "인증성공",
					mismatch : "코드 불일치",
					expired : "인증 시간 초과",
					no_code : "인증코드 없음"
				}
				$("#result").html(msg[response] || "오류");
				
				if(response === "match"){
					 $("#pw-change-form").show();
				}
			});
		}
    </script>
</body>
</html>