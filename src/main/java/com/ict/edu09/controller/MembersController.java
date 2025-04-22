package com.ict.edu09.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu09.service.MembersService;
import com.ict.edu09.vo.MembersVO;

@Controller
public class MembersController {
	@Autowired
	private MembersService membersService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/members_loginForm")
	public ModelAndView getMembersLoginForm() {
		return new ModelAndView("day09/loginForm");
	}
	
	@PostMapping("/members_login_ok")
	public ModelAndView getMembersLoginOK(MembersVO mvo, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView();
			MembersVO membersVO = membersService.getMembersLogInOK(mvo.getM_id());
			if(membersVO == null) {
				// 아이디가 없어서 로그인 실패
				mv.addObject("loginchk", "fail");
				mv.setViewName("day09/loginForm");
				return mv;
			}else {
				// 비밀번호 검사 
				if(passwordEncoder.matches(mvo.getM_pw(), membersVO.getM_pw())) {
					// 로그인 성공 => session
					session.setAttribute("loginchk", "ok");
					
					// 일반유저와 관리자 구분
					if(membersVO.getM_id().equals("admin")) {
						session.setAttribute("admin", "ok");
					}
					session.setAttribute("membersVO", membersVO);
					mv.setViewName("redirect:/day08");
					return  mv;
				}else {
					mv.addObject("loginchk", "fail");
					mv.setViewName("day09/loginForm");
					return mv;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	@GetMapping("/members_logout")
	public ModelAndView getMembersLogout(HttpSession session) {
		// 세션 전체 초기화 
		// session.invalidate();
		
		// 필요한 정보만 삭제 
		session.removeAttribute("loginchk");
		session.removeAttribute("admin");
		session.removeAttribute("membersVO");
		
		
		return new ModelAndView("redirect:/day08");
	}
	
	@GetMapping("/members_join")
	public ModelAndView getMembersJoin() {
		return new ModelAndView("day09/joinForm");
	}
	
	@PostMapping("/members_join_ok")
	public ModelAndView getMembersJoinOK(MembersVO mvo) {
		try {
			mvo.setM_pw(passwordEncoder.encode(mvo.getM_pw()));
			int result = membersService.getMembersJoinOK(mvo);
			if(result>=1) {
				return new ModelAndView("redirect:/members_loginForm"); 
			}else {
				return new ModelAndView("redirect:/members_join");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
}
