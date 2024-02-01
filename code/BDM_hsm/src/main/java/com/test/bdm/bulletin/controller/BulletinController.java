package com.test.bdm.bulletin.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.bulletin.service.BulletinService;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.code.service.CodeService;

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
		LOG.debug(" BoardVO: " + inVO                    );
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
		String[] codeStr = {"page_size", "search_div"};
		
		codes.put("code", codeStr);
		List<CodeVO> codeList = this.codeService.doRetrieve(codes);
		List<CodeVO> bulletinSearchList = new ArrayList<CodeVO>();
		List<CodeVO> pageSizeList = new ArrayList<CodeVO>();
		for(CodeVO vo: codeList) {
			if(vo.getCategory().equals("search_div")) {
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
		
		modelAndView.setViewName("board/bulletin");
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
	}
	