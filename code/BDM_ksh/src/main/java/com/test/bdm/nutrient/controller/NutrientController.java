package com.test.bdm.nutrient.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.nutrient.domain.NutrientVO;
import com.test.bdm.nutrient.service.NutrientService;

@Controller
@RequestMapping("nutrient")
public class NutrientController implements PcwkLogger{
	
	@Autowired
	NutrientService service;

	@GetMapping(value = "/moveToNut.do")
	public String moveToNut() throws SQLException {
		return "nutrient/nutrient";
	}
	
	@GetMapping(value = "/doRetrieveOneDay.do")
	public ModelAndView doRetrieveOneDay(NutrientVO inVO, ModelAndView modelAndView) throws SQLException {
		if(inVO != null && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		if(inVO != null && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}
		
		return modelAndView;
	}
	
	@GetMapping(value = "/doRetrieve.do")
	public ModelAndView doRetrieve(NutrientVO inVO, ModelAndView modelAndView) throws SQLException {
		if(inVO != null && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		if(inVO != null && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}
		
		if(inVO != null && inVO.getSearchWord() == null) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord()));
		}
		
		List<NutrientVO> list = service.doRetrieve(inVO);
		
		long totalCnt = 0;
		// 총 음식 수 
		for(NutrientVO vo  :list) {
			if(totalCnt == 0) {
				totalCnt = vo.getTotalCnt();
				break;
			}
		}
		modelAndView.addObject("totalCnt", totalCnt);
		
		modelAndView.setViewName("nutrient/nutrient");
		modelAndView.addObject("list", list);
		modelAndView.addObject("paramVO", inVO);
		
		//페이징
		long bottomCount = StringUtil.BOTTOM_COUNT;//바닥글
		String html = StringUtil.renderingPager(totalCnt, inVO.getPageNo(), inVO.getPageSize(), bottomCount,
				"/bdm/nutrient/doRetrieve.do", "pageDoRetrieve");
		modelAndView.addObject("pageHtml", html);
		
		return modelAndView;
	}
}
