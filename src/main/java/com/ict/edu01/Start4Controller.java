package com.ict.edu01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start4Controller {
	
	// URL 매핑
	// @RequestMapping(value = "url", method = RequestMethod.GET)
	// @RequestMapping(value = "url", method = RequestMethod.POST)
	
	// @RequestMapping("url")
	// @GetMapping("url")
	// @PostMapping("url")
	
	@GetMapping("/start4")
	public ModelAndView start4() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("city", "seoul");
		
		mv.setViewName("result2");
		return mv;
	}
	
	@RequestMapping("/start5")
	public ModelAndView start5() {
		ModelAndView mv = new ModelAndView("day01/result1");
		mv.addObject("city", "seoul");
		return mv;			
	}
	
	@PostMapping("/start6")
	public ModelAndView start6() {
		return new ModelAndView("result2");	
	}
	
}
