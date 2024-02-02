package com.test.bdm.notice.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.notice.dao.NoticeDao;
import com.test.bdm.notice.domain.NoticeVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoticeControllerJUnitTest implements PcwkLogger {
	
	@Autowired
	NoticeDao dao;

	@Autowired
	WebApplicationContext webApplicationContext;
	// 브라우저 대역
	MockMvc mockMvc;
	List<NoticeVO> noticeList;
	NoticeVO searchVO;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ setUp()                                   │");
		LOG.debug("└───────────────────────────────────────────┘");
		
		String title    = "제목";
		String contents = "내용";
		String regDt   = "사용 하지 않음";
		int    readCnt = 0;
		String id       = "MN_LEE";

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		noticeList = Arrays.asList(new NoticeVO(dao.getNoticeSeq(), title, contents, regDt, readCnt, id));
		searchVO = new NoticeVO();
		searchVO.setTitle(title);
	}
	
	//@Ignore
	@Test
	public void doSave() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSave  ()                                │");
		LOG.debug("└───────────────────────────────────────────┘");

		NoticeVO vo = noticeList.get(0);
		// url, 호출방식(get), seq
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/notice/doSave.do")
				.param("postNo", vo.getPostNo() + "")
				.param("title", vo.getTitle())
				.param("contents", vo.getContents())
				// .param("regDt",vo.getRegDt())
				// .param("modDt", vo.getModDt())
				.param("readCnt", vo.getReadCnt() + "")
				.param("id", vo.getId());
		// 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk());
		// 호출결과
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │" + result);
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		LOG.debug("│ messageVO                                │" + messageVO);
		assertEquals("1", messageVO.getMsgId());
	}

	@Test
	public void beans() {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ webApplicationContext                     │" + webApplicationContext);
		LOG.debug("│ mockMvc                                   │" + mockMvc);
		LOG.debug("│ dao                                       │" + dao);
		LOG.debug("└───────────────────────────────────────────┘");
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(dao);
	}

}
