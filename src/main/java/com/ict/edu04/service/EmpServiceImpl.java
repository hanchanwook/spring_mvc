package com.ict.edu04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu04.repository.EmpDAO;
import com.ict.edu04.vo.EmpVO;
@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDAO empDAO;

	@Override
	public List<EmpVO> getAllEmp() {
		
		return empDAO.getAllEmp();
	}

	@Override
	public List<EmpVO> getDeptnoSearch(EmpVO evo) {
		
		return empDAO.getDeptnoSearch(evo);
	}

	@Override
	public List<EmpVO> getDynamicSearch(String idx, String keyword) {
		
		return empDAO.getDynamicSearch(idx, keyword);
	}

	@Override
	public List<EmpVO> getJobsSearch(List<String> jobs) {
		
		return empDAO.getJobsSearch(jobs);
	}

	@Override
	public List<EmpVO> getEmpNoNameSearch(EmpVO evo) {
		return empDAO.getEmpNoNameSearch(evo);
	}

}
