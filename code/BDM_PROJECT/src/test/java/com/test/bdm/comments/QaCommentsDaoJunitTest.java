package com.test.bdm.comments;

import static org.junit.Assert.*;

import java.sql.SQLException;

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

import com.test.bdm.bulletin.dao.BulletinDao;
import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.comments.dao.CommentsDao;
import com.test.bdm.comments.dao.QaCommentsDao;
import com.test.bdm.comments.domain.CommentsVO;
import com.test.bdm.comments.domain.QaCommentsVO;
import com.test.bdm.qa.dao.QaDao;
import com.test.bdm.qa.domain.QaVO;



@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QaCommentsDaoJunitTest implements PcwkLogger {

	@Autowired
	QaCommentsDao  dao;   
	
	@Autowired
	QaDao  qaDao;
	
	QaVO qa01;
	
	QaCommentsVO qaComments01;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ setUp                             │");
		LOG.debug("└───────────────────────────────────┘");		
		
		//board01 = new BoardVO(boardDao.getBoardSeq(), "10", "제목55", "내용55", 0, "사용X", "pcwk99", "사용X", "pcwk99");
		//(bulletinDao.getBulletinSeq(), title + "제목1", contents + "제목1", regDt, modDt, readCnt, id, modId);
		qa01 = new QaVO(qaDao.getQaSeq(), "테스트제목", "테스트내용", "사용하지않음", "hsm", 0);
		
		int postNo = qa01.getPostNo();
		
		qaComments01 = new QaCommentsVO(dao.getRegNo(),"댓글내용-01","사용하지않음", postNo,"hsm","hsm");
		
	}
	//@Ignore
	@Test
	public void addAndGet() throws SQLException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ addAndGet                         │");
		LOG.debug("└───────────────────────────────────┘");
		
		//board등록 하고
		qaDao.doDelete(qa01);//게시글 삭제
		
		int flag = qaDao.doSave(qa01);
		
		//1. 삭제
		//2. 등록
		//3. 데이터 조회
		//4. 조회데이터 비교
		
		//1.?050278403271
		dao.doDelete(qaComments01);
	
		
		//2.
		flag = dao.doSave(qaComments01);
		assertEquals(1, flag);
		
	
		
		//3.
		QaCommentsVO vo01 = dao.doSelectOne(qaComments01);
		
		
		//4. 비교
		isSameQaComments(vo01,qaComments01);
	}  
	
	private void isSameQaComments(QaCommentsVO vo01, QaCommentsVO comments01) {
		assertEquals(vo01.getRegNo(), comments01.getRegNo());
		assertEquals(vo01.getPostNo(), comments01.getPostNo());
		assertEquals(vo01.getContents(), comments01.getContents());
		assertEquals(vo01.getId(), comments01.getId());
		assertEquals(vo01.getModId(), comments01.getModId());
		
	}
	
	@Ignore
	@Test
	public void doSave() throws SQLException{
		LOG.debug("┌───────────────────────────────────┐");
		LOG.debug("│ doSave                            │");
		LOG.debug("└───────────────────────────────────┘");				
		//board등록 하고
		qaDao.doDelete(qa01);//게시글 삭제
		
		int flag = qaDao.doSave(qa01);
		assertEquals(1, flag);
		
		
		//댓글 등록----------------------------------
		//댓글 삭제, 등록
		dao.doDelete(qaComments01);
		
		flag = dao.doSave(qaComments01);
		assertEquals(1, flag);
		
		
	}
	
	//@Ignore
	@Test
	public void beans() {
		LOG.debug("────────────────────────────────────");
		LOG.debug("beans"                               );
		LOG.debug("dao: " + dao                         );
		LOG.debug("bulletinDao: " + qaDao               );
		LOG.debug("────────────────────────────────────");				
		assertNotNull(dao);
		assertNotNull(qaDao);
	}

}
