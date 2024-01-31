package com.test.bdm.user.dao;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.domain.UserVO;

@Repository
public class UserDaoImpl implements UserDao, PcwkLogger {
	
	final String NAMESPACE = "com.test.bdm.user";
	final String DOT = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public UserDaoImpl() {}

	@Override
	public int doCheckPassword(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doCheckPassword", inVO);
	}

	@Override
	public int doCheckEmail(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doCheckEmail", inVO);
	}

	@Override
	public int doCheckId(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doCheckId", inVO);
	}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.insert(NAMESPACE + DOT + "doSave", inVO);
	}
	
	
}
