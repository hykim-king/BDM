package com.test.bdm.user.dao;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.domain.UserVO;

@Repository
public class UserDaoImpl implements PcwkLogger, UserDao {
	
	final String NAMESPACE = "com.test.bdm.user";
	final String DOT = ".";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public UserDaoImpl() {}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
		int flag = 0;
		LOG.debug("1. param \n" + inVO.toString());
		
		String statement = this.NAMESPACE + DOT + "doSave";
		LOG.debug("2. statement \n" + statement);
		
		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("3. flag \n" + flag);
		
		return flag;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException {
		UserVO outVO = null;
		
		LOG.debug("1. param \n" + inVO.toString());
		
		String statement = NAMESPACE + DOT + "doSelectOne";
		LOG.debug("2. statement \n" + statement);
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		if(outVO != null) {
			LOG.debug("3. outVO \n" + outVO.toString());
		}
		
		return outVO;
	}

	@Override
	public int idDuplicateCheck(UserVO inVO) throws SQLException {
		int flag = 0;
		LOG.debug("1. param \n" + inVO,toString());
		
		flag = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "idDuplicateCheck", inVO);
		LOG.debug("2. flag \n" + flag);
		
		return flag;
	}

	@Override
	public int getCount(UserVO inVO) throws SQLException {
		int count = 0;
		LOG.debug("1. param \n" + inVO.toString());
		
		String statement = NAMESPACE + DOT + "getCount";
		LOG.debug("2. statement \n" + statement);
		
		count = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3. count \n" + count);
		
		return count;
	}

}
