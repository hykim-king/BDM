package com.test.bdm.user;

import java.sql.SQLException;





public interface UserService {

	
	public int idDuplicateCheck(UserVO inVO) throws SQLException;
	
	
	public int doSave(UserVO inVO) throws SQLException;
	
	public void add(UserVO inVO) throws SQLException;	
	
	
}
