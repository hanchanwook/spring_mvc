package com.ict.edu05.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.Paging;
import com.ict.edu05.service.BbsService;
import com.ict.edu05.vo.BbsVO;
import com.ict.edu05.vo.CommVO;

@Controller
public class BBSController {
	@Autowired
	private BbsService bbsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private Paging paging;

	/* 페이징 기법
	 * @RequestMapping("/day05") public ModelAndView getBbsList() { try {
	 * ModelAndView mv = new ModelAndView(); mv.addObject("list",
	 * bbsService.getBbsList()); mv.setViewName("day05/list"); return mv; } catch
	 * (Exception e) { System.out.println(e); return new
	 * ModelAndView("day05/error"); } }
	 */
	
	
	@RequestMapping("/day05")
	public ModelAndView getBbsList(HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			
			// 1. DB 전체 게시물의 수
			int count = bbsService.getTotalCount();
			paging.setTotalRecord(count);
			// 2. 전체 페이지의 수
			//	numPerPage = 4
			//	numPerPage = 4 보다 작으면 전체 페이지는 1페이지에 들어간다.
			if(paging.getTotalRecord()<= paging.getNumPerPage()) {
				paging.setTotalPage(1);	
			}else {
			//	전체 레코드를 numPerPage 로 나눈 정수 값이 전체 페이지가된다.
				paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
				if(paging.getTotalRecord() % paging.getNumPerPage() != 0) {
					// 나머지가 있으면 전체 페이지 +1 하자
					paging.setTotalPage(paging.getTotalPage() + 1);
				}
			}
			
			// 3. 파라미터에서 넘어오는 cPage를 구하자
			//	list로 갈때 마다 현재 페이지 값을 전달하는 파라미터
			String cPage = request.getParameter("cPage");
			// cPage 넘어오지 않으면 무조건 현재 페이지가 1 page가 된다.
			if(cPage == null) {
				paging.setNowPage(1);
			}else {
				paging.setNowPage(Integer.parseInt(cPage));
			}
			// 4. DB 에서 nowPage를 기준으로 필요한 게시물 계산하기
			// MySQL, MariaDB 는 limit, offset
			// offset = limit * (현재 패이지 - 1)
			// limit => numPerPage 
			paging.setOffset(paging.getNumPerPage()*(paging.getNowPage()-1));
			
			// 5. 시작블록과 끝블록 구하기 
			paging.setBeginBlock((int)(((paging.getNowPage()-1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1));
			paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
			
			// 주의 사항
			// endBlock (3, 6, 9, 12....) 끝난다. 
			// 실제 totalPage는 3, 6, 9로 끝나지 않는다.
			if(paging.getEndBlock() >= paging.getTotalPage()) {
				paging.setEndBlock(paging.getTotalPage());
			}
			
			
			// 6. 필요한 게시물 만큼 DB에서 가져오기
			List<BbsVO> list = bbsService.getBbsList(paging.getNumPerPage(), paging.getOffset());
			
			// 7. 저장하기
			mv.addObject("list", list);
			mv.addObject("paging", paging);
			mv.setViewName("day05/list");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day05/error");
		}
	}
	

	@GetMapping("/bbs_write")
	public ModelAndView getBbs_write() {
		return new ModelAndView("day05/write");
	}

	@PostMapping("/bbs_write_ok")
	public ModelAndView getBbsWriteOk(BbsVO bvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
		
			MultipartFile file = bvo.getFile_name();
			if (file.isEmpty()) {
				bvo.setF_name("");
			} else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+file.getOriginalFilename();
				// DB에 저장
				bvo.setF_name(f_name);
				// 실제 업로드
				file.transferTo(new File(path, f_name));
			}

			// 비밀번호 암호화 : 복호화가 안 됨

			String pwd = passwordEncoder.encode(bvo.getPwd());
			bvo.setPwd(pwd);

			int result = bbsService.getBbsInsert(bvo);
			if(result>0) {
				mv.setViewName("redirect:/day05");
			}else {
				mv.setViewName("day05/error");
			}

			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}

