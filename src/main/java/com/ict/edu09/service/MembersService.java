package com.ict.edu09.service;

import com.ict.edu09.vo.MembersVO;

public interface MembersService {
	public MembersVO getMembersLogInOK(String m_id);
	public int getMembersJoinOK(MembersVO mvo);
}
