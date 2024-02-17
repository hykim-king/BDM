package com.test.bdm.user.dao;

import java.sql.SQLException;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.user.domain.UserVO;

public interface UserDao extends WorkDiv<UserVO>{

	int doCheckPassword(UserVO inVO) throws SQLException;

	int doCheckEmail(UserVO inVO) throws SQLException;

	int doCheckId(UserVO inVO) throws SQLException;
	
	int doSave(UserVO inVO) throws SQLException;
}
