package com.test.bdm.user;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public UserServiceImpl() {
	}
	
	
	
	@Override
	public int idDuplicateCheck(UserVO inVO) throws SQLException {
		return userDao.idDuplicateCheck(inVO);
	}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return userDao.doSave(inVO);
	}




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
	public int getCount(UserVO inVO) throws SQLException {
		return userDao.getCount(inVO);
	}




}
