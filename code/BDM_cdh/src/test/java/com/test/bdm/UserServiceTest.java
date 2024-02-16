package com.test.bdm;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;
import com.test.bdm.user.service.UserService;




@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Resource(name ="dummyMailSender" )
	MailSender  mailSender;

	
	@Autowired
	UserDao  userDao;
	
	@Autowired
	UserService  userService;
	
	List<UserVO> users;
	UserVO searchVO;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("**********************************************************");
		LOG.debug("=setUp()=");		
		
		//BASIC
		//BASIC -> SILVER(O)
		//SILVER
		//SILVER -> GOLD(O)
		//GOLD
		
		users = Arrays.asList(
			 
		);
		
		searchVO = new UserVO();
		searchVO.setUserId("p99");
	}

	
	

	
	
	
	//@Ignore
	@Test
	public void beans() {
		LOG.debug("====================");
		LOG.debug("=beans()=");
		LOG.debug("=mailSender="+mailSender);

		LOG.debug("=userDao="+userDao);
		LOG.debug("=userService="+userService);
		LOG.debug("====================");
		
				
	}

}
