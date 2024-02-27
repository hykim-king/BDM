package com.test.bdm.food.service;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

public interface FoodService {
	
	int doSaveFood(String userId, List<String> selectedFoodCode, List<Double> amountList, String divs);

}
