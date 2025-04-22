package com.ict.edu07.service;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	public String createCode() {
		Random ran = new Random();
		
		// 숫자 6자리
		return String.valueOf(100000 + ran.nextInt(900000));	//-899999
	}
	
	public void sendHtmlMail(String toEMail, String subject, String htmlContent) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", EmailUtil.get("mail.smtp.host"));
		props.put("mail.smtp.port", EmailUtil.get("mail.smtp.port"));
		props.put("mail.smtp.auth", EmailUtil.get("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", EmailUtil.get("mail.smtp.starttls.enable"));
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						EmailUtil.get("mail.smtp.username"),
						EmailUtil.get("mail.smtp.password")
					);
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(
				EmailUtil.get("mail.smtp.username"),
				EmailUtil.get("mail.sender.name"),
				"UTF-8"
				));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEMail));
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setContent(htmlContent, "text/html; charset=UTF-8");
		
		
		Transport.send(message);
	
	}
	
	public String sendAuthMail(String toEmail) throws Exception{
		String code = createCode();
		String html = "<h3>인증코드</h3><p><b>" + code + "</b></p><p>5분 이내에 입력해 주세요</p>";
		sendHtmlMail(toEmail, "[ICTEDU 서비스] 이메일 인증", html); 
		return code ;
	}
	
}