	@GetMapping("/bbs_detail")
	public ModelAndView getBbsDetail(BbsVO bvo, @ModelAttribute("cPage") String cPage) {	//@ModelAttribute("cPage") String cPage : cPage를 전달하는 역할
		try {
			ModelAndView mv = new ModelAndView();
			// 조회수 증가
			int result = bbsService.getBbsHitUpdate(bvo);
			// 상세 보기
			mv.addObject("bvo", bbsService.getBbsDetail(bvo));

			// 댓글 리스트 가져오기 (원글과 관련된 댓글)
			List<CommVO> commList = bbsService.getCommentList(bvo.getB_idx());
			mv.addObject("commList", commList);
			
			mv.setViewName("day05/detail");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}

	@GetMapping("/bbs_down")
	public void getBbsDown(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/" + f_name);
			String r_path = URLEncoder.encode(f_name, "UTF-8");

			// 브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + r_path);

			// 실제 입출력
			File file = new File(new String(path.getBytes(), "utf-8"));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();

			FileCopyUtils.copy(in, out);
			response.getOutputStream().flush();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// @ModelAttribute("b_idx") String b_idx
	// 파라미터 b_idx 를 받아서 다음 delete.jsp에 b_ixd로 넘긴다.

	@PostMapping("/bbs_delete")
	public ModelAndView getBbsDelete(@ModelAttribute("b_idx") String b_idx, 
			@ModelAttribute("cPage") String cPage) {
		return new ModelAndView("day05/delete");
	}

	/*
	 * @PostMapping("/bbs_delete2") public ModelAndView getBbsDelete2(BbsVO bvo) {
	 * ModelAndView mv = new ModelAndView(); String b_idx = bvo.getB_idx();
	 * mv.addObject("b_idx", b_idx); mv.setViewName("b_idx", b_idx); return mv; }
	 */

	@PostMapping("/bbs_delete_ok")
	public ModelAndView getBbsDeleteOk(BbsVO bvo, 
			@ModelAttribute("b_idx")String b_idx,
			@ModelAttribute("cPage") String cPage ) {
		try {
			ModelAndView mv = new ModelAndView();

			// 비밀번호 검사
			BbsVO bbsvo = bbsService.getBbsDetail(bvo);
			String dbpwd = bbsvo.getPwd();
			if (passwordEncoder.matches(bvo.getPwd(), dbpwd)) {
				// 원글 삭제
				// active 컬럼을 만들어서 0 -> 1 변경하자
				int result = bbsService.getBbsDelete(bvo);
				if(result>0) {
					mv.setViewName("redirect:/day05");
					return mv;
				}else {
					return new ModelAndView("day05/error");
				}
			} else {
				// 틀렸을때
				mv.addObject("pwdchk", "fail");
				mv.setViewName("day05/delete");
				return mv;
			}

		} catch (Exception e) {
			
			return new ModelAndView("day05/error");
		}
	}

	@PostMapping("/bbs_update")
	public ModelAndView getBbsUpdate(BbsVO bvo, 
			@ModelAttribute("cPage") String cPage) {
		try {
			ModelAndView mv = new ModelAndView();
			mv.addObject("bvo", bbsService.getBbsDetail(bvo));
			mv.setViewName("day05/update");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}

	@PostMapping("/bbs_update_ok")
	public ModelAndView getBbsupdateOk(BbsVO bvo, 
			HttpServletRequest request,
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		try {
			ModelAndView mv = new ModelAndView();

			BbsVO bbsvo = bbsService.getBbsDetail(bvo);
			String dbpwd = bbsvo.getPwd();
			if (passwordEncoder.matches(bvo.getPwd(), dbpwd)) {
				// 원글 수정
				try {
					String path = request.getSession().getServletContext().getRealPath("/resources/upload");
					MultipartFile file = bvo.getFile_name();
					String old_f_name = bvo.getOld_f_name();
					if (file.isEmpty()) {
						bvo.setF_name(old_f_name);
					} else {
						UUID uuid = UUID.randomUUID();
						String f_name = uuid.toString()+"_"+file.getOriginalFilename();
						bvo.setF_name(f_name);
						// 실제 업로드
						file.transferTo(new File(path,f_name));
					}
					
					int result = bbsService.getBbsUpdate(bvo);
					
					if(result>0) {
						mv.setViewName("redirect:/bbs_detail");
						return mv;
					}else {	
						return new ModelAndView("day05/error");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					return new ModelAndView("day05/error");
				}
				
			} else {
				// 틀렸을때
				mv.addObject("pwdchk", "fail");
				// bvo.setF_name(bvo.getOld_f_name());
				// 수정된 정보가 넘어간다
				// mv.addObject("bvo",bvo);
				// 원래 DB 정보
				mv.addObject("bvo", bbsvo);
				mv.setViewName("day05/update");
				return mv;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}
	
	@PostMapping("/comm_insert")
	public ModelAndView getCommInsert(CommVO cvo, 
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage ) {
		try {
			ModelAndView mv = new ModelAndView();
			int result = bbsService.getCommentInsert(cvo);
			if(result>0) {
				mv.setViewName("redirect:/bbs_detail");
				return mv;
			}else {
				return new ModelAndView("day05/error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");			
		}
	}

	@PostMapping("/comm_delete")
	public ModelAndView getCommDelete(CommVO cvo, 
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage ) {
		try {
			ModelAndView mv = new ModelAndView();
			int result = bbsService.getCommentDelete(cvo);
			if(result>0) {
				mv.setViewName("redirect:/bbs_detail");
				return mv;
			}else {
				return new ModelAndView("day05/error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");			
		}
	}
	
	
}




/*
 * 비밀번호 암호화 하기 1. pm.xml 에 추가 하기 https://mvnrepository.com/
 * 
 * 2. web.xml 수정 파일 만들어서 /WEB-INF/spring/security-context.xml
 * 
 * 3. 사용할 클래스(Contr
 */
