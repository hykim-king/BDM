package com.test.bdm.beforeMain.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.cmn.DTO;
import com.test.bdm.user.domain.UserVO;

public interface BeforeMainService {

	int loginCheck(UserVO inVO)throws SQLException;
	
	UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException;
	
	int doSaveSearch(int gender, int birth, String words) throws SQLException;
	
	List<DTO> popSearchWord() throws SQLException;
	
	UserVO doSelectOneByEmail(UserVO inVO) throws SQLException, EmptyResultDataAccessException;
}