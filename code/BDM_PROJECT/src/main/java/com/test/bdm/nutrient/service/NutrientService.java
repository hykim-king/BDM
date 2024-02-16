package com.test.bdm.nutrient.service;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.nutrient.domain.NutrientVO;

public interface NutrientService {
	List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException;
	
	NutrientVO doRetrieveOneDay(String userId, String formatedNow) throws SQLException;
	
	NutrientVO doRetrieveWeek(String userId, String startDate, String finishDate) throws SQLException;
}
