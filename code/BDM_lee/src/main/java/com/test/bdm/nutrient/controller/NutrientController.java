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
	
//	@GetMapping(value = "/doCart.do")
//	public 
	
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
		
		modelAndView.setViewName("nutrient/nutrient");
		modelAndView.addObject("list", list);
		modelAndView.addObject("paramVO", inVO);
		
		return modelAndView;
	}
}
