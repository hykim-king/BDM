package com.test.bdm.qa;

import static org.junit.Assert.*;

import java.sql.SQLException;
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

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.qa.dao.QaDao;
import com.test.bdm.qa.domain.QaVO;
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QaDaoJUnitTest implements PcwkLogger {
	
	@Autowired
	QaDao dao;
	
	QaVO qa01;
	
	QaVO searchVO;
	
	@Autowired
	ApplicationContext context;

	
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ setUp                             │");
		LOG.debug("└───────────────────────────────────┘");
		
		String title    = "제목";
		String contents = "내용";
		String regDt   = "사용 하지 않음";
		String id       = "hsm";

	    qa01 = new QaVO(dao.getQaSeq(), title + "제목1", contents + "내용1", regDt, id, 1);

		searchVO = new QaVO();
		searchVO.setTitle(title);
	}
	
	@Ignore
	@Test
	public void addAndGet() throws SQLException {
		
		int flag = dao.doSave(qa01);
		assertEquals(1, flag);
		
		QaVO vo01 = dao.doSelectOne(qa01);
		isSameBoard(vo01, qa01);
	}
	
	private void isSameBoard(QaVO vo, QaVO qa) {
		assertEquals(vo.getPostNo(), qa.getPostNo());
		assertEquals(vo.getTitle(), qa.getTitle());
		assertEquals(vo.getRegDt(), qa.getRegDt());
		assertEquals(vo.getId(), qa.getId());
	}
	
	@Ignore
	@Test
	public void doDelete() throws SQLException {
		searchVO.setPostNo(1);
		int flag = dao.doDelete(searchVO);
		assertEquals(1, flag);
	}
	
	@Ignore
	@Test
	public void doRetrieve() throws SQLException {
		searchVO.setSearchWord(searchVO.getTitle());
		
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
		dao.doDeleteAll(searchVO);
		
		int flag = dao.doSave(qa01);
		assertEquals(1, flag);
		
		List<QaVO> list = dao.doRetrieve(searchVO);
		
		for(QaVO vo: list) {
			LOG.debug(vo);
		}
	}



	@Test
	public void beans() {
		LOG.debug("─────────────────────────────────────");
		LOG.debug("beans"                                );
		LOG.debug("dao: " + dao                          );
		LOG.debug("context: " + context                  );
		LOG.debug("─────────────────────────────────────");
		
		assertNotNull(dao);
		assertNotNull(context);
	}

}
