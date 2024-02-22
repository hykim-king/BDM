package com.test.bdm.nutrient.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bdm.nutrient.dao.NutrientDao;
import com.test.bdm.nutrient.domain.NutrientVO;

@Service("NutrientServiceImpl")
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
	
	@Override
	public ArrayList<Integer> doRetrieveWeekKcal(String userId, ArrayList<String> weekly) throws SQLException {
		return dao.doRetrieveWeekKcal(userId, weekly);
	}

	@Override
	public ArrayList<Double> doRetrieveWeekCarbo(String userId, ArrayList<String> weekly) throws SQLException {
		return dao.doRetrieveWeekCarbo(userId, weekly);
	}

	@Override
	public ArrayList<Double> doRetrieveWeekProtein(String userId, ArrayList<String> weekly) throws SQLException {
		return dao.doRetrieveWeekProtein(userId, weekly);
	}

	@Override
	public ArrayList<Double> doRetrieveWeekFats(String userId, ArrayList<String> weekly) throws SQLException {
		return dao.doRetrieveWeekFats(userId, weekly);
	}

	@Override
	public ArrayList<Double> doRetrieveWeekSugars(String userId, ArrayList<String> weekly) throws SQLException {
		return dao.doRetrieveWeekSugars(userId, weekly);
	}

	@Override
	public List<NutrientVO> doRetrieveAte(String userId, String formatedNow) throws SQLException {
		return dao.doRetrieveAte(userId, formatedNow);
	}

}
