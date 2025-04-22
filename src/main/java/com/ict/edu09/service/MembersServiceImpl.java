package com.ict.edu09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu09.repository.MembersDAO;
import com.ict.edu09.vo.MembersVO;

@Service
public class MembersServiceImpl implements MembersService{
	@Autowired
	private MembersDAO membersDAO;
	
	@Override
	public MembersVO getMembersLogInOK(String m_id) {
		return membersDAO.getMembersLogInOK(m_id);
	}

	@Override
	public int getMembersJoinOK(MembersVO mvo) {
		return membersDAO.getMembersJoinOK(mvo);
	}

}
