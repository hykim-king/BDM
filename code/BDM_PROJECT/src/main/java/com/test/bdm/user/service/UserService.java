package com.test.bdm.user.service;

import java.sql.SQLException;

import com.test.bdm.user.domain.UserVO;

public interface UserService {
	
	public int doCheckPassword(UserVO inVO) throws SQLException;

	public int doCheckEmail(UserVO inVO) throws SQLException;

	public int doCheckId(UserVO inVO) throws SQLException;
	
	public int doSave(UserVO inVO) throws SQLException;
	
	public int doUpdate(UserVO inVO) throws SQLException;

	UserVO doFindId(UserVO inVO) throws SQLException;
	
	UserVO doFindPassword(UserVO inVO) throws SQLException;
	
	int changePassword(UserVO inVO) throws SQLException;

	int doDelete(UserVO inVO) throws SQLException;
}
