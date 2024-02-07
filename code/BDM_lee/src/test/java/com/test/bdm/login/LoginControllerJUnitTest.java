package com.test.bdm.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.login.dao.LoginDao;
import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginControllerJUnitTest implements PcwkLogger {
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대역
	MockMvc  mockMvc;
	
	List<UserVO> users;
	UserVO searchVO;
	UserVO outVO;
	
	@Autowired
	UserDao  dao;
	
	@Autowired
	LoginDao lDao;

	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────┐");
		LOG.debug("│ setUp     │");
		LOG.debug("└───────────┘");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		users = Arrays.asList(new UserVO("lee", 1, "1234", "lee@email.com", "갱", 101010, 2, 170, 60, "사용안함"),
							  new UserVO("lee2", 1, "1234", "lee@email.com", "갱", 101010, 2, 170, 60, "사용안함"));
		
		searchVO = new UserVO();
		searchVO.setId("lee");
		
		outVO = new UserVO();
	}
	
	@Test
	public void doLogin()throws Exception{
		//1.데이터 삭제
		//2.데이터 입력
		//3.login
		
//		dao.doDelete(users.get(0));
//		dao.doDelete(users.get(1));
		
		//2
		//int flag = dao.doSave(users.get(0));
//		assertEquals(1, flag);
//		assertEquals(1, dao.getCount(searchVO));
		
		//flag = dao.doSave(users.get(1));
//		assertEquals(1, flag);
//		assertEquals(2, dao.getCount(searchVO));
		
		LOG.debug("┌───────────┐");
		LOG.debug("│ doLogin() │");
		LOG.debug("└───────────┘");
		
//		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/doLogin.do").param("id", users.get(0).getId()).param("pw", users.get(0).getPw());
//		
//		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
//		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
//		LOG.debug("┌───────────┐");
//		LOG.debug("│ result    │"+result);
//		LOG.debug("└───────────┘");
//		
//		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
//		assertEquals(String.valueOf(30), messageVO.getMsgId());
		
//		LOG.debug("┌───────────┐");
//		LOG.debug("│ messageVO │"+messageVO);
//		LOG.debug("└───────────┘");
		
		outVO = lDao.doSelectOne(searchVO);	// users 배열 인덱스 0번째꺼
		
		if(outVO == null) {
			LOG.debug("로그인 실패");
		}
		else {
			LOG.debug("성공");
		}
		
	}

	@Test
	public void beans() {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ beans()                                   │");		
		
		LOG.debug("webApplicationContext:"+webApplicationContext);	
		LOG.debug("mockMvc:"+mockMvc);	
		LOG.debug("dao:"+dao);	
		
		LOG.debug("└───────────────────────────────────────────┘");
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(dao);
	}

}
