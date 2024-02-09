package com.test.bdm.notice;

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
import org.springframework.context.ApplicationContext;
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
import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.code.domain.CodeVO;
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
	
	//브라우저 대역
	MockMvc  mockMvc;
	List<NoticeVO>  noticeList;
	NoticeVO searchVO;
	
	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ setUp                             │");
		LOG.debug("└───────────────────────────────────┘");
		
		String title    = "제목";
		String contents = "내용";
		String regDt   = "사용 하지 않음";
		int    readCnt = 0;
		String id       = "MN_LEE";

		mockMvc  = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		new NoticeVO(dao.getNoticeSeq(), title, contents, regDt, readCnt, id);
		noticeList = Arrays.asList(new NoticeVO(dao.getNoticeSeq(), title, contents, regDt, readCnt, id));

		searchVO = new NoticeVO();
		searchVO.setTitle(title);
	}
	
	@Test
	public void doRetrieve() throws Exception{
		//검색
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doRetrieve()                              │");		
		LOG.debug("└───────────────────────────────────────────┘");
		
		MockHttpServletRequestBuilder  requestBuilder  =
				MockMvcRequestBuilders.get("/bulletin/doRetrieve.do")
				.param("pageSize",   "0")
				.param("pageNo",     "0")
				.param("searchDiv",  "")
				.param("searchWord", "")
				;		
		
		//호출 : ModelAndView      
		MvcResult mvcResult=  mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn() ;
		//호출결과
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		List<BulletinVO>  list  = (List<BulletinVO>) modelAndView.getModel().get("list");
		BulletinVO  paramVO  = (BulletinVO) modelAndView.getModel().get("paramVO");
		
		List<CodeVO> bulletinSearchList=(List<CodeVO>) modelAndView.getModel().get("bulletinSearch");
		List<CodeVO> pageSizeList=(List<CodeVO>) modelAndView.getModel().get("pageSize");
		
		for(BulletinVO vo  :list) {
			LOG.debug(vo);
		}
		
		assertNotNull(bulletinSearchList);
		assertNotNull(pageSizeList);
		assertNotNull(list);
		assertNotNull(paramVO);
		
	}
	
	
//	//@Ignore
//	@Test
//	public void update() throws SQLException {
//
//		LOG.debug("┌───────────────────────────────────┐");
//		LOG.debug("│ update                            │");
//		LOG.debug("└───────────────────────────────────┘");
//
//		// dao.doDeleteAll(searchVO);
//
//		int flag = dao.doSave(notice01);
//		assertEquals(1, flag);
//
//		NoticeVO vo01 = dao.doSelectOne(notice01);
//		String title = vo01.getTitle() + "_U";
//		String contents = vo01.getContents() + "_U";
//
//		vo01.setTitle(title);
//		vo01.setContents(contents);
//
//		flag = dao.doUpdate(vo01);
//		assertEquals(1, flag);
//
//		NoticeVO vs01 = dao.doSelectOne(vo01);
//		isSameNotice(vs01, vo01);
//	}
	
	
//	//@Ignore
//	@Test
//	public void addAndGet() throws SQLException {
//
//		int flag = dao.doSave(notice01);
//		assertEquals(1, flag);
//
//		NoticeVO vo01 = dao.doSelectOne(notice01);
//
//		isSameNotice(vo01, notice01);
//
//	}
	
