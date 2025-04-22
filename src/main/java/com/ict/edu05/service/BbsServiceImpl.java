package com.ict.edu05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu05.repository.BbsDAO;
import com.ict.edu05.vo.BbsVO;
import com.ict.edu05.vo.CommVO;

@Service
public class BbsServiceImpl implements BbsService {
	
	@Autowired BbsDAO bbsDAO;

	@Override
	public List<BbsVO> getBbsList() {
		return bbsDAO.getBbsList();
	}

	@Override
	public int getBbsInsert(BbsVO bvo) {
		
		return bbsDAO.getBbsInsert(bvo);
	}

	@Override
	public BbsVO getBbsDetail(BbsVO bvo) {
		
		return bbsDAO. getBbsDetail(bvo);
	}

	@Override
	public int getBbsDelete(BbsVO bvo) {
		return bbsDAO.getBbsDelete(bvo);
	}

	@Override
	public int getBbsUpdate(BbsVO bvo) {
		return bbsDAO.getBbsUpdate(bvo);
	}

	@Override
	public int getBbsHitUpdate(BbsVO bvo) {
	
		return bbsDAO.getBbsHitUpdate(bvo);
	}

	@Override
	public List<CommVO> getCommentList(String b_idx) {
		return bbsDAO.getCommentList(b_idx);
	}

	@Override
	public int getCommentInsert(CommVO cvo) {
		return bbsDAO.getCommentInsert(cvo);
	}

	@Override
	public int getCommentUpdate(CommVO cvo) {
		return 0;
	}

	@Override
	public int getCommentDelete(CommVO cvo) {		
		return bbsDAO.getCommentDelete(cvo);
	}
	
	// 페이징 기법
	@Override
	public int getTotalCount() {
		return bbsDAO.getTotalCount();
	}
	
	@Override
	public List<BbsVO> getBbsList(int limit, int offset) {
		return bbsDAO.getBbsList(limit,offset);
	}


}
