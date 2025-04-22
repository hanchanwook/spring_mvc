package com.ict.edu04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu04.service.EmpService;
import com.ict.edu04.vo.EmpVO;

@Controller
public class Day04Controller {
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/day04")
	public ModelAndView getDay04Index() {

		return new ModelAndView("day04/index");
	}
	
	@GetMapping("/dynamicForm")
	public ModelAndView getDynamicForm() {
		return new ModelAndView("day04/dynamicForm");
	}
	
	@PostMapping("/emp_list")
	public ModelAndView getEmpList() {
		ModelAndView mv = new ModelAndView("day04/dynamicForm");
		try {
			mv.addObject("emplist", empService.getAllEmp());
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", "전체 조회 오류");
		}
		return mv;
	}
	
	@PostMapping("/emp_search")
	public ModelAndView getEmpList(EmpVO evo) {
		ModelAndView mv = new ModelAndView("day04/dynamicForm");
		try {
			mv.addObject("emplist", empService.getDeptnoSearch(evo));
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", "부서 조회 오류");
		}
		return mv;
	}
	
	@PostMapping("/emp_dynamic_search")
	public ModelAndView getEmpDynamicList(
			@RequestParam("idx") String idx,
			@RequestParam("keyword") String keyword	) {
		
		ModelAndView mv = new ModelAndView("day04/dynamicForm");
		try {
			mv.addObject("emplist", empService.getDynamicSearch(idx, keyword));
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", "동적 조회 오류");
		}
		return mv;
	}	
	
	@PostMapping("/emp_by_jobs")
	public ModelAndView getJobsSearch(@RequestParam("jobs") List<String> jobs) {
		
		ModelAndView mv = new ModelAndView("day04/dynamicForm");
		try {
			mv.addObject("emplist", empService.getJobsSearch(jobs));
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", "동적 조회 오류");
		}
		return mv;
	}	
	
	@PostMapping("/emp_by_no_name")
	public ModelAndView getEmpNoNameSearch(EmpVO evo) {
		
		ModelAndView mv = new ModelAndView("day04/dynamicForm");
		try {
			mv.addObject("emplist", empService.getEmpNoNameSearch(evo));
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("error", "조회 오류");
		}
		return mv;
	}
	
	@GetMapping("/ajax01Form")
	public ModelAndView getAjax01Form() {
		return new ModelAndView("day04/ajax01Form");
	}
	
}


















