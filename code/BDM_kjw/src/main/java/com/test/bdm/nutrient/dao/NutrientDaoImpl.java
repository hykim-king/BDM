package com.test.bdm.nutrient.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.nutrient.domain.NutrientVO;

@Repository
public class NutrientDaoImpl implements NutrientDao, PcwkLogger {

	final String NAMESPACE = "com.test.bdm.nutrient";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public NutrientDaoImpl() {	}

	@Override
	public List<NutrientVO> doRetrieve(NutrientVO inVO) throws SQLException {
		LOG.debug("inVO:"+inVO);
		
		List<NutrientVO> list = sqlSessionTemplate.selectList(NAMESPACE + DOT + "doRetrieve", inVO);
		LOG.debug("list:"+list);
		return  list;
	}

	@Override
	public NutrientVO doRetrieveOneDay(String userId, String formatedNow) throws SQLException {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("formatedNow", formatedNow);
		
		NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveOneDay", map);
		LOG.debug("outVO:"+outVO);
		return  outVO;
	}

	@Override
	public NutrientVO doRetrieveWeek(String userId, String startDate, String finishDate) throws SQLException {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("startDate", startDate);
		map.put("finishDate", finishDate);
		
		NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveWeek", map);
		LOG.debug("outVO:"+outVO);
		return  outVO;
	}

	@Override
	public ArrayList<Integer> doRetrieveWeekKcal(String userId, ArrayList<String> weekly) throws SQLException {
		
		ArrayList<Integer> weekKcal = new ArrayList<Integer>();
		
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<weekly.size(); i++) {
			map.put("userId", userId);
			map.put("day", weekly.get(i));
			
			NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveWeekKcal", map);
			int kcal = 0;
			
			if(Objects.nonNull(outVO)) {
				weekKcal.add(outVO.getKcal());
			}
			else {
				weekKcal.add(kcal);
			}
		}
		
		LOG.debug("weekKcal:"+weekKcal);
		return  weekKcal;
	}

	@Override
	public ArrayList<Double> doRetrieveWeekCarbo(String userId, ArrayList<String> weekly) throws SQLException {
		ArrayList<Double> weekCarbo = new ArrayList<Double>();
		
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<weekly.size(); i++) {
			map.put("userId", userId);
			map.put("day", weekly.get(i));
			
			NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveWeekCarbo", map);
			double kcal = 0;
			
			if(Objects.nonNull(outVO)) {
				weekCarbo.add(outVO.getCarbohydrate());
			}
			else {
				weekCarbo.add(kcal);
			}
		}
		
		LOG.debug("weekCarbo:"+weekCarbo);
		return  weekCarbo;
	}

	@Override
	public ArrayList<Double> doRetrieveWeekProtein(String userId, ArrayList<String> weekly) throws SQLException {
		ArrayList<Double> weekProtein = new ArrayList<Double>();
		
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<weekly.size(); i++) {
			map.put("userId", userId);
			map.put("day", weekly.get(i));
			
			NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveWeekProtein", map);
			double kcal = 0;
			
			if(Objects.nonNull(outVO)) {
				weekProtein.add(outVO.getProtein());
			}
			else {
				weekProtein.add(kcal);
			}
		}
		
		LOG.debug("weekProtein:"+weekProtein);
		return  weekProtein;
	}

	@Override
	public ArrayList<Double> doRetrieveWeekFats(String userId, ArrayList<String> weekly) throws SQLException {
		ArrayList<Double> weekFats = new ArrayList<Double>();
		
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<weekly.size(); i++) {
			map.put("userId", userId);
			map.put("day", weekly.get(i));
			
			NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveWeekFats", map);
			double kcal = 0;
			
			if(Objects.nonNull(outVO)) {
				weekFats.add(outVO.getFat());
			}
			else {
				weekFats.add(kcal);
			}
		}
		
		LOG.debug("weekFats:"+weekFats);
		return  weekFats;
	}

	@Override
	public ArrayList<Double> doRetrieveWeekSugars(String userId, ArrayList<String> weekly) throws SQLException {
		ArrayList<Double> weekSugars = new ArrayList<Double>();
		
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<weekly.size(); i++) {
			map.put("userId", userId);
			map.put("day", weekly.get(i));
			
			NutrientVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doRetrieveWeekSugars", map);
			double kcal = 0;
			
			if(Objects.nonNull(outVO)) {
				weekSugars.add(outVO.getSugars());
			}
			else {
				weekSugars.add(kcal);
			}
		}
		
		LOG.debug("weekSugars:"+weekSugars);
		return  weekSugars;
	}

	@Override
	public List<NutrientVO> doRetrieveAte(String userId, String formatedNow) throws SQLException {
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("formatedNow", formatedNow);
		
		List<NutrientVO> outVO = sqlSessionTemplate.selectList(NAMESPACE + DOT + "doRetrieveAte", map);
		LOG.debug("outVO:"+outVO);
		return  outVO;
	}

}
