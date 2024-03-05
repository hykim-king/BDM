package com.test.bdm.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.UserDTO;
import com.test.bdm.user.controller.UserController;
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
		UserController controller = new UserController();
		UserVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doFindSalt", inVO);
		String salt = outVO.getSalt();
		byte[] password = controller.String_to_Byte(inVO.getPw());
		try {
			inVO.setPw(controller.Hashing(password, salt));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.update(NAMESPACE + DOT + "doUpdate", inVO);
	}
	
	@Override
	public UserVO doFindId(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doFindId", inVO);
	}

	@Override
	public UserVO doFindPassword(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doFindPassword", inVO);
	}

	@Override
	public int changePassword(UserVO inVO) throws SQLException {
		return this.sqlSessionTemplate.update(NAMESPACE + DOT + "changePassword", inVO);
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		UserController controller = new UserController();
		UserVO outVO = sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doFindSalt", inVO);
		String salt = outVO.getSalt();
		LOG.debug("salt: "+ salt);
		byte[] password = controller.String_to_Byte(inVO.getPw());
		try {
			inVO.setPw(controller.Hashing(password, salt));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.sqlSessionTemplate.delete(NAMESPACE + DOT + "doDelete", inVO);
	}
	
	@Override
	public List<UserVO> doRetrieve(UserDTO inVO) throws SQLException {
		return sqlSessionTemplate.selectList(NAMESPACE + DOT + "doRetrieve", inVO);
	}

	@Override
	public UserVO doSelectOneByEmail(UserVO inVO) throws SQLException, EmptyResultDataAccessException {
		UserVO  outVO = null;
		LOG.debug("1.param :" + inVO.toString());
		String statement = NAMESPACE+DOT+"doSelectOneByEmail";
		LOG.debug("2.statement :" + statement);
		outVO = sqlSessionTemplate.selectOne(statement, inVO);
		if(null != outVO) {
			LOG.debug("3.outVO \n" + outVO.toString());
		}
		return outVO;
	}

	@Override
	public UserVO doSelectOne(String id) throws SQLException {
		return sqlSessionTemplate.selectOne(NAMESPACE + DOT + "doSelectOne", id);
	}

	@Override
	public int doBlock(UserVO inVO) throws SQLException {
		String block = inVO.getBlock();
		
		if(block.equals("0")) {
			return sqlSessionTemplate.update(NAMESPACE + DOT + "doCancleBlock", inVO);
		}
		else {
			return sqlSessionTemplate.update(NAMESPACE + DOT + "doBlock", inVO);
		}
	}

	@Override
	public List<UserVO> doSelectBlockUsers(UserDTO inVO) throws SQLException {
		return sqlSessionTemplate.selectList(NAMESPACE + DOT + "doSelectBlockUsers", inVO);
	}
}
