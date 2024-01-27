package com.test.bdm.food.dao;

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
	public int doSaveFood(List<String> selectedFoodCode) {
		int count = 0;
		
		for(int i=0; i<selectedFoodCode.size(); i++) {
			count += sqlSessionTemplate.insert(NAMESPACE + DOT + "doSaveFood", selectedFoodCode.get(i));
		}
		
		if(count == selectedFoodCode.size()) {
			count = 1;
		}
		else count = 0;
		
		return count;
	}
}
