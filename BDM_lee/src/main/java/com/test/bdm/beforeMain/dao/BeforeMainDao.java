package com.test.bdm.beforeMain.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.bdm.cmn.DTO;
import com.test.bdm.user.domain.UserVO;

public interface BeforeMainDao {
	
	int idCheck(UserVO inVO)throws SQLException;
	
	int idPassCheck(UserVO inVO)throws SQLException;
	
	UserVO doSelectOne(UserVO inVO) throws SQLException;
	
	int doSaveSearch(int gender, int birth, String words) throws SQLException;
	
	List<DTO> popSearchWord() throws SQLException;
}
