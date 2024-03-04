package com.test.bdm.nutrient.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.StringUtil;
import com.test.bdm.food.domain.FoodVO;
import com.test.bdm.nutrient.domain.EatVO;
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
	
	@GetMapping(value = "/doDelete.do", produces = "application/json;charset=UTF-8") // RequestMethod.GET)
	@ResponseBody // HTTP 요청 부분의 body부분이 그대로 브라우저에 전달된다.
	public MessageVO doDelete(EatVO inVO) throws SQLException {
		int flag = service.doDelete(inVO);

		String message = "";
		if (1 == flag) {// 삭제 성공
			message = inVO.getName() + " 삭제 되었습니다.";
		} else {
			message = inVO.getName() + "삭제 실패!";
		}

		MessageVO messageVO = new MessageVO(String.valueOf(flag), message);

		LOG.debug("│ messageVO                           │" + messageVO);
		return messageVO;
	}
	
	public static Date convertStringToDate(String dateString, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static String convertDateFormat(String inputDate, String inputFormat, String outputFormat) {
        SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);

        try {
            Date date = inputFormatter.parse(inputDate);
            return outputFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            // 예외 처리: 날짜 포맷이 올바르지 않을 경우
            return "날짜 형식 오류";
        }
    }
	
	@GetMapping(value = "/doRetrieveOneDay.do")
	public ModelAndView doRetrieveOneDay(NutrientVO inVO, ModelAndView modelAndView, HttpSession session, FoodVO foodVO) throws SQLException, ParseException {
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
		
		if(foodVO != null && foodVO.getRegDt() != null) {
			formatedNow = foodVO.getRegDt();
		}
		
		NutrientVO oneDay = service.doRetrieveOneDay(userId, formatedNow);
		
		// =============================================================================
		
		Date today = convertStringToDate(formatedNow, "yy/MM/dd");
		
		String pattern = "yy/MM/dd";
		// 출력용으로 사용할 데이트 포맷
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		// 포맷 적용        
		String formatedToday = simpleDateFormat.format(today);
		
		System.out.println("formatedToday: " + formatedToday);
		
		Date date1=new SimpleDateFormat(pattern).parse(formatedToday);
		
		Calendar calendar = Calendar.getInstance();
		// calendar 구조체에 오늘 날짜 지정
		calendar.setTime(date1);
		
		ArrayList<String> weekly = new ArrayList<String>();
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		// 출력 형식 지정, 한 주 시작 날짜(일요일)
		// 시간을 '00:00:00'으로 설정
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
		String startDate = simpleDateFormat.format(calendar.getTime());
		System.out.println("startDate: " + startDate);
		weekly.add(startDate);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		String monday = simpleDateFormat.format(calendar.getTime());
		weekly.add(monday);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
		String tuesday = simpleDateFormat.format(calendar.getTime());
		weekly.add(tuesday);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
		String wednesday = simpleDateFormat.format(calendar.getTime());
		weekly.add(wednesday);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
		String thursday = simpleDateFormat.format(calendar.getTime());
		weekly.add(thursday);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
		String friday = simpleDateFormat.format(calendar.getTime());
		weekly.add(friday);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
		// 출력 형식 지정, 한 주 끝 날짜(토요일)
		// 시간을 '23:59:59'으로 설정
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
		String finishDate = simpleDateFormat.format(calendar.getTime());
		System.out.println("finishDate: " + finishDate);
		weekly.add(finishDate);
		
//		NutrientVO thisWeek = service.doRetrieveWeek(userId, startDate, finishDate);
		ArrayList<Integer> weekKcal = service.doRetrieveWeekKcal(userId, weekly);
		ArrayList<Double> weekCarbo = service.doRetrieveWeekCarbo(userId, weekly);
		ArrayList<Double> weekProtein = service.doRetrieveWeekProtein(userId, weekly);
		ArrayList<Double> weekFat = service.doRetrieveWeekFats(userId, weekly);
		ArrayList<Double> weekSugars = service.doRetrieveWeekSugars(userId, weekly);
		List<EatVO> ateList = service.doRetrieveAte(userId, formatedNow);
		
		// "yy/MM/dd" 형식을 "yyyy년 MM월 dd일"로 변환
        String convertedDate = convertDateFormat(formatedNow, "yy/MM/dd", "yyyy년 MM월 dd일");
		
		modelAndView.setViewName("user/mypage");
		modelAndView.addObject("oneDay", oneDay);
//		modelAndView.addObject("thisWeek", thisWeek);
		modelAndView.addObject("weekKcal", weekKcal);
		modelAndView.addObject("weekCarbo", weekCarbo);
		modelAndView.addObject("weekProtein", weekProtein);
		modelAndView.addObject("weekFat", weekFat);
		modelAndView.addObject("weekSugars", weekSugars);
		modelAndView.addObject("convertedDate", convertedDate);
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("finishDate", finishDate);
		modelAndView.addObject("ateList", ateList);
		
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
