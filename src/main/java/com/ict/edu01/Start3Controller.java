package com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Start3Controller implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 일처리 => 서비스에서 한다.

		// 일처리 후의 결과 저장 하기
		request.setAttribute("name", "홍길동");

		ModelAndView mv = new ModelAndView();
		// 갈 곳 지정 (결과를 보여줄 jsp 이름)
		mv.setViewName("result1");
		// request 처럼 데이터 저장
		mv.addObject("addr", "활빈당");

		return mv;
	}

}
