package com.test.bdm.news;
//package com.test.bdm.news.dao;
//
//import static org.junit.Assert.*;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
//import com.test.bdm.cmn.PcwkLogger;
//import com.test.bdm.news.dao.NewsDao;
//import com.test.bdm.news.domain.NewsVO;
//
//@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
//		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class NewsDaoJunitTest implements PcwkLogger {
//
//	@Autowired
//	NewsDao dao;
//	
//	NewsVO news;
//	
//	NewsVO searchVO;
//	
//	@Autowired  //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
//	ApplicationContext context;	
//	
//	@Before
//	public void setUp() throws Exception {
//		LOG.debug("┌───────────────────────────────────┐");
//		LOG.debug("│ setUp                             │");
//		LOG.debug("└───────────────────────────────────┘");		
//		
//		String title = "두둠칫";//p99-01
//		String contents = "내용 입니다";
//		String regDt    = "사용 하지 않음";
//		int    readCnt  = 0;
//		String id       ="chen";
//		
//		news = new NewsVO(dao.getNewsSeq(),title+"두둠칫", contents+"안녕", regDt, readCnt, id);
//		
//		searchVO = new NewsVO();
//		searchVO.setTitle(title);
//		
//	}
//	
//	@Test
//	public void updateReadCnt() throws SQLException {
//		//1.
//		dao.doDeleteAll(searchVO);
//		//2.
//		int flag = dao.doSave(news);
//		assertEquals(1, flag);
//		
//		
//		
//		//3.단건조회
//		//최초 등록자와 id가 동일하면 update 않됨
//		news.setId(news.getId()+"U");
//		flag = dao.updateReadCnt(news);
//		assertEquals(1, flag);
//		NewsVO vs01 = dao.doSelectOne(news);
//		
//		assertEquals(vs01.getPostNo(), news.getPostNo());
//		assertEquals(vs01.getReadCnt(), news.getReadCnt()+1);
//	}
//	
//	
//	
//	@Test
//	public void doRetrieve() throws SQLException {
//		//searchVO.setSearchDiv("10");//제목으로 검색
//		//searchVO.setSearchWord(searchVO.getTitle());
//		
//		searchVO.setPageNo(1);
//		searchVO.setPageSize(10);
//		//1.
//		
//		//2.
//		int flag = dao.doSave(news);
//		assertEquals(1, flag);
//		
//	
//		
//		List<NewsVO> list=dao.doRetrieve(searchVO);
//		
//		for(NewsVO vo :list) {
//			LOG.debug(vo);
//		}
//	}
//	@Ignore
//	@Test
//	public void addAndGet()throws SQLException{
//		//1. 삭제
//		//2. 등록
//		//3. 단건조회
//		
//	
//		LOG.debug("news.getSeq():"+news.getPostNo());
//		
//		
//		//2.
//		int flag = dao.doSave(news);
//		assertEquals(1, flag);
//		
//
//	
//		
//		
//		
//	}
//	
//	@Test
//	public void beans() {
//		LOG.debug("┌───────────────────────────────────┐");
//		LOG.debug("│ beans                             │");
//		LOG.debug("│ dao                               │"+dao);
//		LOG.debug("│ context                           │"+context);
//		LOG.debug("└───────────────────────────────────┘");
//		
//		assertNotNull(dao);
//		assertNotNull(context);
//		//assertNotNull(board01);
//	}
//
//}
