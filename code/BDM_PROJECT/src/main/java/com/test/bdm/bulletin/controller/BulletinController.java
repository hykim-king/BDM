package com.test.bdm.bulletin.controller;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.bulletin.service.BulletinService;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.code.service.CodeService;
import com.test.bdm.comments.service.CommentsService;
import com.test.bdm.heart.domain.HeartVO;
import com.test.bdm.heart.service.HeartService;
import com.test.bdm.user.domain.UserVO;

@Controller
@RequestMapping("bulletin")
public class BulletinController implements PcwkLogger {
	
	@Autowired
	BulletinService service;

	@Autowired
	HeartService heartService;	
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CommentsService commentsService;
	
	public BulletinController() {}
	
	@GetMapping(value = "/moveToReg.do")
	public String moveToReg(Model model, BulletinVO inVO) throws SQLException {
		String viewName = "";

		// DIV코드 조회
		//Map<String, Object> codes = new HashMap<String, Object>();
		//String[] codeStr = { "BULLETIN_DIV" };
		//codes.put("code", codeStr);

		//List<CodeVO> codeList = this.codeService.doRetrieve(codes);
		//model.addAttribute("divCode", codeList);
		model.addAttribute("paramVO", inVO);

		String title = "자유게시판 등록";

		model.addAttribute("title", title);

		viewName = "bulletin/bulletin_reg";
		return viewName;
	}
	
