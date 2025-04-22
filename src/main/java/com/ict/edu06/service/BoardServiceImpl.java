package com.ict.edu06.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu06.repository.BoardDAO;
import com.ict.edu06.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return boardDAO.getTotalCount();
	}
	
	
	public List<BoardVO> getBoardList(int limit, int offset){
		return boardDAO.getBoardList(limit, offset);
	}


	@Override
	public int getBoardInsert(BoardVO boardVO) {
		return boardDAO.getBoardInsert(boardVO);
	}


	@Override
	public int getBoardHit(String b_idx) {
		return boardDAO.getBoardHit(b_idx);
	}


	@Override
	public BoardVO getBoardDetail(String b_idx) {
		return boardDAO.getBoardDetail(b_idx);
	}


	@Override
	public int getLevUp(Map<String, Integer> map) {
		
		return boardDAO.getLevUp(map);
	}


	@Override
	public int getBoardAnsInsert(BoardVO boardVO) {
	
		return boardDAO.getBoardAnsInsert(boardVO);
	}


	@Override
	public int getBoardDelete(String b_idx) {
		return boardDAO.getBoardDelete(b_idx);
	}


	@Override
	public int getBoardUpdate(BoardVO boardVO) {
		return boardDAO.getBoardUpdate(boardVO);
	}
	
	
	
	
	

}
