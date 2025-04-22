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
	<h2>기상청_단기예보(구_동네예보) 조회서비스 - 단기 예보 조회 (서울)</h2>
	<button id="btnXml">XML데이터불러오기</button>
	<button id="btnJson">Json데이터불러오기</button>
	
	<div id="result"></div>
	
	<!-- ajax는 일반적인 Controller로 가지 않는다. RestController로 간다. -->
	<script type="text/javascript">
		// 설명과 해설용
		const weatherExplain = {
			POP: { label: "강수확률", interpret: val => val + "% 비 올 확률입니다."},
			PTY: { label: "강수형태", interpret: val =>({"0":"없음", "1":"비", "2":"비/눈", "3":"눈", "4":"소나기"}[val] || "미상")},
			REH: { label: "습도", interpret: val => val + "% 습도 입니다."},
			SKY: { label: "하늘상태", interpret: val =>({"1":"맑음", "3":"구름많음", "4":"흐림"}[val] || "미상")},
			T3H: { label: "3시간 기온", interpret: val => val + "℃ 예상 기온입니다." },
			TMN: { label: "일 최저기온", interpret: val => val + "℃ 오늘 아침 최저입니다." },
			TMX: { label: "일 최고기온", interpret: val => val + "℃ 오늘 낮 최고입니다." },
			WSD: { label: "풍속", interpret: val => val + "m/s 바람입니다." }
		}
	
	
		$("#btnXml").on("click", function(){
			$("#result").html("<p>XML 데이터 로딩 중...</p>");
			$.ajax({
				url : "/weatherXml",
				method : "post",
				dataType : "xml",
				success : function(response){
		//			console.log(response);
					let table = "<table>";
					table +="<thead>";
					table +="<tr><td>예보일자</td><td>시간</td><td>항목</td><td>값</td><td>설명</td><td>해석</td></tr>";
					table +="</thead>";
					
					table +="<tbody>";
					$(response).find("item").each(function() {
						let date = $(this).find("fcstDate").text()
						let time = $(this).find("fcstTime").text()
						let category = $(this).find("category").text()
						let fcstValue = $(this).find("fcstValue").text()
						
						let label = weatherExplain[category]?.label||category;
						let desc = weatherExplain[category]?.interpret(fcstValue)|| "-" ;
						
						table +="<tr>";
						table +="<td>"+ date +"<td>";
						table +="<td>"+ time +"<td>";
						table +="<td>"+ category +"<td>";
						table +="<td>"+ fcstValue +"<td>";
						table +="<td>"+ label +"<td>";
						table +="<td>"+ desc +"<td>";
						table +="</tr>";
						
					});
					table +="</tbody>";
					table +="</table>";	
					
					$("#result").html(table);
				},
				error : function(){
					alert("가져오기 실패")
				}
			})
		});
		$("#btnJson").on("click", function(){
			$("#result").html("<p>XML 데이터 로딩 중...</p>");
			$.ajax({
				url : "/weatherJson",
				method : "post",
				dataType : "json",
				success : function(response){
				//	console.log(response);	
					let items = response.response.body.items.item;
					
					let table = "<table>";
					table +="<thead>";
					table +="<tr><td>예보일자</td><td>시간</td><td>항목</td><td>값</td><td>설명</td><td>해석</td></tr>";
					table +="</thead>";	
					table +="<tbody>";
					$.each(items, function(i, item){
						let date =  v.fcstDate;
						let time = v.fcstTime;
						let category = v.category;;
						let fcstValue = v.fcstValue
						
						let label = weatherExplain[category]?.label||category;
						let desc = weatherExplain[category]?.interpret(fcstValue)|| "-" ;
						
						table +="<tr>";
						table +="<td>"+ date +"<td>";
						table +="<td>"+ time +"<td>";
						table +="<td>"+ category +"<td>";
						table +="<td>"+ fcstValue +"<td>";
						table +="<td>"+ label +"<td>";
						table +="<td>"+ desc +"<td>";
						table +="</tr>";
						
					});
					
					
					table +="</tbody>";
					table +="</table>";					
				
					$("#result").html(table);		
				},
				error : function(){
					alert("가져오기 실패")
				}
			})
		});
		</script>
</body>
</html>