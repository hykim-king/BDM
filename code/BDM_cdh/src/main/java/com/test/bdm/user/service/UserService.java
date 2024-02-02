package com.test.bdm.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.test.bdm.user.domain.UserVO;

public interface UserService {



	public int idDuplicateCheck(UserVO inVO) throws SQLException;
	
	
	public int doSave(UserVO inVO) throws SQLException;
	
	
}
