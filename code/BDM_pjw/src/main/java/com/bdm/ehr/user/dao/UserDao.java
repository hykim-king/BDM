package com.bdm.ehr.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.bdm.ehr.cmn.WorkDiv;
import com.bdm.ehr.user.domain.UserVO;

public interface UserDao extends WorkDiv<UserVO> {

	List<UserVO> getAll(UserVO inVO) throws SQLException;

	int getCount(UserVO inVO) throws SQLException;
	
	int idDuplicateCheck(UserVO inVO) throws SQLException;

}