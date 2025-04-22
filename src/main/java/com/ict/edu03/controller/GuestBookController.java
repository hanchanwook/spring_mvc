package com.ict.edu03.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu03.service.GuestBookService;
import com.ict.edu03.vo.GuestBookVO;

@Controller
public class GuestBookController {	
	
	/* 0. GuestBookService 객체를 스프링이 자동으로 주입해줌 (DI - 의존성 주입) */
	@Autowired
	private GuestBookService guestBookService;
	
	/* 1. 방명록 전체 목록 조회 */
	@GetMapping("/guestBookList")
	public ModelAndView getGuestBookList() {
		ModelAndView mv = new ModelAndView();	/* 응답 페이지 정보 저장 객체 생성 */
		List<GuestBookVO> list = guestBookService.getGuestBookList();	/* DB에서 전체 방명록 목록 조회 */
		mv.addObject("list", list);		/* 뷰(JSP)에서 사용할 데이터 추가 */
		mv.setViewName("day03/list");	/* 보여줄 jsp 파일 이름 지정 */
		return mv;	/* ModelAndView 객체 반환 (뷰와 데이터 함께 전달) */
	}
	
	/* 2. 방명록 작성 폼으로 이동 */
	@GetMapping("/guestBookWrite")
	public ModelAndView guestBookWrite() {
		return new ModelAndView("day03/write");	/* write.jsp로 이동 */
	}
	
