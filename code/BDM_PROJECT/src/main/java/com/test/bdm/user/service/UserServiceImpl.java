package com.test.bdm.user.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
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
}
