package com.test.bdm.nutrient.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bdm.nutrient.domain.NutrientVO;

public interface NutrientService {
	List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException;
	
	NutrientVO doRetrieveOneDay(String userId, String formatedNow) throws SQLException;
	
	NutrientVO doRetrieveWeek(String userId, String startDate, String finishDate) throws SQLException;
	
	ArrayList<Integer> doRetrieveWeekKcal(String userId, ArrayList<String> weekly) throws SQLException;
	
	ArrayList<Double> doRetrieveWeekCarbo(String userId, ArrayList<String> weekly) throws SQLException;
	
	ArrayList<Double> doRetrieveWeekProtein(String userId, ArrayList<String> weekly) throws SQLException;
	
	ArrayList<Double> doRetrieveWeekFats(String userId, ArrayList<String> weekly) throws SQLException;
	
	ArrayList<Double> doRetrieveWeekSugars(String userId, ArrayList<String> weekly) throws SQLException;
	
	List<NutrientVO> doRetrieveAte(String userId, String formatedNow) throws SQLException;
}
