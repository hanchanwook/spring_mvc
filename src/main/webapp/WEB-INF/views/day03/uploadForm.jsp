<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
<%-- 
	파일업로드/다운로드
	1.	업로드할 폴더 지정 (webapp-resources-upload)
		오류발생하면 제일 먼저 해당 폴더가 있는지 확인
	2.	pom.xml에서 라이브러리 다운 받기
		<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.5</version>
		</dependency>
	3. servlet-context.xml 에서 추가 내용
	<!-- 파일 업로드용 클래스 등록 -->
		<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="maxUploadSize" value="524288000" /> 
		<beans:property name="defaultEncoding" value="UTF-8" />
	</beans:bean>
	
	4. form형식
	<form action="" method="post" enctype="multipart/form-data">

	</form>	
 --%>
	<form action="/f_up" method="post" enctype="multipart/form-data">
		<p> 올린 사람 : <input type="text" name="name"></p>
		<p> 파일 : <input type="file" name="f_name"></p>
		<p><input type="submit" name="파일업로드"></p>
	</form>	
	<hr>
 
 	<form action="/f_up2" method="post" enctype="multipart/form-data">
		<p> 올린 사람 : <input type="text" name="name"></p>
		<p> 파일 : <input type="file" name="f_name"></p>
		<p><input type="submit" name="파일업로드"></p>
	</form>	
	<hr> 
 
 
</body>
</html>