	@GetMapping(value = "/doRetrieve.do")
	public ModelAndView doRetrieve(BulletinVO inVO, ModelAndView modelAndView,HeartVO heartVO) throws SQLException {
		LOG.debug("─────────────────────────────────────");
		LOG.debug(" doRetrieve"                          );
		LOG.debug(" bulletinVO: " + inVO                 );
		LOG.debug("─────────────────────────────────────");
		// Default처리
		// 페이지 사이즈:10
		if (null != inVO && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}

		// 페이지 번호:1
		if (null != inVO && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}

		// 검색구분:""
		if (null != inVO && null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv()));
		}
		// 검색어:""
		if (null != inVO && null == inVO.getSearchWord()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchWord()));
		}
		LOG.debug("Bulletin Default처리: " + inVO);
		
		Map<String, Object> codes = new HashMap<String, Object>();
		String[] codeStr = { "PAGE_SIZE", "SEARCH" };

		codes.put("code", codeStr);
		List<CodeVO> codeList = this.codeService.doRetrieve(codes);

		List<CodeVO> bulletinSearchList = new ArrayList<CodeVO>();
		List<CodeVO> pageSizeList = new ArrayList<CodeVO>();
		
		for (CodeVO vo : codeList) {
			if (vo.getCategory().equals("SEARCH")) {
				bulletinSearchList.add(vo);
			}

			if (vo.getCategory().equals("PAGE_SIZE")) {
				pageSizeList.add(vo);
			}
		}
		 List<BulletinVO> list = service.doRetrieve(inVO);

		    for (BulletinVO vo : list) {
		        int count = heartService.getHeartCountForBulletin(vo.getPostNo()); // 게시물 번호를 기반으로 하트 개수를 가져오는 메소드 호출
		        vo.setHeartCount(count); // BulletinVO에 하트 개수 설정
		    }
		    
		    for (BulletinVO vo:list) {
		    	int commentsCount = commentsService.commentsCount(vo.getPostNo());
		    	vo.setCommentsCount(commentsCount);
		    }
		
		
		long totalCnt = 0;
		
		for(BulletinVO vo: list) {
			if(totalCnt == 0) {
				totalCnt = vo.getTotalCnt();
				break;
			}
		}
		modelAndView.addObject("totalCnt", totalCnt);
		
		modelAndView.setViewName("bulletin/bulletin_list");
		modelAndView.addObject("list", list);
		modelAndView.addObject("paramVO", inVO);
		modelAndView.addObject("bulletinSearch", bulletinSearchList);
		modelAndView.addObject("pageSize", pageSizeList);
		
		long bottomCount = StringUtil.BOTTOM_COUNT;// 바닥글
		String html = StringUtil.renderingPager(totalCnt, inVO.getPageNo(), inVO.getPageSize(), bottomCount,
				"/bdm/bulletin/doRetrieve.do", "pageDoRerive");
		modelAndView.addObject("pageHtml", html);

		String title = "게시판-목록";
		modelAndView.addObject("title", title);
		
		
		return modelAndView;
		}
	
		@PostMapping(value = "/doSave.do", produces = "application/json;charset=UTF-8")
		@ResponseBody
		public MessageVO doSave(BulletinVO inVO) throws SQLException {
			LOG.debug("─────────────────────────────────────");
			LOG.debug(" doSave"                              );
			LOG.debug(" BulletinVO: " + inVO                 );
			LOG.debug("─────────────────────────────────────");
			// seq조회
			int seq = service.getBulletinSeq();
			inVO.setPostNo(seq);
			LOG.debug("BulletinVO seq: " + inVO);
			int flag = service.doSave(inVO);

			String message = "";
			if (1 == flag) {
				message = "등록 되었습니다.";
			} else {
				message = "등록 실패.";
			}

			MessageVO messageVO = new MessageVO(String.valueOf(flag), message);
			LOG.debug("messageVO: " + messageVO);
			return messageVO;
		}
	
	@PostMapping(value = "/doUpdate.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doUpdate(BulletinVO inVO) throws SQLException {
		LOG.debug("─────────────────────────────────────");
		LOG.debug(" doUpdate"                            );
		LOG.debug(" BulletinVO: " + inVO                 );
		LOG.debug("─────────────────────────────────────");
		
		int flag = service.doUpdate(inVO);
		
		String message = "";
		if(1 == flag) {
			message = "수정되었습니다.";
		} else {
			message = "수정 실패";
		}
		
		MessageVO messageVO = new MessageVO(flag + "", message);
		LOG.debug("messageVO: " + messageVO);
		return messageVO;
	}
	
	@GetMapping(value = "/doSelectOne.do")
	public String doSelectOne(BulletinVO inVO, Model model, HttpSession httpSession) throws SQLException, EmptyResultDataAccessException {
		String view = "bulletin/bulletin_mng";
		LOG.debug("─────────────────────────────────────");
		LOG.debug(" doSelectOne"                         );
		LOG.debug(" BulletinVO: " + inVO                 );
		LOG.debug("─────────────────────────────────────");
		
		if(0 == inVO.getPostNo()) {
			LOG.debug("─────────────────────────────────────");
			LOG.debug(" nullPointerException                ");
			LOG.debug("─────────────────────────────────────");
			
			throw new NullPointerException("순번을 입력 하세요");
			
		}
		
		UserVO user = (UserVO) httpSession.getAttribute("user");
		if (user != null) {
		    inVO.setId(user.getId());
		} else {
		    inVO.setId("");
		}
		
		BulletinVO outVO = service.doSelectOne(inVO);
		model.addAttribute("vo", outVO);
		
		Map<String, Object> codes = new HashMap<String, Object>();
		String[] codeStr = {"search"};
		codes.put("code", codeStr);
		
		List<CodeVO> codeList = this.codeService.doRetrieve(codes);
		model.addAttribute("divCode", codeList);
		
		return view;
		
		
	}
	
	@GetMapping(value="/bulletinView.do")
	public String bulletinView(BulletinVO inVO, HeartVO heartVO, Model model, HttpSession httpSession) throws SQLException, EmptyResultDataAccessException {
		String view = "bulletin/bulletin_view";
		LOG.debug("─────────────────────────────────────");
		LOG.debug(" bulletinView"                        );
		LOG.debug(" bulletinVO: " + inVO                 );
		LOG.debug(" BulletinView User ID: " + inVO.getId());
		LOG.debug("─────────────────────────────────────");
		
		if(0 == inVO.getPostNo()) {
			LOG.debug("─────────────────────────────────────");
			LOG.debug(" nullPointerException                ");
			LOG.debug("─────────────────────────────────────");
			
			throw new NullPointerException("순번을 입력 하세요");
			
		}
		
		
		UserVO user = (UserVO) httpSession.getAttribute("user");
		if(user != null) {
			inVO.setId(user.getId());
			heartVO.setId(user.getId());			
		} else {
			inVO.setId("");
		}
		
		BulletinVO outVO = service.bulletinView(inVO);
		model.addAttribute("vo", outVO);
		
		int myCount = heartService.getCount(heartVO);
		model.addAttribute("myCount", myCount);
		
		int count = heartService.getTotalCount(heartVO);
		model.addAttribute("count", count);		
		
		return view;
	
	}
	
	@GetMapping(value = "/doDelete.do", produces = "application/json;charset=UTF-8") // RequestMethod.GET)
	@ResponseBody // HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public MessageVO doDelete(BulletinVO inVO) throws SQLException {
		LOG.debug("─────────────────────────────────────");
		LOG.debug(" doDelete"                            );
		LOG.debug(" BulletinVO: " + inVO                 );
		LOG.debug("─────────────────────────────────────");
		if (0 == inVO.getPostNo()) {
			LOG.debug("============================");
			LOG.debug("==nullPointerException===");
			LOG.debug("============================");
			MessageVO messageVO = new MessageVO(String.valueOf("3"), "순번을 선택 하세요.");
			return messageVO;
		}

		int flag = service.doDelete(inVO);

		String message = "";
		if (1 == flag) {// 삭제 성공
			message = inVO.getPostNo() + "삭제 되었습니다.";
		} else {
			message = inVO.getPostNo() + "삭제 실패!";
		}

		MessageVO messageVO = new MessageVO(String.valueOf(flag), message);

		LOG.debug("│ messageVO                           │" + messageVO);
		return messageVO;
	}
	
}
	