	/* 3. 방명록 작성 처리 (form 데이터와 파일 업로드 처리) */
	@PostMapping("/guestBookWriteOK")
	public ModelAndView guestBookWriteOK(GuestBookVO gbvo,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();	/* 결과 페이지 설정을 위한 객체 */
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");	/* 업로드 파일이 저장될 실제 서버 경로 가져오기 */
			MultipartFile file = gbvo.getGb_file_name();	/* VO에서 업로드된 파일 가져오기 */
			
			if(file.isEmpty()) {		/* 파일이 비어있으면 (첨부 안했으면), 빈 문자열로 설정 */
				gbvo.setGb_f_name("");	/* 파일이 없을 경우 빈 문자열로 설정 */
			}else {
				UUID uuid = UUID.randomUUID();	/* 파일이 존재하면 처리 */
				String f_name = uuid.toString()+"_"+file.getOriginalFilename();	/* 중복 방지를 위한 파일명 생성 */
				gbvo.setGb_f_name(f_name);		/* VO에 파일명 저장 */
				
				file.transferTo(new File(path, f_name));	// 실제 파일 업로드 수행
			}
			
			// 비밀번호 암호화(다음에)
			
			int result = guestBookService.getGuestBookInsert(gbvo);	/* DB에 저장 요청 */
			if(result > 0) {
				mv.setViewName("redirect: /guestBookList");		/* 저장 성공 시 목록 페이지로 리다이렉트 */
				return mv;
			}
			return new ModelAndView("day03/error");		/* 실패 시 에러 페이지 */
		} catch (Exception e) {
			return new ModelAndView("day03/error");		/* 예외 발생 시 에러 페이지로 이동 */
		}
		
	}
	
	/* 4. 방명록 상세보기 */
	@GetMapping("/guestBookDetail")
	public ModelAndView guestBookDetail(GuestBookVO gbvo) {
		try {
			ModelAndView mv = new ModelAndView();
			GuestBookVO gvo = guestBookService.getGuestBookDetail(gbvo);	/* 글 번호로 글 정보 조회 */
			if(gvo != null) {
				mv.addObject("gvo", gvo);			/* 상세 정보를 뷰로 전달 */
				mv.setViewName("day03/onelist");	/* onelist.jsp로 이동 */
				return mv;
			}
			return new ModelAndView("day03/error");
		} catch (Exception e) {
			return new ModelAndView("day03/error");
		}
	}
	
	/* 5. 방명록 첨부파일 다운로드 */
	@GetMapping("/guestBookDown")
	public void guestBookDown(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");		/* 파일이름 파라미터로 받기 */
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			String r_path = URLEncoder.encode(f_name,"UTF-8");	/* 한글 파일명 깨짐 처리 */
			
			//	브라우저에 다운로드 파일로 인식시키기 위한 설정(response)
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition","attachment; filename="+ r_path);	/* 다운로드 설정 */
			
			//	실제 파일 입출력 처리
			File file = new File(new String(path.getBytes(),"utf-8"));	/* 파일 객체 생성 */
			FileInputStream in = new FileInputStream(file);		/* 파일 입력 스트림 */
			OutputStream out = response.getOutputStream();		/* 브라우저로 내보낼 출력 스트림 */
			FileCopyUtils.copy(in, out);		/* 파일 복사해서 브라우저로 출력 */
			response.getOutputStream().flush();	/* 버퍼 비우기 */
			
		}catch(Exception e) {
			e.printStackTrace();	/* 예외 발생 시 콘솔에 출력 */
		}
	}
	
	/* 6. 방명록 삭제 폼 이동 (비밀 번호 확인 화면) */
	@GetMapping("/guestBookDelete")
	public ModelAndView guestBookDelete(GuestBookVO gbvo) {
		
		try {
			ModelAndView mv = new ModelAndView();
			GuestBookVO gvo = guestBookService.getGuestBookDetail(gbvo);	/* 삭제할 글 가져오기 */
			if(gvo != null) {
				
				// delete.jsp에서 gb_pw 와 gb_idx를 사용해야한다
				
				mv.addObject("gvo", gvo);		/* 삭제할 글 전달 (비밀번호 확인용) */
				mv.setViewName("day03/delete");		/* delete.jsp로 이동 */
				return mv;
			}
			return new ModelAndView("day03/error");
		} catch (Exception e) {
			return new ModelAndView("day03/error");
		}	
	}
	
	/* 7. 방명록 삭제 처리 */
	@PostMapping("/guestBookDeleteOK")
	public ModelAndView guestBookDeleteOK(GuestBookVO gbvo) {
		try {
			ModelAndView mv = new ModelAndView();
			int result = guestBookService.getGuestBookDelete(gbvo);	/* 삭제 요청 */
			if(result>0) {
				mv.setViewName("redirect:/guestBookList");		/* 성공 시 목록 이동 */
				return mv;
			}
			return new ModelAndView("day03/error");
		} catch (Exception e) {
			return new ModelAndView("day03/error");
		}
	}
	
	/* 8. 방명록 수정 폼 이동 */
	@GetMapping("/guestBookUpdate")
	public ModelAndView guestBookUpdate(GuestBookVO gbvo) {
		try {
			ModelAndView mv = new ModelAndView();
			GuestBookVO gvo = guestBookService.getGuestBookDetail(gbvo); /* 기존 글 가져오기 */
			if(gvo != null) {				
				mv.addObject("gvo", gvo);			/* 수정할 데이터 전달 */
				mv.setViewName("day03/update");		/* update.jsp 로 이동 */
				return mv;
			}
			return new ModelAndView("day03/error");
		} catch (Exception e) {
			return new ModelAndView("day03/error");		
		}
	}
	
	/* 9. 방명록 수정 처리 */
	@PostMapping("/guestBookUpdateOK")	/* "/guestBookUpdateOK" 경로로 POST 요청이 들어오면 실행됨 */
	public ModelAndView guestBookUpdateOK(GuestBookVO gbvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();	/* 결과 페이지로 이동하기 위한 객체 */
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");	/* 파일 업로드할 실제 경로 가져오기(웹 서버의 /resources/upload/ 경로) */
			MultipartFile file = gbvo.getGb_file_name();	/* 사용자가 업로드할 파일 객체 가져오기 (GuestBookVO에 잇는 MultipartFile) */
			
			if(file.isEmpty()) {	/* 파일을 새로 첨부하지 않았을 경우 */
				gbvo.setGb_f_name(gbvo.getGb_old_f_name());		/* 파일 수정 안 했으면 기존 파일 유지 */
			}else {
//				/* 새 파일을 첨부한 경우 */
				UUID uuid = UUID.randomUUID();	/* UUID를 이용해서 파일 이름 중복 방지 */
				String f_name = uuid.toString()+"_"+file.getOriginalFilename();	/* 새 파일 이름 만들기 (UUID + 원래 파일이름) */
				gbvo.setGb_f_name(f_name);		/* VO에 새 파일 이름 저장 */
				// 실제 새 파일 업로드
				file.transferTo(new File(path, f_name));
			}
			
			int result = guestBookService.getGuestBookUpdate(gbvo);	/* 데이터 베이스에 게시글 내용과 파일 정보를 업데이트 */
			if(result>0) {		/* 업데이트 성공했을 경우 */
				mv.setViewName("redirect:/guestBookDetail?gb_idx="+gbvo.getGb_idx());	/* 다시 상세보기 페이지로 이동 (수정한 글의 상세 보기로 리다이렉트) */
				return mv;
			}
				
			return mv;		/* 수정 실패 시 (에러 페이지로 가지않고 현재 뷰 유지 - 보완 필요할 수 있음) */
		} catch (Exception e) {
			return new ModelAndView("day03/error");
				
		}		
	}
	
}














