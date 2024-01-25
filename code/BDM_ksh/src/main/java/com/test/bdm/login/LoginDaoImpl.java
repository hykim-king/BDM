package com.test.bdm.login;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.user.UserVO;

@Repository
public class LoginDaoImpl implements LoginDao {
	final Logger LOG = LogManager.getLogger(getClass());

	final String NAMESPACE = "com.test.bdm.login";
	final String DOT       = ".";
	
	
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
	public int doSave(UserVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException {
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
	public int doDelete(final UserVO inVO) throws SQLException {
		int flag = 0;

		//----------------------------------------------------------------------
		String statement = this.NAMESPACE+this.DOT+"doDelete";
		LOG.debug("1.param \n" + inVO.toString());
		LOG.debug("2.statement \n" + statement);
		
		flag=this.sqlSessionTemplate.delete(statement, inVO);
		
		LOG.debug("3.flag \n" + flag);
		//------------------R----------------------------------------------------
		return flag;
	}


	@Override
	public List<UserVO> doRetrieve(UserVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
