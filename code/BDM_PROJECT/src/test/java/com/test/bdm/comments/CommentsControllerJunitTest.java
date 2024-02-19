package com.test.bdm.comments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.bdm.bulletin.dao.BulletinDao;
import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.comments.dao.CommentsDao;
import com.test.bdm.comments.domain.CommentsVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentsControllerJunitTest implements PcwkLogger {

	@Autowired
	CommentsDao dao;

	@Autowired
	WebApplicationContext webApplicationContext;

	// 브라우저 대역
	MockMvc mockMvc;

	@Autowired
	BulletinDao bulletinDao;

	BulletinVO bulletin01;
	
	BulletinVO searchVO;

	CommentsVO comments01;

	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ setUp()                                   │");
		LOG.debug("└───────────────────────────────────────────┘");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		bulletin01 = new BulletinVO(bulletinDao.getBulletinSeq(), "20240214", "contents", "사용하지않음", "사용하지않음", 0, "MN_LEE", "MN_LEE");

		int postNo = bulletin01.getPostNo();

		comments01 = new CommentsVO(dao.getRegNo(), "댓글내용-01", "사용하지않음", postNo, "MN_LEE", "MN_LEE");
	}

	@Ignore
	@Test
	public void doRetrieve() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doRetrieve                                │");
		LOG.debug("└───────────────────────────────────────────┘");

		this.bulletinDao.doDelete(bulletin01);
		int flag = bulletinDao.doSave(bulletin01);
		assertEquals(1, flag);

		// 댓글1. 등록
		flag = dao.doSave(comments01);
		assertEquals(1, flag);
		
		LOG.debug("│ c.getRegNo() │" + comments01.getRegNo());


		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comments/doRetrieve.do")
				.param("postNo", comments01.getPostNo() + "");

		// 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk());
		// 호출결과
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │" + result);

		Type listType = new TypeToken<List<CommentsVO>>() {
		}.getType();

		List<CommentsVO> list = new Gson().fromJson(result, listType);

		assertEquals(1, list.size());
		assertNotNull(list);

		for (CommentsVO vo : list) {
			LOG.debug(vo);
		}

	}

	@Ignore
	@Test
	public void doUpdate() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doUpdate()                                │");
		LOG.debug("└───────────────────────────────────────────┘");

		// Board 1.기존 데이터 삭제
		// 2.신규등록

		// 1
		// this.bulletinDao.doDelete(bulletin01);

		// 2
		int flag = bulletinDao.doSave(bulletin01);
		assertEquals(1, flag);

		// 댓글1. 등록
		flag = dao.doSave(comments01);
		assertEquals(1, flag);

		// 댓글2. 단건조회
		CommentsVO vo = dao.doSelectOne(comments01);

		// 댓글 수정
		vo.setContents(vo.getContents() + "_U");

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comments/doUpdate.do")
				.param("regNo", vo.getRegNo() + "").param("contents", vo.getContents());

		// 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk());
		// 호출결과
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │" + result);
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		LOG.debug("│ messageVO                                │" + messageVO);
		assertEquals("1", messageVO.getMsgId());
	}

	//@Ignore
	@Test
	public void doDelete() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doDelete()                                │");
		LOG.debug("└───────────────────────────────────────────┘");

		// Board 1.기존 데이터 삭제
		// 2.신규등록

		// 1
		this.bulletinDao.doDelete(bulletin01);

		// 2
		int flag = bulletinDao.doSave(bulletin01);
		assertEquals(1, flag);

		// Reply삭제
		CommentsVO vo = comments01;
		// 1. 등록
		flag = dao.doSave(comments01);
		assertEquals(1, flag);

		

		// post/get
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comments/doDelete.do")
				.param("regNo", vo.getRegNo()+"");

		// 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk());
		// 호출결과
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │" + result);
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		LOG.debug("│ messageVO                                │" + messageVO);
		assertEquals("1", messageVO.getMsgId());

	}

@Ignore
	@Test
	public void doSave() throws Exception {
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSave()                                  │");
		LOG.debug("└───────────────────────────────────────────┘");

		// this.bulletinDao.doDelete(bulletin01);

		int flag = bulletinDao.doSave(bulletin01);
		assertEquals(1, flag);

		CommentsVO vo = comments01;

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comments/doSave.do")
				.param("regNo", vo.getRegNo() + "").param("contents", vo.getContents())
				.param("postNo", vo.getPostNo() + "").param("id", vo.getId()).param("modId", vo.getModId());

		// MockHttpServletRequest request = new MockHttpServletRequest();
		// HttpSession session = new MockHttpSession();

		// UserVO user = new UserVO("ksh01", 85, "1234", "사용x", "강소희", 950325, 2, 179,
		// 49, "사용x", 1);
		// new UserVO(id, no, pw, email, name, birth, gender, height, weight, regDt,
		// userFilter)

		// 세션에 데이터 추가
		// session.setAttribute("user", user);

		// 요청에 세션 설정
		// request.setSession(session);

		// 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().isOk());
		// 호출결과
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │" + result);
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		LOG.debug("│ messageVO                                │" + messageVO);
		assertEquals("1", messageVO.getMsgId());

	}

	@Ignore
	@Test
	public void beans() {
		LOG.debug("│ dao                                   │" + dao);
		LOG.debug("│ webApplicationContext                 │" + webApplicationContext);
		LOG.debug("│ mockMvc                 │" + mockMvc);

		assertNotNull(dao);
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
	}

}
