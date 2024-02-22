package com.test.bdm.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.test.bdm.food.dao.FoodDao;

@Service("FoodServiceImpl")
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodDao foodDao;
	
	public FoodServiceImpl() {}

	@Override
	public int doSaveFood(String userId, List<String> selectedFoodCode, List<Double> amountList, String divs) {
		return foodDao.doSaveFood(userId, selectedFoodCode, amountList, divs);
	}

}
