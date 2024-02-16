package com.test.bdm.beforeMain.service;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.user.domain.UserVO;

public interface BeforeMainService {

	/**
	 * 데이터 베이스 ID비번 체크 
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	int loginCheck(UserVO inVO)throws SQLException;
	
	UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException;
}