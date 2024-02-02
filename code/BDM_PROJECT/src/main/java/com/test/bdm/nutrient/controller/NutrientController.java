package com.test.bdm.nutrient.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.nutrient.domain.NutrientVO;
import com.test.bdm.nutrient.service.NutrientService;
import com.test.bdm.user.domain.UserVO;

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
	public ModelAndView doRetrieveOneDay(NutrientVO inVO, ModelAndView modelAndView, HttpSession session) throws SQLException, ParseException {
		if(inVO != null && inVO.getPageSize() == 0) {
			inVO.setPageSize(10L);
		}
		if(inVO != null && inVO.getPageNo() == 0) {
			inVO.setPageNo(1L);
		}
		
		UserVO sessionData = (UserVO) session.getAttribute("user");
		String userId = sessionData.getId();
		
		LocalDate now = LocalDate.now();
		
		 // 포맷 정의        
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		// 포맷 적용        
		String formatedNow = now.format(formatter);
		
		NutrientVO oneDay = service.doRetrieveOneDay(userId, formatedNow);
		
		Date today = new Date();
		
		// =============================================================================
		
		String pattern = "yy/MM/dd HH:mm:ss";
		// 출력용으로 사용할 데이트 포맷
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		// 포맷 적용        
		String formatedToday = simpleDateFormat.format(today);
		
		System.out.println(formatedToday);
		
		Date date1=new SimpleDateFormat(pattern).parse(formatedToday);
		
		Calendar calendar = Calendar.getInstance();
		// calendar 구조체에 오늘 날짜 지정
		calendar.setTime(date1);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		// 출력 형식 지정, 한 주 시작 날짜(일요일)
		// 시간을 '00:00:00'으로 설정
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
		String startDate = simpleDateFormat.format(calendar.getTime());
		System.out.println(startDate);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
		// 출력 형식 지정, 한 주 끝 날짜(토요일)
		// 시간을 '23:59:59'으로 설정
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
		String finishDate = simpleDateFormat.format(calendar.getTime());
		System.out.println(finishDate);
		
		NutrientVO thisWeek = service.doRetrieveWeek("test1", startDate, finishDate);
		
		modelAndView.setViewName("user/mypage");
		modelAndView.addObject("oneDay", oneDay);
		modelAndView.addObject("thisWeek", thisWeek);
		
		
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
