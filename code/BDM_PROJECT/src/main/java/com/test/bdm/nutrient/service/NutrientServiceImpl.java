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
	public NutrientVO doRetrieveOneDay(String userId, String formatedNow) throws SQLException {
		return dao.doRetrieveOneDay(userId, formatedNow);
	}

	@Override
	public NutrientVO doRetrieveWeek(String userId, String startDate, String finishDate) throws SQLException {
		return dao.doRetrieveWeek(userId, startDate, finishDate);
	}

}
