package com.ict.edu07.service;

import java.io.InputStream;
import java.util.Properties;

public class EmailUtil {
	//	properties 파일을 읽는 방법
	private static Properties props = new Properties();
	
	static {
		try {
			InputStream input = 
					EmailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
			props.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		
		return props.getProperty(key);
		
	}
	
}
