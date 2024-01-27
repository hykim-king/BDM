package com.test.bdm.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.test.bdm.food.dao.FoodDao;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodDao foodDao;
	
	public FoodServiceImpl() {}

	@Override
	public int doSaveFood(List<String> selectedFoodCode) {
		// TODO Auto-generated method stub
		return 0;
	}

}
