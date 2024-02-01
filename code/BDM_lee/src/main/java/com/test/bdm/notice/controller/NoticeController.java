package com.test.bdm.notice.controller;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.code.service.CodeService;
import com.test.bdm.notice.domain.NoticeVO;
import com.test.bdm.notice.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController implements PcwkLogger {
	
	@Autowired
	NoticeService service;
	
	@Autowired
	CodeService  codeService;
	
	@Autowired
	MessageSource messageSource;//ResourceBundleMessageSource가 주입됨
	
	
	public NoticeController() {}
	
	@GetMapping(value="/moveToReg.do")
	public String moveToReg(Model model, NoticeVO inVO) throws SQLException {
		String viewName = "";
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ moveToReg                         │");
		LOG.debug("│ inVO                              │"+inVO);
		LOG.debug("└───────────────────────────────────┘");
		
//		//DIV코드 조회
//		Map<String, Object> codes=new HashMap<String, Object>();
//		String[] codeStr = {"BOARD_DIV"};
//		codes.put("code", codeStr);
//		
//		List<CodeVO> codeList = this.codeService.doRetrieve(codes);
//		model.addAttribute("divCode", codeList);
//		model.addAttribute("paramVO", inVO);
//		
//		//공지사항:10, 자유게시판:20
//		String title = "";
//		if(inVO.getSearchDiv().equals("10")) {
//			title = "공지사항-등록";
//		}else {
//			title = "자유게시판-등록";
//		}
//		model.addAttribute("title", title);	
		
		
		
		viewName = "board/notice_reg";///WEB-INF/views/ viewName .jsp
		return viewName;
	}
	
	
	
	
	

}
