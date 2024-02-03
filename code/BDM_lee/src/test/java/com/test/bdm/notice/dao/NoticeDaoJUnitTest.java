package com.test.bdm.notice.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.notice.domain.NoticeVO;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoticeDaoJUnitTest implements PcwkLogger {
	
	@Autowired
	NoticeDao dao;
	
	NoticeVO  notice01;
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

		notice01 = new NoticeVO(dao.getNoticeSeq(), title, contents, regDt, readCnt, id);
				
		searchVO = new NoticeVO();
		searchVO.setTitle(title);
	}
	
	@Test
	public void updateReadCnt() throws SQLException {
		//1.
		dao.doDeleteAll(searchVO);
		//2.
		int flag = dao.doSave(notice01);
		assertEquals(1, flag);
		
		//3.단건조회
		//최초 등록자와 id가 동일하면 update 안됨
		notice01.setId(notice01.getId()+"U");
		flag = dao.updateReadCnt(notice01);
		assertEquals(1, flag);
		NoticeVO vs01 = dao.doSelectOne(notice01);
		
		assertEquals(vs01.getPostNo(), notice01.getPostNo());
		assertEquals(vs01.getReadCnt(), notice01.getReadCnt()+1);
	}
	
	@Test
	public void doRetrieve() throws SQLException {
		//searchVO.setSearchDiv("10");//제목으로 검색
		//searchVO.setSearchWord(searchVO.getTitle());
		
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		//1.
		dao.doDeleteAll(searchVO);
		//2.
		int flag = dao.doSave(notice01);
		assertEquals(1, flag);
		
		List<NoticeVO> list=dao.doRetrieve(searchVO);
		
		for(NoticeVO vo :list) {
			LOG.debug(vo);
		}
	}
	
	@Test
	public void update() throws SQLException {
		//1. 데이터 삭제
		//2. 데이터 등록
		//3. 등록데이터 조회
		//4. 3번 조회된 데이터를 수정
		//5. update
		//6. 수정데이터 조회
		//7. 비교
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ update                            │");
		LOG.debug("└───────────────────────────────────┘");
		
		//1.
		dao.doDeleteAll(searchVO);
		//2.
		int flag = dao.doSave(notice01);
		assertEquals(1, flag);
		
		//3.
		NoticeVO vo01 =dao.doSelectOne(notice01);
		String title = vo01.getTitle()+"_U";
		String contents = vo01.getContents()+"_U";
		
		
		//4.
		vo01.setTitle(title);
		vo01.setContents(contents);
		
		
		
		//5.
		flag = dao.doUpdate(vo01);
		assertEquals(1, flag);
		
		//6. 
		NoticeVO vs01=dao.doSelectOne(vo01);
		isSameNotice(vs01, vo01);
	}
	
	@Test
	public void addAndGet()throws SQLException{
		//1. 삭제
		//2. 등록
		//3. 단건조회
		
		//1.
		dao.doDelete(notice01);
		
		LOG.debug("notice01.getPostNo():"+notice01.getPostNo());
		
		//2.
		int flag = dao.doSave(notice01);
		assertEquals(1, flag);
		
		//3.
		NoticeVO vo01 = dao.doSelectOne(notice01);
		
		isSameNotice(vo01, notice01);
	}
	
	private void isSameNotice(NoticeVO vo, NoticeVO notice) {
		assertEquals(vo.getPostNo(), notice.getPostNo());
		assertEquals(vo.getTitle(), notice.getTitle());
		assertEquals(vo.getContents(), notice.getContents());
		assertEquals(vo.getReadCnt(), notice.getReadCnt());
		assertEquals(vo.getId(), notice.getId());

	}

	@Test
	public void beans() {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ beans                             │");
		LOG.debug("│ dao                               │" + dao);
		LOG.debug("│ context                           │" + context);
		LOG.debug("└───────────────────────────────────┘");

		assertNotNull(dao);
		assertNotNull(context);
	}

}
