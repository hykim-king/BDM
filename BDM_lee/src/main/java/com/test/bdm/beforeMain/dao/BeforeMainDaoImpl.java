package com.test.bdm.beforeMain.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.DTO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.domain.UserVO;

@Repository
public class BeforeMainDaoImpl implements BeforeMainDao, PcwkLogger {
	
	final String NAMESPACE = "com.test.bdm.beforeMain";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int idCheck(UserVO inVO) throws SQLException {
		int count = 0;
		LOG.debug("1.param :" + inVO.toString());
		String statement = NAMESPACE+DOT+"idCheck";
		LOG.debug("2.statement :" + statement);
		
		count = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3.count :n" + count);
		return count;
	}

	@Override
	public int idPassCheck(UserVO inVO) throws SQLException {
		int count = 0;
		LOG.debug("1.param :" + inVO.toString());
		String statement = NAMESPACE+DOT+"idPassCheck";
		LOG.debug("2.statement :" + statement);
		
		count =sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3.count :n" + count);
		return count;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException {
		UserVO  outVO = null;
		LOG.debug("1.param :" + inVO.toString());
		String statement = NAMESPACE+DOT+"doSelectOne";
		LOG.debug("2.statement :" + statement);
		outVO=sqlSessionTemplate.selectOne(statement, inVO);
		if(null != outVO) {
			LOG.debug("3.outVO \n" + outVO.toString());
		}
		return outVO;
	}

	@Override
	public int doSaveSearch(int gender, int birth, String words) throws SQLException {
		int flag = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("gender", gender);
		map.put("birth", birth);
		map.put("words", words);
		
		flag = sqlSessionTemplate.insert(NAMESPACE + DOT + "doSaveSearch", map);
		
		return flag;
	}

	@Override
	public List<DTO> popSearchWord() throws SQLException {
		List<DTO> wordList;
		wordList = sqlSessionTemplate.selectList(NAMESPACE + DOT + "popSearchWord");
		
		return wordList;
	}
}
