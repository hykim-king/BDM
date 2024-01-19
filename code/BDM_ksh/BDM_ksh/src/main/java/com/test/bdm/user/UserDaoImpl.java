package com.test.bdm.user;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

	final Logger LOG = LogManager.getLogger(getClass());
	
	final String NAMESPACE = "com.test.bdm.user";
	final String DOT       = ".";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public UserDaoImpl() {}
	
	@Override
	public int doSave(UserVO inVO) throws SQLException {
		int flag = 0;
		LOG.debug("1.param \n" + inVO.toString());
		//----------------------------------------------------------------------

		String statement = this.NAMESPACE+DOT+"doSave";
		LOG.debug("2.statement \n" + statement);
		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("3.flag \n" + flag);
		
		return flag;
	}
	
	@Override
	public int idDuplicateCheck(UserVO inVO) throws SQLException {
		int flag = 0;
		LOG.debug("1.param :" + inVO.toString());
		
		flag = sqlSessionTemplate.selectOne(NAMESPACE+DOT+"idDuplicateCheck", inVO);
		LOG.debug("2.flag :" + flag);
		return flag;
	}



	
}
