package com.test.bdm.user;

import java.sql.SQLException;

import com.test.bdm.cmn.WorkDiv;


public interface UserDao extends WorkDiv<UserVO> {
	
	int doCheckPassword(UserVO inVO)throws SQLException;
	
	int doCheckEmail(UserVO inVO)throws SQLException;
	
	int idDuplicateCheck(UserVO inVO) throws SQLException;
	
	int doSave(UserVO inVO) throws SQLException;
	
	int doCheckId(UserVO inVO) throws SQLException;

	int getCount(UserVO inVO) throws SQLException;



}