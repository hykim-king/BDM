package com.test.bdm.user.dao;

import java.sql.SQLException;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.user.domain.UserVO;

public interface UserDao extends WorkDiv<UserVO> {
	
	int idDuplicateCheck(UserVO inVO) throws SQLException;
	
	int getCount(UserVO inVO) throws SQLException;

}