//	@Ignore
	@Test
	public void doUpdate() throws Exception{
		//1.데이터 입력
		//2.등록데이터 조회
		//3.3번 조회된 데이터를 수정
		//4.update
		//5.수정데이터 조회
		//6.데이터 비교
		
		NoticeVO notice01 = noticeList.get(0);
		//1.
		int flag = dao.doSave(notice01);
		assertEquals(1, flag);
		
		//2.
		NoticeVO vo = dao.doSelectOne(notice01);
		
		//3.
		String upStr = "_U";
		vo.setSearchDiv("20");
		vo.setTitle(vo.getTitle()+upStr);
		vo.setContents(vo.getContents()+upStr);
		vo.setId(vo.getId()+upStr);
		
		
		//4. url, param, method(get/post)
		MockHttpServletRequestBuilder  requestBuilder = MockMvcRequestBuilders.post("/notice/doUpdate.do")
				.param("postNo",     vo.getPostNo()+"")
				.param("title",     vo.getTitle())
				.param("contents",     vo.getContents())
				.param("regDt",     vo.getRegDt())
				.param("readCnt",     vo.getReadCnt()+"")
				.param("id",     vo.getId())
				;
		//호출        
		ResultActions resultActions=  mockMvc.perform(requestBuilder).andExpect(status().isOk());
		//호출결과
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │"+result);		
		
		MessageVO messageVO=new Gson().fromJson(result, MessageVO.class);
		assertEquals("1", messageVO.getMsgId());
		
		//5.
		NoticeVO updateResult = dao.doSelectOne(vo);
		
		//6.
		isSameNotice(updateResult,vo);
	}
	
	private void isSameNotice(NoticeVO vo, NoticeVO notice) {
		assertEquals(vo.getPostNo(), notice.getPostNo());
		assertEquals(vo.getTitle(), notice.getTitle());
		assertEquals(vo.getContents(), notice.getContents());
		assertEquals(vo.getReadCnt(), notice.getReadCnt());
//		assertEquals(vo.getId(), notice.getId());

	}
	
//	@Ignore
	@Test
	public void doSelectOne()throws Exception{
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSelectOne()                             │");		
		LOG.debug("└───────────────────────────────────────────┘");
		
		int flag = dao.doSave(noticeList.get(0));
		assertEquals(1, flag);
		NoticeVO vo = noticeList.get(0);
		
		MockHttpServletRequestBuilder  requestBuilder = MockMvcRequestBuilders.get("/notice/doSelectOne.do")
				.param("postNo",     vo.getPostNo()+"")
				.param("id",     vo.getId())
				;		
		
		//호출 : ModelAndView      
		MvcResult mvcResult=  mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn() ;
		//호출결과
		ModelAndView modelAndView = mvcResult.getModelAndView();
		NoticeVO outVO = (NoticeVO) modelAndView.getModel().get("vo");
		LOG.debug("│ outVO                                │"+outVO);
		assertNotNull(outVO);
	}
	
//	@Ignore
	@Test
	public void doSave()throws Exception{
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doSave  ()                                │");		
		LOG.debug("└───────────────────────────────────────────┘");		
		
		NoticeVO vo = noticeList.get(0);
		//url, 호출방식(get), seq
		MockHttpServletRequestBuilder  requestBuilder = MockMvcRequestBuilders.post("/notice/doSave.do")
				.param("postNo",     vo.getPostNo()+"")
				.param("title",     vo.getTitle())
				.param("contents",     vo.getContents())
				.param("regDt",     vo.getRegDt())
				.param("readCnt",     vo.getReadCnt()+"")
				.param("id",     vo.getId())
				;
		//호출        
		ResultActions resultActions=  mockMvc.perform(requestBuilder).andExpect(status().isOk());
		//호출결과
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │"+result);		
		MessageVO messageVO=new Gson().fromJson(result, MessageVO.class);
		LOG.debug("│ messageVO                                │"+messageVO);
		assertEquals("1", messageVO.getMsgId());
	}
	
//	@Ignore
	@Test
	public void doDelete()throws Exception{
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ doDelete()                                │");		
		LOG.debug("└───────────────────────────────────────────┘");
		
		int flag = dao.doSave(noticeList.get(0));
		assertEquals(1, flag);
		
		//url, 호출방식(get), seq
		MockHttpServletRequestBuilder  requestBuilder = MockMvcRequestBuilders.get("/notice/doDelete.do")
				.param("postNo", noticeList.get(0).getPostNo() + "");
				//.param("seq", boardList.get(0).getSeq()+"");
		
		ResultActions resultActions=  mockMvc.perform(requestBuilder).andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("│ result                                │"+result);		
		MessageVO messageVO=new Gson().fromJson(result, MessageVO.class);
		LOG.debug("│ messageVO                                │"+messageVO);
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
