package com.test.bdm.food.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;

@Repository
public class FoodDaoImpl implements FoodDao, PcwkLogger {
	
	final String NAMESPACE = "com.test.bdm.food";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public FoodDaoImpl() {}

	@Override
	public int doSaveFood(String userId, List<String> selectedFoodCode) {
		int count = 0;
		
		HashMap<String, String> map = new HashMap<>();
		
		LOG.debug("==========================================");
		LOG.debug("====" + userId + "======");
		LOG.debug("====" + selectedFoodCode + "======");
		LOG.debug("==========================================");
		
		for(int i=0; i<selectedFoodCode.size(); i++) {
			String foodCode = selectedFoodCode.get(i);
			map.put("userId", userId);
			map.put("foodCode", foodCode);
			LOG.debug("map: " + map);
			count += sqlSessionTemplate.insert(NAMESPACE + DOT + "doSaveFood", map);
			map.clear();
		}
		
		if(count == selectedFoodCode.size()) {
			count = 1;
		}
		else count = 0;
		
		return count;
	}
}
