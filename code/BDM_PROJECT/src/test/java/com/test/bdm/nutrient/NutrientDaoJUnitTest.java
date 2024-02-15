package com.test.bdm.nutrient;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.nutrient.dao.NutrientDao;
import com.test.bdm.nutrient.domain.NutrientVO;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NutrientDaoJUnitTest implements PcwkLogger{
	
	@Autowired
	NutrientDao dao;
	
	@Autowired
	ApplicationContext context;
	
	NutrientVO searchVO;

	@Before
	public void setUp() throws Exception {
		searchVO = new NutrientVO();
	}
	
	@Test
	public void doRetrieveWeek() throws SQLException, ParseException {
		Date  now = new Date();
		
		 // 포맷 정의        
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
		String pattern = "yy/MM/dd HH:mm:ss";
		// 출력용으로 사용할 데이트 포맷
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		// 포맷 적용        
		String formatedNow = simpleDateFormat.format(now);
		
		System.out.println(formatedNow);
		
		Date date1=new SimpleDateFormat(pattern).parse(formatedNow);
		
		Calendar calendar = Calendar.getInstance();
		// calendar 구조체에 오늘 날짜 지정
		calendar.setTime(date1);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		// 출력 형식 지정, 한 주 시작 날짜(일요일)
		String startDate = simpleDateFormat.format(calendar.getTime());
		System.out.println(startDate);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
		// 출력 형식 지정, 한 주 끝 날짜(토요일)
		String finishDate = simpleDateFormat.format(calendar.getTime());
		System.out.println(finishDate);
		
		NutrientVO outVO = dao.doRetrieveWeek("firstUser", startDate, finishDate);
		
		LOG.debug(outVO);
	}

	@Test
	public void doRetrieveOneDay() throws SQLException {
		LocalDate now = LocalDate.now();
		
		 // 포맷 정의        
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		// 포맷 적용        
		String formatedNow = now.format(formatter);         
		// 결과 출력        
		System.out.println(formatedNow);
		
		NutrientVO outVO = dao.doRetrieveOneDay("firstUser", "24/02/02");
		
		LOG.debug(outVO);
	}
	
	@Test
	public void doRetrieve() throws SQLException{
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		searchVO.setSearchWord("김치");
		
		List<NutrientVO> list = dao.doRetrieve(searchVO);
		
		for(NutrientVO vo : list) {
			LOG.debug(vo);
		}
	}

	@Test
	public void beans() {
		assertNotNull(dao);
		assertNotNull(context);
	}

}
