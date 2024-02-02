//package com.test.bdm.bulletin.controller;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.AbstractMockMvcBuilder;
//import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.test.bdm.bulletin.dao.BulletinDao;
//import com.test.bdm.bulletin.domain.BulletinVO;
//import com.test.bdm.cmn.PcwkLogger;
//
//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
//		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class BulletinControllerJUnitTest implements PcwkLogger {
//	
//	@Autowired
//	BulletinDao dao;
//	
//	@Autowired
//	WebApplicationContext webApplicationContext;
//	
//	MockMvc mockMvc;
//	List<BulletinVO> bulletinList;
//	BulletinVO searchVO;
//
//	@Before
//	public void setUp() throws Exception {
//		LOG.debug("┌───────────────────────────────────────────┐");
//		LOG.debug("│ setUp()                                   │");		
//		LOG.debug("└───────────────────────────────────────────┘");
//		
//		int postNo = 2;
//		String title = "게시물 제목";
//		String contents = "테스트";
//		String regDt = "사용하지 않음";
//		String modDt = "사용하지 않음";
//		int readCnt = 30;
//		String id = "p10";
//		String modId = "p10";
//		
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		bulletinList = Arrays.asList(
//				new BulletinVO(dao.getBulletinPostNo(), title + "-01 제목", contents + "-01", regDt, readCnt, id, modId),
//				new BulletinVO(dao.getBulletinPostNo(), title + "-02 제목", contents + "-02", regDt, readCnt+1, id, modId),
//				new BulletinVO(dao.getBulletinPostNo(), title + "-03 제목", contents + "-03", regDt, readCnt+2, id, modId)
//		);
//		
//		searchVO = new BulletinVO();
//		searchVO.setTitle(title);
//	}
//
//	@Test
//	public void doRetrieve() throws Exception {
//		LOG.debug("┌───────────────────────────────────────────┐");
//		LOG.debug("│ doRetrieve()                              │");		
//		LOG.debug("└───────────────────────────────────────────┘");
//		
//		MockHttpServletRequestBuilder requestBuilder = 
//				MockMvcRequestBuilders.get("/bulletin/doRetrieve.do")
//				.param("pageSize", "0")
//				.param("pageNo", "0")
//				.param("searchDiv", "")
//				.param("searchWord", "");
//		
//		MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
//		ModelAndView modelAndView = mvcResult.getModelAndView();
//		List<BulletinVO> list = (List<BulletinVO>) modelAndView.getModel().get("list");
//		BulletinVO paramVO = (BulletinVO) modelAndView.getModel().get("vo");
//		
//		List<CodeVO> bulletinSearchList = (List<CodeVO>) modelAndView.getModel().get("bulletinSearch");
//		List<CodeVO> pageSizeList=(List<CodeVO>) modelAndView.getModel().get("pageSize");
//		
//		for(BulletinVO vo: list) {
//			LOG.debug(vo);
//		}
//		
//		assertNotNull(bulletinSearchList);
//		assertNotNull(pageSizeList);
//		assertNotNull(list);
//		assertNotNull(paramVO);
//	}
//	
//	@Test
//	public void doUpdate() throws Exception {
//		BulletinVO bulletin01 = bulletinList.get(0);
//		
//		int flag = dao.doSave(bulletin01);
//		assertEquals(1, flag);
//		
//		BulletinVO vo = dao.doSelectOne(bulletin01);
//		
//		String upStr = "_U";
//		
//	}
//
//}
