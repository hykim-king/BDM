package com.test.bdm.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoJUnitTest implements PcwkLogger {
	
	@Autowired
	UserDao dao;
	
	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;
	
	UserVO vo1;
	UserVO vo2;
	UserVO vo3;

	@Before
	public void setUp() throws Exception {
		vo1 = new UserVO("MN_KIM", 1, "1111", "kjew03@gmail.com", "김지우", 980120, 1, 111.1, 11.1, 1, "사용x", 2);
		vo2 = new UserVO("id5", 2, "2222", "2222", "이름2", 222222, 2, 111.1, 11.1, 1, "사용x", 2);
		vo3 = new UserVO("id7", 3, "3333", "3333", "이름3", 333333, 1, 111.1, 11.1, 1, "사용x", 2);
	}
	
	@Test
	public void findPassword() throws SQLException {
		String password = "";
		
		UserVO outVO = dao.doFindPassword(vo1);
		
		if(outVO == null) {
			LOG.debug("비밀번호를 찾을 수 없습니다.");
		}
		else {
			password = outVO.getPw();
			LOG.debug("찾으시는 비밀번호는 " + password + " 입니다.");
		}
	}

	@Ignore
	@Test
	public void findId() throws SQLException {
		String id = "";
		
		UserVO outVO = dao.doFindId(vo1);
		
		if(outVO == null) {
			LOG.debug("아이디를 찾을 수 없습니다.");
		}
		else {
			id = outVO.getId();
			LOG.debug("찾으시는 아이디는 " + id + " 입니다.");
		}
	}
	
	@Ignore
	@Test
	public void check() throws SQLException {
		int count = 0;
		
		count = dao.doCheckId(vo1);
		if (count == 1)
			LOG.debug("중복");
		else
			LOG.debug("사용 가능");
		
		count = dao.doCheckId(vo3);
		if (count == 1)
			LOG.debug("중복");
		else
			LOG.debug("사용 가능");
	}
	
	@Ignore
	@Test
	public void Add() throws ClassNotFoundException, SQLException {
		// 2. 데이터 등록
		int flag = dao.doSave(vo1);
		
		if (flag == 1)
			LOG.debug("등록 성공");
		else
			LOG.debug("등록 실패");

		dao.doSave(vo2);

		dao.doSave(vo3);
	}

	@Test
	public void beans() {
		assertNotNull(dao);
		assertNotNull(context);
	}

}
