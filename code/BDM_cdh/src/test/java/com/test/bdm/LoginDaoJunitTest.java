package com.test.bdm;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.bdm.login.dao.LoginDao;
import com.test.bdm.user.dao.UserDao;

import com.test.bdm.user.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginDaoJunitTest {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired  //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;
	
	@Autowired
	LoginDao  loginDao;
	
	@Autowired
	UserDao   dao;
	
	


	// getCount에 사용
	UserVO searchVO;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("====================");
		LOG.debug("=setUp=" );		
		LOG.debug("====================");
		// 등록
		
		// getCount에 사용
		searchVO = new UserVO();
		searchVO.setUserId("abc");
		searchVO.setPassword("14123");
	}
	
	@Test
	public void doLogin()throws SQLException {
		//1. 데이터 삭제
		//2. 데이터 입력
		//3. login
		
		int cnt = loginDao.idCheck(searchVO);
		assertEquals(1, cnt);
		
		//3.2 idPassCheck
		cnt = loginDao.idPassCheck(searchVO);
		assertEquals(1, cnt);
		
		//3.3. 단건조회
		UserVO outVO = loginDao.doSelectOne(searchVO);
	    
		if(outVO ==null ) {
			LOG.debug("없음");
		}else {
			LOG.debug("로그인 성공");
		}
		
	}
	
	private void isSameUser(UserVO userVO, UserVO outVO) {
		assertEquals(userVO.getUserId(), outVO.getUserId());
		assertEquals(userVO.getName(), outVO.getName());
		assertEquals(userVO.getPassword(), outVO.getPassword());
		
		assertEquals(userVO.getEmail(), outVO.getEmail());//email

	}
	
	
	
	@Test
	public void beans() {
		LOG.debug("====================");
		LOG.debug("=beans=");		
		LOG.debug("=context="+context);
		LOG.debug("=dao="+dao);		
		LOG.debug("=loginDao="+loginDao);	
		LOG.debug("====================");
		
		assertNotNull(context);
		assertNotNull(dao);
		assertNotNull(loginDao);
	}

}
