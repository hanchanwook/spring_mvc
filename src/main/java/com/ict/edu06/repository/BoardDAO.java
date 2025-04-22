package com.ict.edu06.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu06.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("board.count");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<BoardVO> getBoardList(int limit, int offset) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("limit", limit);
			map.put("offset", offset);
			return sqlSessionTemplate.selectList("board.list", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getBoardInsert(BoardVO boardVO) {
		try {
			return sqlSessionTemplate.insert("board.insert", boardVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getBoardHit(String b_idx) {
		try {
			return sqlSessionTemplate.update("board.hitup", b_idx);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	public BoardVO getBoardDetail(String b_idx) {
		try {
			return sqlSessionTemplate.selectOne("board.detail", b_idx);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getLevUp(Map<String, Integer> map) {
		try {
			return sqlSessionTemplate.update("board.levup", map);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;

		}
	}
	
	public int getBoardAnsInsert(BoardVO boardVO) {
		try {
			return sqlSessionTemplate.insert("board.ansinsert", boardVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public int getBoardDelete(String b_idx) {
		try {
			return sqlSessionTemplate.update("board.delete", b_idx);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getBoardUpdate(BoardVO boardVO) {
		try {
			return sqlSessionTemplate.update("board.update", boardVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
}
