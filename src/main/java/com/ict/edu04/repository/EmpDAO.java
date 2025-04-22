package com.ict.edu04.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu04.vo.EmpVO;

@Repository
public class EmpDAO {
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List<EmpVO> getAllEmp() {
		try {			
			return sessionTemplate.selectList("emp.getAllEmp");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public List<EmpVO> getDeptnoSearch(EmpVO evo) {
		try {			
			return sessionTemplate.selectList("emp.getDeptnoSearch", evo);
		} catch (Exception e) {
			e.printStackTrace();
		return null;
		}
	}
	
	public List<EmpVO> getDynamicSearch(String idx, String keyword) {
		try {			
			// 파라미터가 여러 개이면 vo를 사용하든가 map을 사용해야 한다.
			Map<String, String> map = new HashMap<String, String>();
			map.put("idx", idx);
			map.put("keyword", keyword);
			return sessionTemplate.selectList("emp.getDynamicSearch", map);
		} catch (Exception e) {
			e.printStackTrace();
		return null;
		}
	}

	
	public List<EmpVO> getJobsSearch(List<String> jobs) {
		try {			
			return sessionTemplate.selectList("emp.getJobsSearch", jobs);
		} catch (Exception e) {
			e.printStackTrace();
		return null;
		}
	}

	
	public List<EmpVO> getEmpNoNameSearch(EmpVO evo) {
		try {			
			return sessionTemplate.selectList("emp.getEmpNoNameSearch", evo);
		} catch (Exception e) {
			e.printStackTrace();
		return null;
		}
		
	}
}
