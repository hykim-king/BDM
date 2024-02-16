package com.test.bdm.nutrient.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.nutrient.domain.NutrientVO;

@Repository
public class NutrientDaoImpl implements NutrientDao, PcwkLogger {

	final String NAMESPACE = "com.test.bdm.nutrient";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public NutrientDaoImpl() {	}

	@Override
	public List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException {
		LOG.debug("inVO:"+inVO);
		
		List<NutrientVO> list = sqlSessionTemplate.selectList(NAMESPACE + DOT + "doRetrieve", inVO);
		LOG.debug("list:"+list);
		return  list;
	}

	@Override
	public NutrientVO doRetrieveOneDay(String userId, String formatedNow) throws SQLException {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("formatedNow", formatedNow);
		
		NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveOneDay", map);
		LOG.debug("outVO:"+outVO);
		return  outVO;
	}

	@Override
	public NutrientVO doRetrieveWeek(String userId, String startDate, String finishDate) throws SQLException {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("startDate", startDate);
		map.put("finishDate", finishDate);
		
		NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveWeek", map);
		LOG.debug("outVO:"+outVO);
		return  outVO;
	}

}
