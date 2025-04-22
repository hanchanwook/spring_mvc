package com.ict.edu04.service;

import java.util.List;

import com.ict.edu04.vo.EmpVO;

public interface EmpService {
	List<EmpVO> getAllEmp();
	List<EmpVO> getDeptnoSearch(EmpVO evo);
	List<EmpVO> getDynamicSearch(String idx, String keyword);
	List<EmpVO> getJobsSearch(List<String> jobs);
	List<EmpVO> getEmpNoNameSearch(EmpVO evo);

}
