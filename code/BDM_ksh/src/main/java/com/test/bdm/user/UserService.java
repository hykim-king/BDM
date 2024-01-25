package com.test.bdm.user;

import java.sql.SQLException;


public interface UserService {

public	int doCheckPassword(UserVO inVO)throws SQLException;
	
public	int doCheckEmail(UserVO inVO)throws SQLException;
	
public	int idDuplicateCheck(UserVO inVO) throws SQLException;
	
public	int doSave(UserVO inVO) throws SQLException;
	
public	int doCheckId(UserVO inVO) throws SQLException;

public	int getCount(UserVO inVO) throws SQLException;
	
	
}
