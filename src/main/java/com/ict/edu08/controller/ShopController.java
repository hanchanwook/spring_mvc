package com.ict.edu08.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu08.service.ShopService;
import com.ict.edu08.vo.CartVO;
import com.ict.edu08.vo.ShopVO;
import com.ict.edu09.vo.MembersVO;

@Controller
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/day08")
	public ModelAndView getShopList(String category) {
		try {
			ModelAndView mv = new ModelAndView();
			if(category == null) {
				category = "ele002";
			}
			
			List<ShopVO> shop_list = shopService.getShopList(category);
			
			if(shop_list != null) {
				mv.addObject("shop_list", shop_list);
				mv.setViewName("day08/product_list");
				return mv;
			}else {
				mv.setViewName("day08/error");
				return mv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	
	@GetMapping("/shop_detail")
	public ModelAndView getShopDetail(@RequestParam("shop_idx") String shop_idx) {
		try {
			ModelAndView mv = new ModelAndView();
			ShopVO svo = shopService.getShopDetail(shop_idx);
			if( svo != null ) {
				mv.addObject("svo", svo );
				mv.setViewName("day08/product_content");
				return mv;
			}else {
				mv.setViewName("day08/error");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	
	@GetMapping("/shop_show_cart")
	public ModelAndView getShopShowCart(HttpSession session) {
		try {
			String loginchk  =  (String) session.getAttribute("loginchk");
			if("ok".equals(loginchk)) {
				ModelAndView mv = new ModelAndView();
				
				// 로그인한 사람의 정보를 가져와서 cart 테이블에서 검색 후 cartList에 넘기자 
				MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
				List<CartVO> cart_list = shopService.getCartList(membersVO.getM_id());
				
				if(cart_list != null) {
					mv.addObject("cart_list", cart_list);
				}
				
				mv.setViewName("day08/cartList");
				return mv;
			}else {
				session.setAttribute("loginchk", "required");
				return new ModelAndView("redirect:/members_loginForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/members_loginForm");
		}
	}
	
	@GetMapping("/shop_add_cart")
	public ModelAndView shopAddCart(@ModelAttribute("shop_idx") String shop_idx,
			HttpSession session) {
		try {
			String loginchk  =  (String) session.getAttribute("loginchk");
			if("ok".equals(loginchk)) {
				ModelAndView mv = new ModelAndView();
				
				// 사용자 정보 
				MembersVO mvo = (MembersVO)session.getAttribute("membersVO");
			
				// 제품정보가져오기
				ShopVO svo = shopService.getShopDetail(shop_idx);
				
				// cart 테이블에 사용자ID 에 속하는 제품번호가 있는지  검색
				int result = shopService.getCartChk(mvo.getM_id(), svo.getP_num());
				
				if(result == 0) {
					// 카트 테이블에 추가 (insert)
					CartVO cvo = new CartVO();
					cvo.setP_num(svo.getP_num());
					cvo.setP_name(svo.getP_name());
					cvo.setP_price(String.valueOf(svo.getP_price()));
					cvo.setP_saleprice(String.valueOf(svo.getP_saleprice()));
					cvo.setM_id(mvo.getM_id());
					shopService.getCartInsert(cvo);
				}else{
					// 카트 테이블에 수량 만 증가 (update)
					shopService.getCartUpdate(mvo.getM_id(), svo.getP_num());
				}
				
				mv.setViewName("redirect:/shop_detail");
				return mv;
			}else {
				session.setAttribute("loginchk", "required");
				return new ModelAndView("redirect:/members_loginForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/members_loginForm");
		}
	}
	
	@PostMapping("/cart_edit")
	public ModelAndView getCartEdit(CartVO cartVO) {
		try {
			ModelAndView mv = new ModelAndView();
			
			shopService.getCartEdit(cartVO);
			
			mv.setViewName("redirect:/shop_show_cart");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	@GetMapping("/cart_delete")
	public ModelAndView getCartDelete(@RequestParam("cart_idx") String cart_idx) {
		try {
			ModelAndView mv = new ModelAndView();
			
			shopService.getCartDelete(cart_idx);
			
			mv.setViewName("redirect:/shop_show_cart");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	@GetMapping("/shop_product_add_form")
	public ModelAndView getshopProductAddForm() {
		return new ModelAndView("day08/product_add");
	}
	
	@PostMapping("/shop_product_add_ok")
	public ModelAndView getshopProductAddOK(ShopVO shopVO, 
			HttpServletRequest request,
			@ModelAttribute("category") String category) {
		try {
			ModelAndView mv = new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/images/");
			MultipartFile file_s = shopVO.getFile_s();
			MultipartFile file_l = shopVO.getFile_l();
			
			// 둘다 무조건 넘어온다.
			UUID uuid = UUID.randomUUID();
			shopVO.setP_image_s(uuid.toString()+"_"+file_s.getOriginalFilename());
			shopVO.setP_image_l(uuid.toString()+"_"+file_l.getOriginalFilename());
			
			file_s.transferTo(new File(path, shopVO.getP_image_s()));
			file_l.transferTo(new File(path, shopVO.getP_image_l()));
			
			// DB 에 저장
			int result = shopService.getProductInsert(shopVO);
			if(result >= 1) {
				mv.setViewName("redirect:/day08");
				return mv;
			}else {
				mv.setViewName("redirect:/shop_product_add_form");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
}






