package com.test.bdm.cart;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.bdm.cart.dao.CartDao;
import com.test.bdm.cmn.PcwkLogger;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartDaoJUnitTest implements PcwkLogger {

	@Autowired
	CartDao dao;

	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 자동으로 객체값으로 주입
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void find(HttpServletRequest request) throws SQLException {
		String id = dao.findUser(request);
		
		LOG.debug("id: " + id);
	}

	@Test
	public void bean() {
		assertNotNull(dao);
		assertNotNull(context);
	}

}
