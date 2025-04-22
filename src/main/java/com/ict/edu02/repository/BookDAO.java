package com.ict.edu02.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu02.vo.BookVO;

@Repository
public class BookDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;  //autocommit

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public List<BookVO> getBookList(){
		return sqlSessionTemplate.selectList("book.booklist");
	}
	
	public BookVO getBookDetail(BookVO bvo) {
		return sqlSessionTemplate.selectOne("book.bookdetail", bvo);
	}
	
	public int getBookDelete(BookVO bvo) {
		return sqlSessionTemplate.delete("book.bookdelete", bvo);
	}
	
	public int getBookUpdate_ok(BookVO bvo) {
		return sqlSessionTemplate.update("book.bookupdate", bvo);
	}
}
