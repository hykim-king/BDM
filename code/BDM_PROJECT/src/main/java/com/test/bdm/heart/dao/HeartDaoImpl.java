package com.test.bdm.heart.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.heart.domain.HeartVO;

@Repository
public class HeartDaoImpl implements HeartDao,PcwkLogger {
	final String NAMESPACE = "com.test.bdm.heart";
	final String DOT	=".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;



	@Override
	public int save(HeartVO inVO) throws SQLException {
		int count = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "count", inVO);
		
		if(count == 0)
			return sqlSessionTemplate.insert(NAMESPACE+DOT+"save", inVO);
		else
			return sqlSessionTemplate.delete(NAMESPACE+DOT+"delete", inVO);
	}

	@Override
	public int delete(HeartVO inVO) throws SQLException {
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"delete", inVO);

	}

	@Override
	public List<HeartVO> selectHeart(HeartVO inVO) throws SQLException {
		return sqlSessionTemplate.selectList(NAMESPACE+DOT+"selectHeart", inVO);

	}

	@Override
	public int getPostNo() throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getPostNo");
	
	}

	@Override
	public int getId() throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getId");

	}

	@Override
	public int getHeartStatus(HeartVO heartVO) throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getHeartStatus");

	}

	@Override
	public int getCount(HeartVO heartVO) throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"count", heartVO);
	}

	@Override
	public int getTotalCount(HeartVO heartVO) throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE+DOT+"getTotalCount", heartVO);
	}


}
