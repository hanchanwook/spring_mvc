package com.ict.edu02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu02.service.BookService;
import com.ict.edu02.service.CalcService;
import com.ict.edu02.vo.BookVO;
import com.ict.edu02.vo.CalcVO;
import com.ict.edu02.vo.MembersVO;

@Controller
public class Day2Controller {
	
	// @Autowired(Spring) / @Inject(java)
	
	@Autowired // @service랑 연결시켜준다
	private CalcService calcservice;
	
	@Autowired
	private BookService bookservice;
	
	@GetMapping("/day02")
	public ModelAndView getForm() {
		
		return new ModelAndView("day02/input"); // 뷰 네임만 가지고 이동
	}
	
	/* HttpServletRequest request 사용
	@PostMapping("/logIn01")
	public ModelAndView getlogIn01(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView();
		String userId= request.getParameter("userId"); // 폼태그 받아옴
		String userPw= request.getParameter("userPw"); 
		
		mv.addObject("userId", userId);
		mv.addObject("userPw", userPw);// 받아서 저장
		mv.setViewName("day02/result01");
		
		return mv;
	}
	*/
	// vo사용
	@PostMapping("/logIn01")
	public ModelAndView getlogIn01(MembersVO mvo) {
		ModelAndView mv= new ModelAndView();
		
		// 원래는 DB 로그인 처리
		
		mv.addObject("userId", mvo.getUserId());
		mv.addObject("userPw", mvo.getUserPw());// 받아서 저장
		
		mv.addObject("mvo",mvo);
		mv.setViewName("day02/result01");
		
		return mv;
	}
	/*
	@GetMapping("/calc")
	public ModelAndView getCalc(CalcVO cvo) {
		ModelAndView mv= new ModelAndView();
		
		
		int su1= Integer.parseInt(cvo.getSu1());
		int su2= Integer.parseInt(cvo.getSu2());
		int result= 0;
		switch (cvo.getOp()) {
			case "+": result= su1+ su2; break;
			case "-": result= su1- su2; break;
			case "*": result= su1* su2; break;
			case "/": result= su1/ su2; break;
		}
		
		// CalcService cs= new CalcService(); // 객체를 생성하지 않고 @service로 객체 생성
		int result= calcservice.getCalc(cvo);
		
		mv.addObject("cvo", cvo);
		mv.addObject("result",result);
		mv.setViewName("day02/result02");
		return mv;
	}
	*/
	
	@GetMapping("/calc")
	public ModelAndView getCalc(@ModelAttribute("cvo") CalcVO cvo2) {
		// @ModelAttribute("cvo") => 클라이언트 요청의 파라미터를 자바 객체에 자동 바인딩 역할
		ModelAndView mv= new ModelAndView();
		
		/*
		int su1= Integer.parseInt(cvo.getSu1());
		int su2= Integer.parseInt(cvo.getSu2());
		int result= 0;
		switch (cvo.getOp()) {
			case "+": result= su1+ su2; break;
			case "-": result= su1- su2; break;
			case "*": result= su1* su2; break;
			case "/": result= su1/ su2; break;
		}
		 */
		// CalcService cs= new CalcService(); // 객체를 생성하지 않고 @service로 객체 생성
		int result= calcservice.getCalc(cvo2);
		
		//mv.addObject("cvo", cvo); => @ModelAttribute("cvo")가 대신 하는즁
		mv.addObject("result",result);
		mv.setViewName("day02/result02");
		return mv;
	}
	
	@RequestMapping("/hobby")
	public ModelAndView getHobby(@ModelAttribute("cvo") CalcVO cvo) {
		ModelAndView mv= new ModelAndView();
		
		
		mv.setViewName("day02/result03");
		return mv;
	}
	
	@RequestMapping("/bookList")
	public ModelAndView getBookList() {
		ModelAndView mv= new ModelAndView();
		List<BookVO> list =  bookservice.getBookList();
		mv.addObject("list", list);
		mv.setViewName("day02/result04");
		return mv;
	}
	
	@GetMapping("/bookdetail")
	public ModelAndView getBookDetail(BookVO bvo) {
		ModelAndView mv = new ModelAndView();
		BookVO bookvo = bookservice.getBookDetail(bvo);
		mv.addObject("bvo", bookvo);
		mv.setViewName("day02/result05");
		return mv;
	}
	
	@GetMapping("/bookdelete")
	public ModelAndView getBookDelete(BookVO bvo) {
		ModelAndView mv = new ModelAndView();
		
		int result = bookservice.getBookDelete(bvo);
		
		// servlet-context.xml 에 리턴 후 redirect 때문에 
		// servlet-context.xml 와서 /bookList 를 찾아 간다.
		mv.setViewName("redirect:/bookList");
		// mv.setViewName("forward:/bookList");
		return mv;
	}
	
	@GetMapping("/bookupdate")
	public ModelAndView getBookUpdate(BookVO bvo) {
		ModelAndView mv = new ModelAndView();
		BookVO bookvo = bookservice.getBookDetail(bvo);
		mv.addObject("bvo", bookvo);
		mv.setViewName("day02/result06");
		return mv;
	}
	@PostMapping("/bookupdate_ok")
	public ModelAndView getBookUpdate_ok(BookVO bvo) {
		ModelAndView mv = new ModelAndView();
		int result = bookservice.getBookUpdate_ok(bvo);
		mv.setViewName("redirect:/bookdetail?bookid="+bvo.getBookid());
		return mv;
	}

	@GetMapping("/guestbookList")
    public ModelAndView getGuestBookList() {
        ModelAndView mv = new ModelAndView();
        // 필요한 데이터 처리
        mv.setViewName("guestbook/list");
        return mv;
    }
	

	
}
