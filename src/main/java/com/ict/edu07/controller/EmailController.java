package com.ict.edu07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {
	
	@GetMapping("/day07")
	public ModelAndView getEmailIndex() {
		return new ModelAndView("day07/index");
	}

}
