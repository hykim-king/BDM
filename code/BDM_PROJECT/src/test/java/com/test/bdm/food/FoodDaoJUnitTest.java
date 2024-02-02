package com.test.bdm.food;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.food.dao.FoodDao;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public class FoodDaoJUnitTest implements PcwkLogger {
	
	@Autowired
	FoodDao dao;
	
	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;
	
	String userId = "firstUser";
	
	List<String> foods = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		foods.add("D202-086000000-0041 ");
		foods.add("D202-086000000-0015 ");
	}
	
	@Test
	public void add() throws SQLException {
		int flag = 0;
		
		flag = dao.doSaveFood(userId, foods);
		
		if(flag == 1) {
			LOG.debug("=========================");
			LOG.debug("성공성공성공성공성공성공성공성공성공");
			LOG.debug("=========================");
		}
		else {
			LOG.debug("=========================");
			LOG.debug("실패실패실패실패실패실패실패실패실패");
			LOG.debug("=========================");
		}
	}

	@Test
	public void bean() {
		
	}

}
