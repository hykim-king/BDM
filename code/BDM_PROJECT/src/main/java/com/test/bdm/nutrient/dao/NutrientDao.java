package com.test.bdm.nutrient.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bdm.nutrient.domain.NutrientVO;

public interface NutrientDao {
	
	List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException;
	
	NutrientVO doRetrieveOneDay(String userId, String formatedNow) throws SQLException;
	
	NutrientVO doRetrieveWeek(String userId, String startDate, String finishDate) throws SQLException;
	
	ArrayList<Double> doRetrieveWeekKcal(String userId, ArrayList<String> weekly) throws SQLException;
}
