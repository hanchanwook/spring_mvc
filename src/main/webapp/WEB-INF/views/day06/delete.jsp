<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<style type="text/css">
table {
        width:700px;
}
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
input{
    margin: 5px;
    padding: 5px;
}
table caption {
	font-size: 20px;
	font-weight: bold;
	margin: 30px 0px;
}
</style>

<script type="text/javascript">
  <c:if test="${pwdchk == 'fail'}">
      alert("비밀번호 틀림");
  </c:if>
</script>

</head>
<body>
    <div  align="center">
    <form method="post">
        <table summary="게시판 글 삭제">
            <caption><h2>Board 게시판 글 삭제</h2></caption>
            <tbody>
            <tr>
                <th>비밀번호</th>
                <td align="left"><input type="password" name="pwd" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="hidden" name="cPage" value="${cPage }" >
                    <input type="hidden" name="b_idx" value="${b_idx}" >
                    <input type="button" value="삭제" onclick="board_delete_ok(this.form)" > 
                    <input type="button" value="목록" onclick="bord_list_go(this.form)" > 
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    </div>
    <script type="text/javascript">
        function board_delete_ok(f) {
            f.action="/board_delete_ok" ;
            f.submit();
        }
        
        function bord_list_go(f) {
            f.action="/day06" ;
            f.submit();
        }
    </script>
</body>
</html>