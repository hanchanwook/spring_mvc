package com.ict.edu02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu02.repository.BookDAO;
import com.ict.edu02.vo.BookVO;

@Service
public class BookService {
	@Autowired
	private BookDAO bookDAO;
	
	public List<BookVO> getBookList(){
		return bookDAO.getBookList();
	}
	
	public BookVO getBookDetail(BookVO bvo) {
		return bookDAO.getBookDetail(bvo);
	}

	public int getBookDelete(BookVO bvo) {
		return bookDAO.getBookDelete(bvo);
	}
	
	public int getBookUpdate_ok(BookVO bvo) {
		return bookDAO.getBookUpdate_ok(bvo);
	}
}
