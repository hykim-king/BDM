package com.test.bdm.user.service;

import java.sql.SQLException;

import com.test.bdm.user.domain.UserVO;

public interface UserService {
	
	public int doCheckPassword(UserVO inVO) throws SQLException;

	public int doCheckEmail(UserVO inVO) throws SQLException;

	public int doCheckId(UserVO inVO) throws SQLException;
	
	public int doSave(UserVO inVO) throws SQLException;

}
