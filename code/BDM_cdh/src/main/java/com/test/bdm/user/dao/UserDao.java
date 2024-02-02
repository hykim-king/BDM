package com.test.bdm.user.dao;

import java.sql.SQLException;
import java.util.List;
import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.user.domain.UserVO;

public interface UserDao extends WorkDiv<UserVO> {
    
	
	List<UserVO> getAll(UserVO inVO) throws SQLException;

	int getCount(UserVO inVO) throws SQLException;
	
	int idDuplicateCheck(UserVO inVO) throws SQLException;
	
	

}