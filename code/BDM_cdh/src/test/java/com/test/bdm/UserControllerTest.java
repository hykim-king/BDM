package com.test.bdm;



import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.test.bdm.user.domain.UserVO;




@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	
	//브라우저 대역
	MockMvc  mockMvc;
	
	List<UserVO> users;
	UserVO searchVO;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ setUp()                                   │");		
		LOG.debug("└───────────────────────────────────────────┘");	
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		users = Arrays.asList(
				 new UserVO("chen_01", 1, "4321","ehgus0420@naver.com","천도현_01",010420,1,180,75, "2024-01-19",1),
				 new UserVO("chen_02", 1, "4321","ehgus0420@naver.com","천도현_02",010420,1,180,75, "2024-01-19",1),
				 new UserVO("chen_03", 1, "4321","ehgus0420@naver.com","천도현_03",010420,1,180,75, "2024-01-19",1),
				 new UserVO("chen_04", 1, "4321","ehgus0420@naver.com","천도현_04",010420,1,180,75, "2024-01-19",1)
			);
			
		searchVO = new UserVO();
		searchVO.setUserId("p99");
			
	}
	
	@Ignore
	public UserVO doSelectOne(UserVO  inVO) throws Exception{
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSelectOne()                             │");		
		LOG.debug("└───────────────────────────────────────────┘");		
		
		//UserVO  inVO = users.get(0);
		//url + 호출방식(get) + param(userId)
		MockHttpServletRequestBuilder  requestBuilder = 
				MockMvcRequestBuilders.get("/user/doSelectOne.do")
				.param("userId",        inVO.getUserId());	
		
		ResultActions resultActions=this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ result                                    │"+result);		
		LOG.debug("└───────────────────────────────────────────┘");		
		
		UserVO outVO = new Gson().fromJson(result, UserVO.class);
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ outVO                                     │"+outVO);		
		LOG.debug("└───────────────────────────────────────────┘");				
		assertNotNull(outVO);
		
		return outVO;
		
	}
//	@Ignore
//	@Test
//	public void doUpdate() throws Exception {
//		LOG.debug("┌───────────────────────────────────────────┐");
//		LOG.debug("│ doUpdate                                  │");		
//		LOG.debug("└───────────────────────────────────────────┘");
//		
//		UserVO inVO = users.get(0);
//		String upStr = "_U";
//		int upNum    = 100;
//		MockHttpServletRequestBuilder  requestBuilder = 
//                MockMvcRequestBuilders.post("/user/doUpdate.do")
//               .param("userId",        inVO.getUserId())
//               .param("name",          inVO.getName()+upStr)
//               .param("password",      inVO.getPassword()+upStr)
//               .param("levelIntValue", (inVO.getLevelIntValue())+"")
//               .param("login",         (inVO.getLogin()        +upNum)+"")
//               .param("recommend",     (inVO.getRecommend()    +upNum)+"")
//               .param("email",         inVO.getEmail()+upStr);		
//		ResultActions resultActions=this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
//		
//		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
//		LOG.debug("┌───────────────────────────────────────────┐");
//		LOG.debug("│ result                                    │"+result);		
//		LOG.debug("└───────────────────────────────────────────┘");			
//		
//		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
//		assertEquals(String.valueOf(1), messageVO.getMsgId());
//		LOG.debug("┌───────────────────────────────────────────┐");
//		LOG.debug("│ messageVO                                 │"+messageVO);		
//		LOG.debug("└───────────────────────────────────────────┘");					
//		
//	}
	
	@Ignore
	@Test
	public void addAndGet() throws Exception {
		// 1. 데이터 삭제
		// 2. 등록
		// 3. 한건조회  		
		
		//1.
		doDelete(users.get(0));
		doDelete(users.get(1));
		doDelete(users.get(2));
		doDelete(users.get(3));
		doDelete(users.get(4));
		
		// 2. 
		doSave(users.get(0));
		doSave(users.get(1));
		doSave(users.get(2));
		doSave(users.get(3));
		doSave(users.get(4));
		
		
		
	}
	
//	private void isSameUser(UserVO userVO, UserVO outVO) {
//		assertEquals(userVO.getUserId(), outVO.getUserId());
//		assertEquals(userVO.getName(), outVO.getName());
//		assertEquals(userVO.getPassword(), outVO.getPassword());
//		
//		assertEquals(userVO.getLevel(), outVO.getLevel());//등급
//		assertEquals(userVO.getLogin(), outVO.getLogin());//로그인 횟수
//		assertEquals(userVO.getRecommend(), outVO.getRecommend());//추천수
//		assertEquals(userVO.getEmail(), outVO.getEmail());//email
//
//	}
	
	@Ignore
	public void doDelete(UserVO  inVO) throws Exception{
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doDelete()                                │");		
		LOG.debug("└───────────────────────────────────────────┘");
		//UserVO  inVO = users.get(0);
		//url + 호출방식(get) + param(userId)
		MockHttpServletRequestBuilder  requestBuilder = 
				MockMvcRequestBuilders.get("/user/doDelete.do")
				.param("userId",        inVO.getUserId());
		
		ResultActions resultActions=this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ result                                    │"+result);		
		LOG.debug("└───────────────────────────────────────────┘");				
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		//assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ messageVO                                 │"+messageVO);		
		LOG.debug("└───────────────────────────────────────────┘");			
	}
	
   
	public void doSave(UserVO  inVO) throws Exception{
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSave()                                  │");		
		LOG.debug("└───────────────────────────────────────────┘");			
		
		//MockMvcRequestBuilders : param 데이터 저장
		//MockMvc: 호출
		//UserVO  inVO = users.get(0);
		
		MockHttpServletRequestBuilder  requestBuilder = 
				                 MockMvcRequestBuilders.post("/user/doSave.do")
				                .param("userId",        inVO.getUserId())
				                .param("number",          inVO.getNumber()+"")
				                .param("password",      inVO.getPassword())
				                .param("email",         inVO.getEmail())
				                .param("name", inVO.getName())
				                .param("birth",         inVO.getBirth() +"")
				                .param("gender",     inVO.getGender() +"")
				                .param("height", inVO.getHeight()+"")
				                .param("weight", inVO.getWeight()+"")
				                .param("regDt", inVO.getRegDt())
				                .param("filter", inVO.getFilter()+"");
		
		
		
		ResultActions resultActions=this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ result                                    │"+result);		
		LOG.debug("└───────────────────────────────────────────┘");			
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ messageVO                                 │"+messageVO);		
		LOG.debug("└───────────────────────────────────────────┘");			
	}

	
	@Test
	public void beans() {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ beans()                                   │");
		LOG.debug("│ webApplicationContext                     │"+webApplicationContext);
		LOG.debug("│ mockMvc                                   │"+mockMvc);
		LOG.debug("└───────────────────────────────────────────┘");		
		
		assertNotNull(mockMvc);
		assertNotNull(webApplicationContext);
		
	}

}