package com.test.bdm.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.cmn.UserDTO;
import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, PcwkLogger {

	@Autowired
	private UserDao userDao;
	
	public UserServiceImpl() {}
	
	@Override
	public int doCheckPassword(UserVO inVO) throws SQLException {
		return userDao.doCheckPassword(inVO);
	}

	@Override
	public int doCheckEmail(UserVO inVO) throws SQLException {
		return userDao.doCheckEmail(inVO);
	}

	@Override
	public int doCheckId(UserVO inVO) throws SQLException {
		return userDao.doCheckId(inVO);
	}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return userDao.doSave(inVO);
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		return userDao.doUpdate(inVO);
	}

	@Override
	public UserVO doFindId(UserVO inVO) throws SQLException {
		return userDao.doFindId(inVO);
	}

	@Override
	public UserVO doFindPassword(UserVO inVO) throws SQLException {
		return userDao.doFindPassword(inVO);
	}

	@Override
	public int changePassword(UserVO inVO) throws SQLException {
		return userDao.changePassword(inVO);
	}
	
	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		return userDao.doDelete(inVO);
	}
	
	@Override
	public List<UserVO> doRetrieve(UserDTO inVO) throws SQLException {
		return userDao.doRetrieve(inVO);
	}

	@Override
	public UserVO doSelectOneByEmail(UserVO inVO) throws SQLException, EmptyResultDataAccessException {
		return userDao.doSelectOneByEmail(inVO);
	}

	@Override
	public UserVO doSelectOne(String id) throws SQLException {
		return userDao.doSelectOne(id);
	}

	@Override
	public int doBlock(UserVO inVO) throws SQLException {
		return userDao.doBlock(inVO);
	}

	@Override
	public List<UserVO> doSelectBlockUsers(UserDTO inVO) throws SQLException {
		return userDao.doSelectBlockUsers(inVO);
	}
}
