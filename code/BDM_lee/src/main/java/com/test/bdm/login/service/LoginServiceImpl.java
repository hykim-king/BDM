package com.test.bdm.login.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.login.dao.LoginDao;
import com.test.bdm.user.domain.UserVO;

@Service
public class LoginServiceImpl implements LoginService, PcwkLogger {
	
	@Autowired
	LoginDao  loginDao;
	
	public LoginServiceImpl() {}

	@Override
	public int loginCheck(UserVO inVO) throws SQLException {
		//10:ID 없음
		//20:비번이상
		//30:로그인
		
		int checkStatus = 0;
		
		//idCheck
		int status = loginDao.idCheck(inVO);
		
		if(status == 0) {
			checkStatus = 10;
			LOG.debug("10 idCheck checkStatus:"+checkStatus);
			return checkStatus;
		}
		
		//idCheck : 비번 check
		status = loginDao.idPassCheck(inVO);
		if(status == 0) {
			checkStatus = 20;
			LOG.debug("20 idPassCheck checkStatus:"+checkStatus);
			return checkStatus;
		}
		
		//id,비번 정상 로그인
		checkStatus = 30;
		LOG.debug("30 idPassCheck pass checkStatus:"+checkStatus);
		return checkStatus;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException {
		
		return loginDao.doSelectOne(inVO);
	}
	
	

}
