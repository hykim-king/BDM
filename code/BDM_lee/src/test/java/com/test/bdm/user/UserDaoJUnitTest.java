package com.test.bdm.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.sql.SQLException;

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
import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoJUnitTest implements PcwkLogger {
	
	
	@Autowired
	UserDao dao;
	
	// 등록
	UserVO userVO01;
	UserVO userVO02;
	//UserVO userVO03;

	// getCount에 사용
	UserVO searchVO;
	
	@Autowired  //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		
		//등록
		//userVO01 = new UserVO(id, no, pw, email, name, birth, gender, height, weight, regDt);
		userVO01 = new UserVO("lee", 1, "1234", "lee@email.com", "갱", 101010, 2, 170, 60, "사용안함");
		userVO02 = new UserVO("lee2", 2, "1234", "lee@email.com", "갱", 101010, 2, 170, 60, "사용안함");
 
		//getCount에 사용
//		searchVO.setId("lee");
		
		LOG.debug("====================");
		LOG.debug("=context=" + context);
		LOG.debug("=dao=" + dao);
		LOG.debug("====================");
		
	}
	
	//@Ignore  //테스트 disable
	@Test
	public void addAndGet() throws SQLException {
		// 1. 삭제
		// 2. 등록
		// 3. 한건조회  

		// 2.
		int flag = dao.doSave(userVO01);
		assertThat(flag, is(1));

		// 2-2. UserVO02등록
		flag = dao.doSave(userVO02);
		assertThat(flag, is(1));

	}
	

	@Test
	public void beans() {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ beans                             │");
		LOG.debug("│ dao                               │"+dao);
		LOG.debug("│ context                           │"+context);
		LOG.debug("└───────────────────────────────────┘");
		
		assertNotNull(dao);
		assertNotNull(context);
		
	}

}
