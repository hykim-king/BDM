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
	public int delete(HeartVO inVO) throws SQLException {
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"delete", inVO);

	}

	@Override
	public int save(HeartVO inVO) throws SQLException {
		return sqlSessionTemplate.delete(NAMESPACE+DOT+"save", inVO);

	}



	

}
