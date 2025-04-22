package com.ict.edu09.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu09.vo.MembersVO;

@Repository
public class MembersDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public MembersVO getMembersLogInOK(String m_id) {
		try {
			return sqlSessionTemplate.selectOne("members.loginchk", m_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public int getMembersJoinOK(MembersVO mvo) {
		try {
			return sqlSessionTemplate.insert("members.join", mvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
