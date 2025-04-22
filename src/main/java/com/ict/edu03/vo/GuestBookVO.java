package com.ict.edu03.vo;

import org.springframework.web.multipart.MultipartFile;

public class GuestBookVO {
	private String 
		gb_idx ,
		gb_name ,
		gb_subject  ,
		gb_content   ,
		gb_email ,
		gb_f_name   ,
		gb_regdate ,
		gb_pw  ,
		gb_old_f_name ;
	
	private MultipartFile gb_file_name;

	public String getGb_idx() {
		return gb_idx;
	}

	public void setGb_idx(String gb_idx) {
		this.gb_idx = gb_idx;
	}

	public String getGb_name() {
		return gb_name;
	}

	public void setGb_name(String gb_name) {
		this.gb_name = gb_name;
	}

	public String getGb_subject() {
		return gb_subject;
	}

	public void setGb_subject(String gb_subject) {
		this.gb_subject = gb_subject;
	}

	public String getGb_content() {
		return gb_content;
	}

	public void setGb_content(String gb_content) {
		this.gb_content = gb_content;
	}

	public String getGb_email() {
		return gb_email;
	}

	public void setGb_email(String gb_email) {
		this.gb_email = gb_email;
	}

	public String getGb_f_name() {
		return gb_f_name;
	}

	public void setGb_f_name(String gb_f_name) {
		this.gb_f_name = gb_f_name;
	}

	public String getGb_regdate() {
		return gb_regdate;
	}

	public void setGb_regdate(String gb_regdate) {
		this.gb_regdate = gb_regdate;
	}

	public String getGb_pw() {
		return gb_pw;
	}

	public void setGb_pw(String gb_pw) {
		this.gb_pw = gb_pw;
	}

	public String getGb_old_f_name() {
		return gb_old_f_name;
	}

	public void setGb_old_f_name(String gb_old_f_name) {
		this.gb_old_f_name = gb_old_f_name;
	}

	public MultipartFile getGb_file_name() {
		return gb_file_name;
	}

	public void setGb_file_name(MultipartFile gb_file_name) {
		this.gb_file_name = gb_file_name;
	}


	
	
}
