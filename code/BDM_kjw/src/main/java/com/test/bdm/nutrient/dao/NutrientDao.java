package com.test.bdm.nutrient.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.nutrient.domain.NutrientVO;

public interface NutrientDao {
	
	List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException;
}
