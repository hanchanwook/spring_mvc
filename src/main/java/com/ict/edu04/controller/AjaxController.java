package com.ict.edu04.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 @Controller는 주로 ModelAndView를 반환하기 위해서 사용
 	Dispather Servlet(servlet-context.xml)으로 반환 한다
 	
 @RestController 는 객체를 브라우저에 그대로 반환	
 	Dispather Servlet(servlet-context.xml)으로 가지 않는다.
 	보통 ajax에서 사용

 */


@RestController
public class AjaxController {
	@RequestMapping(value = "/test", produces="text/plane; charset=utf-8" )
	@ResponseBody
	public String getTest() {
		return "Hello Ajax";
	}
	@RequestMapping(value = "/weatherXml", produces="text/Xml; charset=utf-8")
	public String weatherXml() {
		return fetchWeather("xml");
	}
	@RequestMapping(value = "/weatherJson", produces="application/Json; charset=utf-8")
	public String weatherJson() {
		return fetchWeather("json");
	}
	
	private String fetchWeather(String dataType) {
		BufferedReader rd=null;
		HttpURLConnection conn = null;
		try {
			// 오늘 날짜 구하기
		LocalDate today = LocalDate.now();
		String baseDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String baseTime = "0500";
		String serviceKey = "3PcNXazVlEiN7ZYm3DV1RE+i741pL7ssZrvSrhtmP6gnr3bOnDWdvWIB4T9iMmaWSZ5J1uMWlyZB2KvYKD4+tA==";
	
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));/*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*‘21년 6월 28일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*서울예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
      
        URL url = new URL(urlBuilder.toString());
       
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
       System.out.println(sb.toString());
        	return sb.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				 rd.close();
			     conn.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
