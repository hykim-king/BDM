package com.test.bdm.user;

import java.sql.SQLException;


import com.test.bdm.cmn.WorkDiv;


public interface UserDao extends WorkDiv<UserVO> {
	

	
	int idDuplicateCheck(UserVO inVO) throws SQLException;
	
	int doSave(UserVO inVO) throws SQLException;


}