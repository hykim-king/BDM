package com.test.bdm.notice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.notice.dao.NoticeDao;
import com.test.bdm.notice.domain.NoticeVO;
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoticeDaoJUnitTest implements PcwkLogger {
	
	@Autowired
	NoticeDao dao;

	NoticeVO notice01;

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
	
	
//	@Ignore
	@Test
	public void update() throws SQLException {

		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ update                            │");
		LOG.debug("└───────────────────────────────────┘");

		// dao.doDeleteAll(searchVO);

		int flag = dao.doSave(notice01);
		assertEquals(1, flag);

		NoticeVO vo01 = dao.doSelectOne(notice01);
		String title = vo01.getTitle() + "_U";
		String contents = vo01.getContents() + "_U";

		vo01.setTitle(title);
		vo01.setContents(contents);

		flag = dao.doUpdate(vo01);
		assertEquals(1, flag);

		NoticeVO vs01 = dao.doSelectOne(vo01);
		isSameNotice(vs01, vo01);
	}
	
	
//	@Ignore
	@Test
	public void addAndGet() throws SQLException {

		int flag = dao.doSave(notice01);
		assertEquals(1, flag);

		NoticeVO vo01 = dao.doSelectOne(notice01);

		isSameNotice(vo01, notice01);

	}
	
	private void isSameNotice(NoticeVO vo, NoticeVO bulletin) {
		assertEquals(vo.getPostNo(), bulletin.getPostNo());
		assertEquals(vo.getTitle(), bulletin.getTitle());
		assertEquals(vo.getContents(), bulletin.getContents());
		assertEquals(vo.getReadCnt(), bulletin.getReadCnt());
		assertEquals(vo.getId(), bulletin.getId());

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
