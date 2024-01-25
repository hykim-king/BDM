package com.test.bdm.login;

import java.sql.SQLException;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.user.UserVO;



public interface LoginDao extends WorkDiv<UserVO>{
	
	int idCheck(UserVO inVO)throws SQLException;

	int idPassCheck(UserVO inVO)throws SQLException;

}
