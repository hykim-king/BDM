package com.test.bdm.user.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;

@Service
public class UserServiceImpl implements PcwkLogger, UserService {
	
	@Autowired
	private UserDao userDao;
	
	public UserServiceImpl() {}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return userDao.doSave(inVO);
	}

}
