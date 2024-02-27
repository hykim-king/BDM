package com.test.bdm.user.dao;

import java.sql.SQLException;

import com.test.bdm.user.domain.UserVO;

public interface UserDao {

	int doCheckPassword(UserVO inVO) throws SQLException;

	int doCheckEmail(UserVO inVO) throws SQLException;

	int doCheckId(UserVO inVO) throws SQLException;
	
	int doSave(UserVO inVO) throws SQLException;
	
	int doUpdate(UserVO inVO) throws SQLException;
	
	UserVO doFindId(UserVO inVO) throws SQLException;
	
	UserVO doFindPassword(UserVO inVO) throws SQLException;
	
	int changePassword(UserVO inVO) throws SQLException;

	int doDelete(UserVO inVO) throws SQLException;
}
