package com.ict.edu06.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.Paging;
import com.ict.edu06.service.BoardService;
import com.ict.edu06.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private Paging paging;

	@RequestMapping("/day06")	// paging 기법을 사용할 때는 무조건 반환값이 있다.
	public ModelAndView boardList(HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			
			// 1. 게시물의 수
			int count = boardService.getTotalCount();
			paging.setTotalRecord(count);
			
			// 2. 전체 페이지의 수를 구한다.
			if(paging.getTotalRecord() <= paging.getNumPerPage()) {
				paging.setTotalPage(1);
			}else {
				paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
				if(paging.getTotalRecord() % paging.getNumPerPage() != 0) {
					paging.setTotalPage(paging.getTotalPage()+1);
				}
			}
			
			
			// 3. 파라미터로 넘어온 cPage 구하기
			String cPage = request.getParameter("cPage");
			// cPage가 파라미터로 넘어오지 않았으면 null, 무조건 1page 이다.
			if(cPage == null  || cPage.equals("0")) {
				paging.setNowPage(1);
			}else {
				paging.setNowPage(Integer.parseInt(cPage));
			}
				
			
			// 4. nowPage를 기준으로 필요한 게시물 계산
			paging.setOffset(paging.getNumPerPage() * (paging.getNowPage()-1));
			
			// 5. 시작블록, 끝 블록
			paging.setBeginBlock(((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
			paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);
			/* 주의 사항 (endBlock(3, 6, 9, .... ) 나온다
			 그런데 실제 가지고 있는 총 페이지는 3, 6, 9... 로 나오지 않을 수 있다.
			*/
			if(paging.getEndBlock() > paging.getTotalPage()) {
				paging.setEndBlock(paging.getTotalPage());
			}
			
			// 6. 필요한 게시물만큼 DB에서 가져오기
			List<BoardVO> boardList = boardService.getBoardList(paging.getNumPerPage(), paging.getOffset());
			if(boardList != null) {
				mv.addObject("boardList", boardList);
				mv.addObject("paging", paging);
				mv.setViewName("day06/board_list");
				return mv;
			} else {
				return new ModelAndView("day06.error");
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day06/error");
			}
		}
	@GetMapping("/board_write")
	public ModelAndView boardWrite(@RequestParam(value="cPage", required = false, defaultValue = "1") String cPage) {
	    ModelAndView mv = new ModelAndView("day06/write");
	    mv.addObject("cPage", cPage); 
	    return mv;
	}
	
	@PostMapping("/board_write_ok")
	public ModelAndView boardWriteOk(BoardVO boardVO, 
			@ModelAttribute("cPage") String cPage, 
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
			
			MultipartFile file = boardVO.getFile_name();
			if(file.isEmpty()) {
				boardVO.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+file.getOriginalFilename();
				boardVO.setF_name(f_name);
				file.transferTo(new File(path,f_name));
			}
			
			// 비밀번호 암호화
			String pwd = passwordEncoder.encode(boardVO.getPwd());
			boardVO.setPwd(pwd);
			
			int result = boardService.getBoardInsert(boardVO);
			if(result>0) {
				 mv.setViewName("redirect:/day06");
			}else {
				mv.setViewName("day06/error");
				
			}
			
			
			
			
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day06/error");
		}
		
	}
	
	@GetMapping("/board_detail")
	public ModelAndView boardDetail(@ModelAttribute("cPage") String cPage,
			@RequestParam("b_idx") String b_idx) {
		try {
			ModelAndView mv = new ModelAndView();
			
			// hit 업데이트 
			int result = boardService.getBoardHit(b_idx);
			
			// 상세 보기
			BoardVO boardVO = boardService.getBoardDetail(b_idx);
			
			
			if(boardVO != null) {
				mv.addObject("boardVO", boardVO);
				mv.setViewName("day06/detail");
				return mv; 
			}else {
				mv.setViewName("day06/error");
				return mv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day06/error");
		}
	}

	@GetMapping("/board_down")
	public void boardDown(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			String r_path = URLEncoder.encode(f_name, "UTF-8");
			// 브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="+r_path);
			
			// 실제 입출력
			File file = new File(new String(path.getBytes(),"utf-8"));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
			response.getOutputStream().flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@PostMapping("/board_ans_write")
	public ModelAndView boardAnsWrite(@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		
		
		return new ModelAndView("day06/ans_write");
	}
	
	@PostMapping("/board_ans_write_ok")
	public ModelAndView boardAnsWriteOk(BoardVO boardVO,
			@ModelAttribute("cPage") String cPage,
			HttpServletRequest request) {
		try {
			// 답글에서만 처리 할 일들
			//	1. 원글의 b_groups, b_step, b_lev 들을 가져온다. 
			BoardVO bovo = boardService.getBoardDetail(boardVO.getB_idx());
			
			//	2. step, lev를 하나씩 증가 시키자. (원들의 step, lev 값은 0 ) 
			int b_groups = Integer.parseInt(bovo.getB_groups());
			int b_step = Integer.parseInt(bovo.getB_step());	// 글자 집어넣기 위해 넣음
			int b_lev = Integer.parseInt(bovo.getB_step());	// 원글의 댓글 대댓글을 분류
			
			b_step ++ ;	// 원글 0  - >  1
			b_lev ++ ;	// 원글 0  - >  1
			
			//	3. DB에서 b_groups를 기준으로 b_lev을 업데이트 하자.
			//	** b_groups가 같은 글을 찾아서 기존의 글에 레벨이 같거나 크면 기존 글의 레벨을 증가 시키자.
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("b_groups", b_groups);
			map.put("b_lev", b_lev);
			
			int result = boardService.getLevUp(map);
			
			// 4. 받은 정보와 함께 bovo 에 넣고 insert 하자
			boardVO.setB_groups(String.valueOf(b_groups));
			boardVO.setB_step(String.valueOf(b_step));
			boardVO.setB_lev(String.valueOf(b_lev));
			
			
			ModelAndView mv = new ModelAndView();
			
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
			MultipartFile file = boardVO.getFile_name();
			if(file.isEmpty()) {
				boardVO.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+file.getOriginalFilename();
				boardVO.setF_name(f_name);
				file.transferTo(new File(path,f_name));
			}
			
			// 비밀번호 암호화
			String pwd = passwordEncoder.encode(boardVO.getPwd());
			boardVO.setPwd(pwd);
			
			result = boardService.getBoardAnsInsert(boardVO);
			if(result>0) {
				 mv.setViewName("redirect:/day06");
			}else {
				mv.setViewName("day06/error");
				
			}
			
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day06/error");
		}
	}
	
	@PostMapping("/board_delete")
	public ModelAndView boardDelete(
			@ModelAttribute("cPage") String cPage,
			@ModelAttribute("b_idx") String b_idx
			
			) {
		return new ModelAndView("day06/delete");
	}
	
	@PostMapping("/board_delete_ok")
	public ModelAndView boardDeleteOk(
		@RequestParam("pwd") String pwd,
		@ModelAttribute("b_idx") String b_idx,
		@ModelAttribute("cPage") String cPage
			) {
		try {
			ModelAndView mv = new ModelAndView();
			// 비밀번호 체크 
			BoardVO bovo = boardService.getBoardDetail(b_idx);
			String dbpwd = bovo.getPwd();
			
			if (passwordEncoder.matches(pwd, dbpwd)) {
				
				// 글삭제
				int result = boardService.getBoardDelete(b_idx);
				if(result>0) {
					mv.setViewName("redirect:/day06");
					return mv;
				}else {
					return new ModelAndView("day06/error");
				}
			}else {
				// 비밀번호 틀림
				mv.setViewName("day06/delete");
				mv.addObject("pwdchk", "fail");
				return mv;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day06/error");
		}
	}
	
	@PostMapping("/board_update")
	public ModelAndView boardUpdate(
			BoardVO boardVO,
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage			
			) {
		// 기존의 값 불러오고 (비번제외)
		try {
			ModelAndView mv = new ModelAndView();
			mv.addObject("boardVO",  boardService.getBoardDetail(b_idx));
			mv.setViewName("day06/update");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day06/error");
		}
		
		
		
	}
	
	@PostMapping("/board_update_ok")
	public ModelAndView boardUpdateok(
			BoardVO boardVO,
			HttpServletRequest request,
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage
			) {
		try {
			ModelAndView mv = new ModelAndView();
			// 비밀번호 확인
			BoardVO bovo = boardService.getBoardDetail(b_idx);
			String dbpwd = bovo.getPwd();			
			if(passwordEncoder.matches(boardVO.getPwd(), dbpwd)) {
				//	원글 수정
				try {
					String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
					MultipartFile file = boardVO.getFile_name();
					String old_f_name = boardVO.getOld_f_name();
					if(file.isEmpty()) {
						boardVO.setF_name(old_f_name);
					}else {
						UUID uuid =  UUID.randomUUID();
						String f_name = uuid.toString()+"_"+file.getOriginalFilename();
						boardVO.setF_name(f_name);
						// 실제 업데이트
						file.transferTo(new File(path,f_name));
					}
					int result = boardService.getBoardUpdate(boardVO);
					
					if(result>0) {
						mv.setViewName("redirect:/board_detail");
						return mv;
					}else {
						return new ModelAndView("day06/error");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					return new ModelAndView("day06/error");
				}
			}
			
			// 수정된 값을 DB로 보내주고 > 서비스 > DAO > map
			
			
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day06/error");	
		}
	}
	
}






















