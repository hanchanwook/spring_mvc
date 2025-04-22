package com.ict.edu03.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu03.vo.GuestBookVO;

@Repository
public class GuestBookDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<GuestBookVO> getGuestBookList() {
		try {
			return sqlSessionTemplate.selectList("guestbook.list");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public int getGuestBookInsert(GuestBookVO gbvo) {
		try {
			return sqlSessionTemplate.insert("guestbook.insert", gbvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public GuestBookVO getGuestBookDetail(GuestBookVO gbvo) {
		try {
			return sqlSessionTemplate.selectOne("guestbook.detail", gbvo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public int getGuestBookUpdate(GuestBookVO gbvo) {
		try {
			return sqlSessionTemplate.update("guestbook.update", gbvo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}


	public int getGuestBookDelete(GuestBookVO gbvo) {
		try {
			return sqlSessionTemplate.delete("guestbook.delete", gbvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
