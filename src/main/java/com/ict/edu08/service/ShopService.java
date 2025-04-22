package com.ict.edu08.service;

import java.util.List;

import com.ict.edu08.vo.CartVO;
import com.ict.edu08.vo.ShopVO;

public interface ShopService {
	public List<ShopVO>getShopList(String category);
	public ShopVO getShopDetail(String shop_idx);
	public List<CartVO> getCartList(String m_id);
	public int getCartChk(String m_id, String p_num);
	public void getCartInsert(CartVO cvo);
	public void getCartUpdate(String m_id, String p_num);
	public void getCartEdit(CartVO cartVO);
	public void getCartDelete(String cart_idx);
	public 	int getProductInsert(ShopVO shopVO);
}
