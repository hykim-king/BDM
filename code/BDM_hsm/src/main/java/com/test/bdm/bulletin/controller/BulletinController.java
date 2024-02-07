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
import com.test.bdm.user.domain.UserVO;

@Controller
@RequestMapping("bulletin")
public class BulletinController implements PcwkLogger {
	
	@Autowired
	BulletinService service;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	MessageSource messageSource;
	
	public BulletinController() {}
	
	@GetMapping(value = "/doRetrieve.do")
	public ModelAndView doRetrieve(BulletinVO inVO, ModelAndView modelAndView) throws SQLException {
		LOG.debug("─────────────────────────────────────");
		LOG.debug(" doRetrieve"                          );
		LOG.debug(" bulletinVO: " + inVO                    );
		LOG.debug("─────────────────────────────────────");
		//Default처리
		//페이지 사이즈:10
		if(null != inVO && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		
		//페이지 번호: 1
		if(null != inVO && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}
		
		//검색구분: ""
		if(null != inVO && null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv()));
		}
		LOG.debug("Bulletin Default처리: " + inVO);
		
		Map<String, Object> codes = new HashMap<String, Object>();
		String[] codeStr = {"page_size", "search"};
		
		codes.put("code", codeStr);
		List<CodeVO> codeList = this.codeService.doRetrieve(codes);
		List<CodeVO> bulletinSearchList = new ArrayList<CodeVO>();
		List<CodeVO> pageSizeList = new ArrayList<CodeVO>();
		for(CodeVO vo: codeList) {
			if(vo.getCategory().equals("search")) {
				bulletinSearchList.add(vo);
			}
			
			if(vo.getCategory().equals("page_size")) {
				pageSizeList.add(vo);
			}
		}
		List<BulletinVO> list = service.doRetrieve(inVO);
		
		long totalCnt = 0;
		
		for(BulletinVO vo: list) {
			if(totalCnt == 0) {
				totalCnt = vo.getTotalCnt();
				break;
			}
		}
		modelAndView.addObject("totalCnt", totalCnt);
		
		modelAndView.setViewName("bulletin/bulletin");
		modelAndView.addObject("list", list);
		modelAndView.addObject("paramVO", inVO);
		modelAndView.addObject("bulletinSearch", bulletinSearchList);
		modelAndView.addObject("pageSize", pageSizeList);
		
//		long bottomCount = StringUtil.BOTTOM_COUNT;
//		String html = StringUtil.renderingPager(totalCnt, inVO.getPageNo(), 
//				"/bdm/bulletin/doRetrieve.do", "pageDoRetrieve");
//		modelAndView.addObject("pageHtml", html);
		
		return modelAndView;
		}
	
	@PostMapping(value = "/doUpdate.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public MessageVO doUpdate(BulletinVO inVO) throws SQLException {
		LOG.debug("─────────────────────────────────────");
		LOG.debug(" doUpdate"                            );
		LOG.debug(" BulletinVO: " + inVO                 );
		LOG.debug("─────────────────────────────────────");
		
		int flag = service.doUpdate(inVO);
		Locale locale = LocaleContextHolder.getLocale();
		
		String message = "";
		if(1 == flag) {
			message = messageSource.getMessage("common.message.update", null, locale);
			LOG.debug("message: " + message);
			
			String update = "수정";
			message = MessageFormat.format(message, update);
			LOG.debug("message: " + message);
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
		
		if(null == inVO.getId()) {
			inVO.setId(StringUtil.nvl(inVO.getId(), "Guest"));
		}
		
		if(null == httpSession.getAttribute("user")) {
			UserVO user = (UserVO) httpSession.getAttribute("user");
			inVO.setId(user.getId());
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
	
}
	