package com.ict.edu08.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu08.vo.CartVO;
import com.ict.edu08.vo.ShopVO;

@Repository
public class ShopDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<ShopVO> getShopList(String category) {
		try {
			return sqlSessionTemplate.selectList("shop.list", category);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public ShopVO getShopDetail(String shop_idx) {
		try {
			return sqlSessionTemplate.selectOne("shop.detail", shop_idx);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<CartVO> getCartList(String m_id) {
		try {
			return sqlSessionTemplate.selectList("shop.cartlist", m_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getCartChk(String m_id, String p_num) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("m_id", m_id);
			map.put("p_num", p_num);
			return sqlSessionTemplate.selectOne("shop.cartchk", map);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public void getCartInsert(CartVO cvo) {
		try {
			sqlSessionTemplate.insert("shop.cartinsert", cvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getCartUpdate(String m_id, String p_num) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("m_id", m_id);
			map.put("p_num", p_num);
			sqlSessionTemplate.update("shop.cartupdate", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getCartEdit(CartVO cartVO) {
		try {
			sqlSessionTemplate.update("shop.cartedit", cartVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getCartDelete(String cart_idx) {
		try {
			sqlSessionTemplate.delete("shop.cartdelete", cart_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getProductInsert(ShopVO shopVO) {
		try {
			return sqlSessionTemplate.insert("shop.productinsert", shopVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
