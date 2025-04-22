package com.ict.edu03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu03.repository.GuestBookDAO;
import com.ict.edu03.vo.GuestBookVO;

@Service
public class GuestBookServiceImpl implements GuestBookService{

	@Autowired
	private GuestBookDAO guestBookDAO;
	
	@Override
	public List<GuestBookVO> getGuestBookList() {
		return guestBookDAO.getGuestBookList();
	}

	@Override
	public int getGuestBookInsert(GuestBookVO gbvo) {
		return guestBookDAO.getGuestBookInsert(gbvo);
	}

	@Override
	public GuestBookVO getGuestBookDetail(GuestBookVO gbvo) {
		return guestBookDAO.getGuestBookDetail(gbvo);
	}

	@Override
	public int getGuestBookUpdate(GuestBookVO gbvo) {
		// TODO Auto-generated method stub
		return guestBookDAO.getGuestBookUpdate(gbvo);
	}

	@Override
	public int getGuestBookDelete(GuestBookVO gbvo) {
		// TODO Auto-generated method stub
		return guestBookDAO.getGuestBookDelete(gbvo);
	}

}
