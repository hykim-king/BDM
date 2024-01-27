package com.test.bdm.food.dao;

import java.util.List;

public interface FoodDao {

	int doSaveFood(List<String> selectedFoodCode);
}
