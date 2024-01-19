package com.test.bdm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
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

import com.test.bdm.user.UserDao;
import com.test.bdm.user.UserVO;



@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoJUnitTest {

	final Logger LOG = LogManager.getLogger(getClass());
	@Autowired
	UserDao dao;
	// 등록
	UserVO userVO01;
	UserVO userVO02;
	UserVO userVO03;

	// getCount에 사용
	UserVO searchVO;
	
	
	
	@Autowired  //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {

		// 등록
		userVO01 = new UserVO("sohee",1, "4321","kcc2021@naver.com",  "강소희",950325,2,180,49,"사용하지않음",1);
		userVO02 = new UserVO("sohee2",1, "43211","kcc2021@naver.com",  "강소희2",950325,2,180,49,"사용하지않음",1);
		userVO03 = new UserVO("sohee3",1, "43212","kcc2021@naver.com",  "강소희3",950325,2,180,49,"사용하지않음",1);
 

	
		LOG.debug("====================");
		LOG.debug("=context=" + context);
		LOG.debug("=dao=" + dao);
		LOG.debug("====================");
	}

	


		
		
//	@Ignore	
//	@Test
//	public void idDuplicateCheck()throws SQLException{
//		//1.데이터 삭제
//		//2.데이터 입력
//		//3.idCheck
//		//1.
//		dao.doDelete(userVO01);
//		dao.doDelete(userVO02);
//		dao.doDelete(userVO03);
//		
//		assertEquals(0,dao.getCount(searchVO));
//		//2.
//		int flag = dao.doSave(userVO01);
//		//3
//		assertEquals(1, flag);
//		assertEquals(1,dao.getCount(searchVO));
//		
//		flag = dao.doSave(userVO02);
//		assertEquals(1, flag);
//		assertEquals(2,dao.getCount(searchVO));
//		
//		flag = dao.doSave(userVO03);
//		assertEquals(1, flag);
//		assertEquals(3,dao.getCount(searchVO));			
//		
//		//idCheck : id가 있는 경우
//		int idCheckCnt = dao.idDuplicateCheck(userVO01);
//		assertEquals(1, idCheckCnt);
//		
//		
//		//id가 없는 경우 : 
//		userVO01.setUserId("unknown_user");
//		idCheckCnt= dao.idDuplicateCheck(userVO01);
//		assertEquals(0, idCheckCnt);
//		
//	}
	//@Ignore  //테스트 disable
	@Test(timeout = 30000) // long 1/1000초
	public void addAndGet() throws SQLException {

		
		int flag = dao.doSave(userVO01);
		assertThat(flag, is(1));

		// UserVO02등록
		flag = dao.doSave(userVO02);
		assertThat(flag, is(1));

		// UserVO03등록
		flag = dao.doSave(userVO03);
		assertThat(flag, is(1));

	
	}



	

}
