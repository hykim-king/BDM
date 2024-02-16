package com.test.bdm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;


@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임웤그의 JUnit의 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoJUnitTest {

	final Logger LOG = LogManager.getLogger(getClass());
	@Autowired
	UserDao dao;
	// 등록
	UserVO userVO01;
	UserVO userVO02;
	UserVO userVO03;

	// getCount에 사용
	UserVO searchVO;
	
	
	
	@Autowired  //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {

		// 등록
		userVO01 = new UserVO("chen_04", 1, "4321","ehgus0420@naver.com","천도현_04",010420,1,180,75, "2024-01-19",1);
		userVO02 = new UserVO("chen_05", 1, "4321","ehgus0420@naver.com","천도현_05",010420,1,180,75, "2024-01-19",1);
		userVO03 = new UserVO("chen_06", 1, "4321","ehgus0420@naver.com","천도현_06",010420,1,180,75, "2024-01-19",1);
 
		// getCount에 사용
		
		
		
		LOG.debug("====================");
		LOG.debug("=context=" + context);
		LOG.debug("=dao=" + dao);
		LOG.debug("====================");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=tearDown==========================");
	}

	
		
	@Ignore	
	@Test
	public void idDuplicateCheck()throws SQLException{
		//1.데이터 삭제
		//2.데이터 입력
		//3.idCheck
		//1.
		dao.doDelete(userVO01);
		dao.doDelete(userVO02);
		dao.doDelete(userVO03);
		
		assertEquals(0,dao.getCount(searchVO));
		//2.
		int flag = dao.doSave(userVO01);
		//3
		assertEquals(1, flag);
		assertEquals(1,dao.getCount(searchVO));
		
		flag = dao.doSave(userVO02);
		assertEquals(1, flag);
		assertEquals(2,dao.getCount(searchVO));
		
		flag = dao.doSave(userVO03);
		assertEquals(1, flag);
		assertEquals(3,dao.getCount(searchVO));			
		
		//idCheck : id가 있는 경우
		int idCheckCnt = dao.idDuplicateCheck(userVO01);
		assertEquals(1, idCheckCnt);
		
		
		//id가 없는 경우 : 
		userVO01.setUserId("unknown_user");
		idCheckCnt= dao.idDuplicateCheck(userVO01);
		assertEquals(0, idCheckCnt);
		
	}
	
	@Ignore
	@Test
	public void doRetrieve()throws SQLException{
		LOG.debug("====================");
		LOG.debug("=doRetrieve()=");
		LOG.debug("====================");			
		
		searchVO.setPageSize(10L);
		searchVO.setPageNo(1L);
		searchVO.setSearchDiv("30");
		searchVO.setSearchWord("ehgus0420@naver.com");
		
		List<UserVO> list = dao.doRetrieve(this.searchVO);
		assertEquals(3, list.size());
	}
	
	@Ignore
	@Test
	public void getAll()throws SQLException{
		//1.데이터 삭제
		//2.데이터 입력
		//3.건수확인
		//4.getAll()
		//5.3건 
		//6.데이터 비교
		LOG.debug("====================");
		LOG.debug("=getAll()=");
		LOG.debug("====================");		
		
		//1.
		dao.doDelete(userVO01);
		dao.doDelete(userVO02);
		dao.doDelete(userVO03);
		
		assertEquals(0,dao.getCount(searchVO));
	
		//2.
		int flag = dao.doSave(userVO01);
		//3
		assertEquals(1, flag);
		assertEquals(1,dao.getCount(searchVO));
		
		flag = dao.doSave(userVO02);
		assertEquals(1, flag);
		assertEquals(2,dao.getCount(searchVO));
		
		flag = dao.doSave(userVO03);
		assertEquals(1, flag);
		assertEquals(3,dao.getCount(searchVO));		
		
		
		//4.
		List<UserVO> list = dao.getAll(searchVO);
		
		//5
		assertEquals(3, list.size());
		
		for(UserVO vo   :list) {
			LOG.debug(vo);
		}
		
	}
	
	
//	@Ignore
//	@Test
//	public void update() throws SQLException {
//		//1.데이터 삭제
//		//2.데이터 입력
//		//3.등록데이터 조회
//		//4.3번 조회된 데이터를 수정
//		//5.update
//		//6.수정데이터 조회
//		//7.비교
//		
//		LOG.debug("====================");
//		LOG.debug("=update()=");
//		LOG.debug("====================");		
//		
//		//1.
//		dao.doDelete(userVO01);
//		dao.doDelete(userVO02);
//		dao.doDelete(userVO03);
//		
//		assertEquals(0, dao.getCount(searchVO));
//		
//		//2.
//		dao.doSave(userVO01);
//		assertEquals(1, dao.getCount(searchVO));
//		dao.doSave(userVO02);
//		dao.doSave(userVO03);
//		
//		//3.
//		UserVO getVO = dao.doSelectOne(userVO01);
//		isSameUser(getVO, userVO01);
//		
//		//4
//		String upStr = "_U";
//		int    upInt = 10;
//		
//		getVO.setName(getVO.getName()+upStr);//이상무99_01+"_U"
//		getVO.setPassword(getVO.getPassword()+upStr);
//		
//		getVO.set();
//		
//		getVO.setLogin(getVO.getLogin()+upInt);
//		getVO.setRecommend(getVO.getRecommend()+upInt);
//		getVO.setEmail(getVO.getEmail()+upStr);
//		
//		//5.update
//		int flag = dao.doUpdate(getVO);
//		assertEquals(1, flag);
//		
//		//6.
//		UserVO vsVO = dao.doSelectOne(getVO);
//		
//		//7.
//		isSameUser(vsVO, getVO);
//		
//		
//	}
	
	//setUp() 
	//getFailure()
	//tearDown()
	//expected=예외, 예외가 발생하면 성공
	@Ignore
	@Test(expected = EmptyResultDataAccessException.class)
	public void getFailure() throws SQLException {
		LOG.debug("====================");
		LOG.debug("=getFailure=");
		LOG.debug("====================");
		// 1. 데이터 삭제
		// 2. 한건조회

		// 1.
		dao.doDelete(userVO01);
		dao.doDelete(userVO02);
		dao.doDelete(userVO03);

		userVO01.setUserId("unknown id");
		
		//2.
		dao.doSelectOne(userVO01);
		
	}

	 //테스트 disable
	@Test(timeout = 30000) // long 1/1000초
	public void addAndGet() throws SQLException {
		// 1. 데이터 삭제
		// 2. 등록
		// 3. 한건조회  

		// 1.
		
		// 2.
		int flag = dao.doSave(userVO01);
		
		

		// UserVO02등록
		flag = dao.doSave(userVO02);
	
		

		// UserVO03등록
		flag = dao.doSave(userVO03);
	

	}



}
