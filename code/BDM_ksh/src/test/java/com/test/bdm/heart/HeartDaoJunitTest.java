package com.test.bdm.heart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

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

import com.test.bdm.bulletin.dao.BulletinDao;
import com.test.bdm.bulletin.domain.BulletinVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.heart.dao.HeartDao;
import com.test.bdm.heart.domain.HeartVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeartDaoJunitTest implements PcwkLogger{
	
	@Autowired
	HeartDao dao;
	
	@Autowired
	BulletinDao bulletinDao;
	
	BulletinVO bulletin01;
	
	HeartVO heart01;
	
	@Before
	public void setUp() throws Exception {
		bulletin01 = new BulletinVO(bulletinDao.getBulletinSeq(), "title", "contents", "사용하지않음", "사용하지않음", 0, "ksh", "ksh");
		
		int postNo = bulletin01.getPostNo();
		
		heart01 = new HeartVO(bulletinDao.getBulletinSeq(),"ksh" , 1);
	}
	
	@Ignore
	@Test
	public void doSave() throws SQLException{
		
		int flag = bulletinDao.doSave(bulletin01);
		assertEquals(1, flag);
		
		flag = dao.delete(heart01);
		assertEquals(1, flag);
		
	}
	
	@Ignore
	@Test
	public void beans() {
		assertNotNull(dao);
		assertNotNull(bulletinDao);
	}
	
	


}
