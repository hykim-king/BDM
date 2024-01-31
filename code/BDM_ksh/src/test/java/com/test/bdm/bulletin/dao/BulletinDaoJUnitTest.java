package com.test.bdm.bulletin.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.PcwkLogger;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BulletinDaoJUnitTest implements PcwkLogger {

	@Autowired
	BulletinDao dao;

	BulletinVO bulletin01;

	BulletinVO searchVO;

	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ setUp                             │");
		LOG.debug("└───────────────────────────────────┘");

		String title = "제목";// p99-01
		String contents = "내용";
		String regDt = "사용 하지 않음";
		String modDt = "사용 하지 않음";
		int readCnt = 0;
		String id = "ksh";
		String modId = "ksh";

		bulletin01 = new BulletinVO(dao.getBulletinSeq(), title + "제목1", contents + "제목1", regDt, modDt, readCnt, id,
				modId);

		searchVO = new BulletinVO();
		searchVO.setTitle(title);
	}

	@Ignore
	@Test
	public void updateReadCnt() throws SQLException {
		// 1.
		dao.doDeleteAll(searchVO);
		// 2.
		int flag = dao.doSave(bulletin01);
		assertEquals(1, flag);

		// 3.단건조회
		// 최초 등록자와 id가 동일하면 update 않됨
		bulletin01.setId(bulletin01.getId() + "U");
		flag = dao.updateReadCnt(bulletin01);
		assertEquals(1, flag);
		BulletinVO vs01 = dao.doSelectOne(bulletin01);

		assertEquals(vs01.getPostNo(), bulletin01.getPostNo());
		assertEquals(vs01.getReadCnt(), bulletin01.getReadCnt() + 1);
	}

	@Ignore
	@Test
	public void doRetrieve() throws SQLException {
		 searchVO.setSearchWord(searchVO.getTitle());

		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		// 1.
		dao.doDeleteAll(searchVO);
		// 2.
		int flag = dao.doSave(bulletin01);
		assertEquals(1, flag);

		List<BulletinVO> list = dao.doRetrieve(searchVO);

		for (BulletinVO vo : list) {
			LOG.debug(vo);
		}
	}

	//@Ignore
	@Test
	public void update() throws SQLException {

		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ update                            │");
		LOG.debug("└───────────────────────────────────┘");

		//dao.doDeleteAll(searchVO);

		int flag = dao.doSave(bulletin01);
		assertEquals(1, flag);

		BulletinVO vo01 = dao.doSelectOne(bulletin01);
		String title = vo01.getTitle() + "_U";
		String contents = vo01.getContents() + "_U";
		String modId = vo01.getModId() + "_U";

		vo01.setTitle(title);
		vo01.setContents(contents);
		vo01.setModId(modId);

		flag = dao.doUpdate(vo01);
		assertEquals(1, flag);

		BulletinVO vs01 = dao.doSelectOne(vo01);
		isSameBoard(vs01, vo01);
	}

	//@Ignore
	@Test
	public void addAndGet() throws SQLException {

		int flag = dao.doSave(bulletin01);
		assertEquals(1, flag);

		BulletinVO vo01 = dao.doSelectOne(bulletin01);

		isSameBoard(vo01, bulletin01);

	}

	private void isSameBoard(BulletinVO vo, BulletinVO bulletin) {
		assertEquals(vo.getPostNo(), bulletin.getPostNo());
		assertEquals(vo.getTitle(), bulletin.getTitle());
		assertEquals(vo.getContents(), bulletin.getContents());
		assertEquals(vo.getReadCnt(), bulletin.getReadCnt());
		assertEquals(vo.getId(), bulletin.getId());
		assertEquals(vo.getModId(), bulletin.getModId());

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