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
	public int doSaveFood(String userId, List<String> selectedFoodCode, List<Double> amountList, String divs) {
		int count = 0;
		
		HashMap<String, Object> map = new HashMap<>();
		
		LOG.debug("==========================================");
		LOG.debug("====" + userId + "======");
		LOG.debug("====" + selectedFoodCode + "======");
		LOG.debug("====" + amountList + "======");
		LOG.debug("==========================================");
		
		for(int i=0; i<selectedFoodCode.size(); i++) {
			String foodCode = selectedFoodCode.get(i);
			double amount = amountList.get(i);
			map.put("userId", userId);
			map.put("foodCode", foodCode);
			map.put("amount", amount);
			map.put("divs", divs);
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
