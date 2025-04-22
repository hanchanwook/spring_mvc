package com.ict.edu08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu08.repository.ShopDAO;
import com.ict.edu08.vo.CartVO;
import com.ict.edu08.vo.ShopVO;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDAO shopDAO;
	
	@Override
	public List<ShopVO> getShopList(String category) {
		return shopDAO.getShopList(category);
	}
	
	@Override
	public ShopVO getShopDetail(String shop_idx) {
		return shopDAO.getShopDetail(shop_idx);
	}
	
	@Override
	public List<CartVO> getCartList(String m_id) {
		return shopDAO.getCartList(m_id);
	}

	@Override
	public int getCartChk(String m_id, String p_num) {
		return shopDAO.getCartChk( m_id, p_num);
	}

	@Override
	public void getCartInsert(CartVO cvo) {
		shopDAO.getCartInsert(cvo);
	}

	@Override
	public void getCartUpdate(String m_id, String p_num) {
		shopDAO.getCartUpdate(m_id, p_num);
	}

	@Override
	public void getCartEdit(CartVO cartVO) {
		shopDAO.getCartEdit(cartVO);
		
	}

	@Override
	public void getCartDelete(String cart_idx) {
		shopDAO.getCartDelete(cart_idx);
	}
	
	@Override
	public int getProductInsert(ShopVO shopVO) {
		return shopDAO.getProductInsert(shopVO);
	}
}
