package com.test.bdm.nutrient.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bdm.nutrient.dao.NutrientDao;
import com.test.bdm.nutrient.domain.NutrientVO;

@Service
public class NutrientServiceImpl implements NutrientService {
	
	@Autowired
	NutrientDao dao;

	@Override
	public List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException {
		return dao.doRetrieve(inVO);
	}

	@Override
	public List<NutrientVO> doRetrieveOneDay() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
