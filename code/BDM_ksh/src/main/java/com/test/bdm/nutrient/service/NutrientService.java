package com.test.bdm.nutrient.service;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.nutrient.domain.NutrientVO;

public interface NutrientService {
	List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException;
	
	List<NutrientVO> doRetrieveOneDay() throws SQLException;
}
