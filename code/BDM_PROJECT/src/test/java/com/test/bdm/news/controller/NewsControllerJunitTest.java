package com.test.bdm.news.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.code.domain.CodeVO;
import com.test.bdm.news.dao.NewsDao;
import com.test.bdm.news.domain.NewsVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewsControllerJunitTest implements PcwkLogger {

	@Autowired
	NewsDao dao;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	//브라우저 대역
	MockMvc  mockMvc;
	List<NewsVO>  newsList;
	NewsVO   searchVO;
	
	
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ setUp()                                   │");		
		LOG.debug("└───────────────────────────────────────────┘");	
		
		String title ="제목";//p99-01
		String contents = "내용 ";
		String   regDt = "사용하지않음";
	    int  readCnt = 0;
		String id =  "chen";
		
		
		mockMvc  = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		newsList = Arrays.asList(new NewsVO(dao.getNewsSeq(),title, contents+"안녕", regDt, readCnt, id));
		
		searchVO = new NewsVO();
		searchVO.setTitle(title);
		
	}
	
	
	
	@Test
	public void doRetrieve() throws Exception{
		//검색
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doRetrieve()                              │");		
		LOG.debug("└───────────────────────────────────────────┘");
		
		MockHttpServletRequestBuilder  requestBuilder  =
				MockMvcRequestBuilders.get("/news/doRetrieve.do")
				.param("pageSize",   "0")
				.param("pageNo",     "0")
				.param("searchDiv",  "")
				.param("searchWord", "")
				;		
		
		//호출 : ModelAndView      
		MvcResult mvcResult=  mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn() ;
		//호출결과
		ModelAndView modelAndView = mvcResult.getModelAndView();
		List<NewsVO>  list  = (List<NewsVO>) modelAndView.getModel().get("list");
		NewsVO  paramVO  = (NewsVO) modelAndView.getModel().get("vo");
		
		
		List<CodeVO> newsSearchList=(List<CodeVO>) modelAndView.getModel().get("newsSearch");
		List<CodeVO> pageSizeList=(List<CodeVO>) modelAndView.getModel().get("pageSize");
		
		for(NewsVO vo  :list) {
			LOG.debug(vo);
		}
		
		assertNotNull(newsSearchList);
		assertNotNull(pageSizeList);
		assertNotNull(list);
		assertNotNull(paramVO);
		
	}
	
	
	@Test
	public void beans() {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ webApplicationContext                     │"+webApplicationContext);		
		LOG.debug("│ mockMvc                                   │"+mockMvc);
		LOG.debug("│ dao                                       │"+dao);
		LOG.debug("└───────────────────────────────────────────┘");			
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(dao);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
