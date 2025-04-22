package com.ict.edu05.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu05.vo.BbsVO;
import com.ict.edu05.vo.CommVO;

@Repository
public class BbsDAO {
	
	@Autowired SqlSessionTemplate sessionTemplate;
	
	
	public List<BbsVO> getBbsList() {
		try {
			return sessionTemplate.selectList("bbs.list");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public int getBbsInsert(BbsVO bvo) {
		try {
			return sessionTemplate.insert("bbs.insert",bvo);
		} catch (Exception e) {
		e.printStackTrace();
		return 0;
		}
		
	}

	
	public BbsVO getBbsDetail(BbsVO bvo) {
		try {
			return sessionTemplate.selectOne("bbs.detail",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public int getBbsDelete(BbsVO bvo) {
		try {
			return sessionTemplate.update("bbs.delete",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public int getBbsUpdate(BbsVO bvo) {
		try {
			return sessionTemplate.update("bbs.update",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public int getBbsHitUpdate(BbsVO bvo) {
		try {
			return sessionTemplate.update("bbs.hitup",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public List<CommVO> getCommentList(String b_idx) {
		try {
			return sessionTemplate.selectList("bbs.commList", b_idx);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public int getCommentInsert(CommVO cvo) {
		try {
			return sessionTemplate.insert("bbs.commInsert", cvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public int getCommentUpdate(CommVO cvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getCommentDelete(CommVO cvo) {
		try {
			return sessionTemplate.delete("bbs.commDelete", cvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// 페이징 기법
	
	public int getTotalCount() {
		try {
			return sessionTemplate.selectOne("bbs.totalCount");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<BbsVO> getBbsList(int limit, int offset) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("limit", limit);
			map.put("offset", offset);
			
			return sessionTemplate.selectList("bbs.listPage", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
