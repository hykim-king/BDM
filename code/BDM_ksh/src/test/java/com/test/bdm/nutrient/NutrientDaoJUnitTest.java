package com.test.bdm.nutrient;

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

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.nutrient.dao.NutrientDao;
import com.test.bdm.nutrient.domain.NutrientVO;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NutrientDaoJUnitTest implements PcwkLogger{
	
	@Autowired
	NutrientDao dao;
	
	@Autowired
	ApplicationContext context;
	
	NutrientVO searchVO;

	@Before
	public void setUp() throws Exception {
		searchVO = new NutrientVO();
	}
	
	@Ignore
	@Test
	public void doRetrieve() throws SQLException{
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		searchVO.setSearchWord("김치");
		
		List<NutrientVO> list = dao.doRetrieve(searchVO);
		
		for(NutrientVO vo : list) {
			LOG.debug(vo);
		}
	}

	@Test
	public void beans() {
		assertNotNull(dao);
		assertNotNull(context);
	}

}
