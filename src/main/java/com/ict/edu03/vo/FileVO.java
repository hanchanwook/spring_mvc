package com.ict.edu03.vo;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	// DB 컬럼은 fname 이라고 가정하자
	private String name, fname;
	// uploadForm 파일의 파라미터이름 f_name
	private MultipartFile f_name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public MultipartFile getF_name() {
		return f_name;
	}

	public void setF_name(MultipartFile f_name) {
		this.f_name = f_name;
	}

}
