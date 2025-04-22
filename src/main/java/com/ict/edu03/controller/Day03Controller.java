package com.ict.edu03.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu03.vo.FileVO;

@Controller
public class Day03Controller {
	
	
	@GetMapping("/day03") /* 파일 업로드 폼 페이지 요청 처리 */
	public ModelAndView getFileUploadForm() {
		return new ModelAndView("day03/uploadForm"); /*  /WEB-INF/views/day03/uploadForm.jsp 로 이동 */
	}
	
	@PostMapping("/f_up")	/* 업로드 처리 방법 (방법1: MultipartFile 직접 사용) */
	public ModelAndView fileUp(
		@RequestParam("f_name") MultipartFile f_name,	/* 업로드 된 파일 */	
		HttpServletRequest request	) {
		try {
			ModelAndView mv = new ModelAndView();
			// 업로드 위치(저장 경로-웹 서버 내 실제 경로)
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
			// 같은이름의 파일을 업로드하면 업로드가 되지 않는다.
			// 보통 해결책은 파일이름 앞이나 뒤에 id와 업로드 날짜 붙인다
			// 아니면 UUID를 생성해서 붙인다.
			// UUID(범용 고유 식별자(Universally Unique Identifiers)
			// 각 개체를 고유하게 식별 가능한 값을 말한다
			// 데이터가 많아지면 완전히 중복을 방지하지 않으므로 보통 id를 추가한다.
			UUID uuid = UUID.randomUUID();	/*파일명중복방지*/
			String fname = uuid.toString()+"_"+f_name.getOriginalFilename();
			String name = request.getParameter("name");		/* 사용자 입력값 */
			// 프로젝트를 tomcat에 실제로 올리기 전까지는 
			// sts3이 만든 가상 tomcat에 올라간다. 
			String savePath = path+fname; // 저장 될 전체경로 (위치와 파일이름)
			String contentType = f_name.getContentType();	/* 파일 정보 */
			long size = f_name.getSize()/1024;
			
			// 실제 파일 저장
			f_name.transferTo(new File(savePath));
			
			/* 결과 페이지로 데이터 전달 */
			mv.addObject("name", name);
			mv.addObject("fname", fname);
			mv.addObject("savePath", savePath);
			mv.addObject("contentType", contentType);
			mv.addObject("size", size);
			
			mv.setViewName("day03/result01");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day03/error");
		}
	}
	
	/* 파일 다운로드 처리 */
	@GetMapping("/down")
	public void fileDown(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			/* 요청한 파일 이름 */
			String fname = request.getParameter("fname");
			// 업로드 위치 (실제 위치)
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+fname);
			String r_path = URLEncoder.encode(fname, "utf-8");	/* 파일 이름 인코딩(한글 파일명 깨짐 방지) */
			
			// 응답 헤더 (브라우저) 설정 (HttpServletResponse response) 브라우저가 다운로드로 인식하도록
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition",
					"attachment; filename="+ r_path);
			
			// 실제 파일 입출력
			File file  = new File(new String(path.getBytes(),"utf-8"));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			
			/* 파일을 복사해서 전송 */
			FileCopyUtils.copy(in, out);
			response.getOutputStream().flush();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/* 파일 업로드 처리 (방법2: VO 사용 - FileVO 클래스 활용) */
	@PostMapping("/f_up2")
	public ModelAndView fileUp2(FileVO fileVO,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			// 업로드 저장 위치
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
			UUID uuid = UUID.randomUUID();	/* 파일명 중복 방지용 UUID */
			String fname = uuid.toString()+"_"+fileVO.getF_name().getOriginalFilename();
			String name = request.getParameter("name");		/* 사용자 입력 */
			
			String savePath = path+fname; // 전체 저장위치와 파일이름
			String contentType = fileVO.getF_name().getContentType();
			long size = fileVO.getF_name().getSize()/1024;
			
			// 실제 업로드 코드
			fileVO.getF_name().transferTo(new File(savePath));
			
			/* 결과 페이지로 정보 전달 */
			mv.addObject("name", name);
			mv.addObject("fname", fname);
			mv.addObject("savePath", savePath);
			mv.addObject("contentType", contentType);
			mv.addObject("size", size);
			
			
			mv.setViewName("day03/result01");
			return mv;
		} catch (Exception e) {
		 return new ModelAndView("day03/error");
		}
	}
	

	
}








