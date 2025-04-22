package com.ict.edu03.service;

import java.util.List;

import com.ict.edu03.vo.GuestBookVO;

public interface GuestBookService {
	// list
	public List<GuestBookVO> getGuestBookList();
	
	// write
	public int getGuestBookInsert(GuestBookVO gbvo);
	
	// detail
	public GuestBookVO getGuestBookDetail(GuestBookVO gbvo);
	
	// update
	public int getGuestBookUpdate(GuestBookVO gbvo);
	
	// delete
	public int getGuestBookDelete(GuestBookVO gbvo);
}
