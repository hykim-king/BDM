package com.test.bdm.food.dao;

import java.util.List;

public interface FoodDao {

	int doSaveFood(String userId, List<String> selectedFoodCode, List<Double> amountList, String divs);
}
