package com.ict.edu05.service;

import java.util.List;

import com.ict.edu05.vo.BbsVO;
import com.ict.edu05.vo.CommVO;

public interface BbsService {
	// 리스트
	public List<BbsVO> getBbsList();
	// 원글 삽입
	public int getBbsInsert(BbsVO bvo);
	// 원글 상세 보기
	public BbsVO getBbsDetail(BbsVO bvo);
	// 원글 삭제
	public int getBbsDelete(BbsVO bvo);
	// 원글 수정
	public int getBbsUpdate(BbsVO bvo);
	// 조회수 업데이트
	public int getBbsHitUpdate(BbsVO bvo);
	//  댓글 리스트
	public List<CommVO> getCommentList(String b_idx);
	//	댓글 삽입
	public int getCommentInsert(CommVO cvo);
	//	댓글 수정
	public int getCommentUpdate(CommVO cvo);;
	//	댓글 삭제
	public int getCommentDelete(CommVO cvo);
	
	// 페이지 기법
	public int getTotalCount();
	
	public List<BbsVO> getBbsList(int limit, int offset);
	

}
