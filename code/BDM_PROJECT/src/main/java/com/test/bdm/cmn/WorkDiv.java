package com.test.bdm.cmn;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
	
public interface WorkDiv<T> {

		int doUpdate(T inVO) throws SQLException;
		int doDelete(T inVO) throws SQLException;
		T doSelectOne(T inVO) throws SQLException, EmptyResultDataAccessException;
		int doSave(T inVO) throws SQLException;
		// List<T> doRetrieve(T inVO) throws SQLException;
}