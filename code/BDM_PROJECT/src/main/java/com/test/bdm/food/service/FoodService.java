package com.test.bdm.food.service;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

public interface FoodService {
	
	int doSaveFood(@ModelAttribute("selectedFoodCode") List<String> selectedFoodCode);

}