package com.test.bdm.cmn;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
	
public interface WorkDiv<T> {

		int doUpdate(T inVO) throws SQLException;
		int doDelete(T inVO) throws SQLException;
		T doSelectOne(T inVO) throws SQLException, EmptyResultDataAccessException;
		int doSave(T inVO) throws SQLException;
<<<<<<< HEAD
		// List<T> doRetrieve(T inVO) throws SQLException;

=======
>>>>>>> 005b50598c6e610b710bbb2d7889a2f7da5f27a0
}

