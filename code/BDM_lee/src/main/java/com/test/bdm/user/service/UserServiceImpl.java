package com.test.bdm.user.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.user.dao.UserDao;
import com.test.bdm.user.domain.UserVO;

@Service
public class UserServiceImpl implements UserService,PcwkLogger {

	public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMEND_COUNT_FOR_GOLD = 30;

	@Autowired
	private UserDao userDao;

//	@Resource(name = "dummyMailSender")
//	private MailSender mailSender;

	public UserServiceImpl() {}


	@Override
	public int doSave(UserVO inVO) throws SQLException {
		return userDao.doSave(inVO);
	}

	@Override
	public int idDuplicateCheck(UserVO inVO) throws SQLException {
		return userDao.idDuplicateCheck(inVO);
	}

}
